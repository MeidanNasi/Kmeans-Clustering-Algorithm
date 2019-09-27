package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	// ************************************************************************* //

						// ****** vars ****** //
	static HashMap<String, String> map = new HashMap<>(); // mapping between name to age
	static ArrayList<String> list1 = new ArrayList<>();
	static ArrayList<String> list2 = new ArrayList<>();
	static ArrayList<String> fixedList = new ArrayList<>(); //
	static int[] ages = new int[69];
	static Statement stmt = null;
	static ArrayList<int[]> clusters = new ArrayList<>();

	
					// ******* func spacer ****** //
	static ArrayList<String> spaceRemoverAndUpdate(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String tmp = list.get(i);
			char[] arr = tmp.toCharArray();
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] == arr[j + 1]) {
					arr[j] = ' ';
					arr[j + 1] = ' ';
				}

			}
			StringBuilder bs = new StringBuilder();
			bs.append(arr);
			fixedList.add(bs.toString());
		}
		return fixedList;

	}

	// ************************ visual application GUI ******************************* //

	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.setTitle("Displayer");
			BorderPane root = new BorderPane();

			final NumberAxis xAxis = new NumberAxis();
			final NumberAxis yAxis = new NumberAxis();
			final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
			xAxis.setLabel("Ages of users");
			lineChart.setTitle("Clustering ages");
			
			// cluster 1
			XYChart.Series series1 = new XYChart.Series();
			series1.setName("Cluster 1");
			for(int j=0; j<clusters.get(0).length; j++) {
				if(clusters.get(0)[j] != 0) {
				series1.getData().add(new XYChart.Data(clusters.get(0)[j], 1)); }
			}	
			
			// cluster 2
			XYChart.Series series2 = new XYChart.Series();
			series2.setName("Cluster 2");
			for(int j=0; j<clusters.get(1).length; j++) {
				if(clusters.get(1)[j] != 0) {
				series2.getData().add(new XYChart.Data(clusters.get(1)[j], 1)); }
			}	
			
			// cluster 3
			XYChart.Series series3 = new XYChart.Series();
			series3.setName("Cluster 3");
			for(int j=0; j<clusters.get(2).length; j++) {
				if(clusters.get(2)[j] != 0) {
				series3.getData().add(new XYChart.Data(clusters.get(2)[j], 1)); }
			}	
			
			// cluster 4
			XYChart.Series series4 = new XYChart.Series();
			series4.setName("Cluster 4");
			for(int j=0; j<clusters.get(3).length; j++) {
				if(clusters.get(3)[j] != 0) {
				series4.getData().add(new XYChart.Data(clusters.get(3)[j], 1)); }
			}	
			
			// cluster 5
			XYChart.Series series5 = new XYChart.Series();
			series5.setName("Cluster 5");
			for(int j=0; j<clusters.get(4).length; j++) {
				if(clusters.get(4)[j] != 0) {
				series5.getData().add(new XYChart.Data(clusters.get(4)[j], 1)); }
			}	
			
			

			Scene scene = new Scene(lineChart, 800, 300);
			lineChart.getData().addAll(series2,series1, series3, series4, series5);

			
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		
		// **** connecting to db **** //
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
			System.out.print("Database is connected !");

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			// ****** query for ex 1 ****** //
			String sql1 = "SELECT name, age " + "FROM users " + "WHERE age = max(age) " + "GROUP BY country";

			// ****** query for ex 2 ****** //

			String sql2 = "SELECT name, MAX(`users`.`age`) as `age` FROM test.users WHERE created_date BETWEEN '2019-07-03 00:00:00' AND '2019-07-19 00:00:00' GROUP BY country";

			
			// ******* holds the result ******* //
			
			ResultSet rs2 = stmt.executeQuery(sql2);
			
			// ********* pump the result into a map ****** //
			while (rs2.next()) {
				// Retrieve by column name and age
				map.put(rs2.getString("name"), rs2.getString("age"));
				list2.add(rs2.getString("name"));
			}
					
			fixedList = spaceRemoverAndUpdate(list2);
			
			System.out.println(list2); 
			System.out.println(fixedList);

			// ********* saving ages into an array so we can send it to the clustering algorithm

			Collection<String> ageCollection = map.values();
			Iterator<String> iterator = ageCollection.iterator();
			for (int i = 0; i < ages.length; i++) {
				ages[i] = Integer.parseInt(iterator.next());
			}

			conn.close();

		}
		// ********** in case connection to db failed *********** //
		catch (Exception e) {
			System.out.print("Do not connect to DB - Error:" + e);
		}

		Kmeans k = new Kmeans();
		clusters = k.calculate(ages);
		
		launch(args);

	}
}
