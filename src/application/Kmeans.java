package application;

import java.util.ArrayList;

public class Kmeans {

	public ArrayList<int[]> calculate(int[] arr) {

		// vars and what they stand for

		int m1, m2, m3, m4, m5; // represents the means
		int a, b, c, d, e; // represents the prev val of the means
		int n = 0; // represents the number of iteration
		boolean flag = true; // a flag to notice if means have changed or not
		float sum1, sum2, sum3, sum4, sum5; // each sum represents the cluster's sum, so we'll be able to caclculate the avg.
		int[] disatance = new int[5]; // distance table
		ArrayList<int[]> clusters = new ArrayList<>(); // the array we return 

		// choosing random means

		a = arr[0];
		b = arr[1];
		c = arr[2];
		d = arr[3];
		e = arr[4];

		m1 = a;
		m2 = b;
		m3 = c;
		m4 = d;
		m5 = e;

		// our clusters

		int cluster1[] = new int[arr.length];
		int cluster2[] = new int[arr.length];
		int cluster3[] = new int[arr.length];
		int cluster4[] = new int[arr.length];
		int cluster5[] = new int[arr.length];

		int k1 = 0, k2 = 0, k3 = 0, k4 = 0, k5 = 0; // represents num of element in each cluster
		int i = 0; // the index running over the data arr

		do {

			sum1 = 0;
			sum2 = 0;
			sum3 = 0;
			sum4 = 0;
			sum5 = 0;
			n++;

			for (; i < arr.length; i++) {

				// holding the distance of the value from each the mean

				disatance[0] = Math.abs(arr[i] - m1);
				disatance[1] = Math.abs(arr[i] - m2);
				disatance[2] = Math.abs(arr[i] - m3);
				disatance[3] = Math.abs(arr[i] - m4);
				disatance[4] = Math.abs(arr[i] - m5);

				// finding the minimun distance

				int meanNumber = 0; // holds the mean which has the minimun dist from the val
				int minValue = disatance[0];
				for (int z = 1; z < disatance.length; z++) {
					if (disatance[z] < minValue) {
						meanNumber = z;
						minValue = disatance[z];
					}
				}

				// updating the clusters based on where is the minimum dist
				// meanNumber represents the index on the distances array
				// so if meanNumber equals to 0, arr[i] belongs to cluster 1

				if (meanNumber == 0) {
					cluster1[k1] = arr[i];
					k1++;
				} else if (meanNumber == 1) {
					cluster2[k2] = arr[i];
					k2++;
				} else if (meanNumber == 2) {
					cluster3[k3] = arr[i];
					k3++;
				} else if (meanNumber == 3) {
					cluster4[k4] = arr[i];
					k4++;
				} else if (meanNumber == 4) {
					cluster5[k5] = arr[i];
					k5++;
				}

			}

			System.out.println();

			// calcutating sum of each cluster

			for (int j = 0; j < k1; j++) {
				sum1 = sum1 + cluster1[j];
			}
			for (int j = 0; j < k2; j++) {
				sum2 = sum2 + cluster2[j];
			}
			for (int j = 0; j < k3; j++) {
				sum3 = sum3 + cluster3[j];
			}
			for (int j = 0; j < k4; j++) {
				sum4 = sum4 + cluster4[j];
			}
			for (int j = 0; j < k5; j++) {
				sum5 = sum5 + cluster5[j];
			}

			// printing current means

			// saving the values of the means before we update them, so then we can check if
			// they changed afer the update
			// remember-> my stop condition is if none of the mean need to update.

			a = m1;
			b = m2;
			c = m3;
			d = m4;
			e = m5;

			// updating means = (sum of values of the cluster) divided by (num of elements
			// in the cluster)

			m1 = Math.round(sum1 / k1);
			m2 = Math.round(sum2 / k2);
			m3 = Math.round(sum3 / k3);
			m4 = Math.round(sum4 / k4);
			m5 = Math.round(sum5 / k5);

			// a checking if there's a change or not

			if (m1 == a && m2 == b && m3 == c && m4 == d && m5 == e) {
				flag = false;
			}

			// printing the clusters of each iteration
			
			System.out.println("-----------------" +" iteretion: "+n+" -----------------------");
			
			// **** cluster 1 **** //
			System.out.println(" cluster 1 :\n");
			for (int j = 0; j < cluster1.length; j++) {
				System.out.print(cluster1[j] + "\t");
			}

			// **** cluster 2 **** //
			System.out.println("\n");
			System.out.println(" cluster 2 :\n");
			for (int j = 0; j < cluster2.length; j++) {
				System.out.print(cluster2[j] + "\t");
			}
			// **** cluster 3 **** //
			System.out.println("\n");
			System.out.println(" cluster 3 :\n");
			for (int j = 0; j < cluster3.length; j++) {
				System.out.print(cluster3[j] + "\t");
			}
			// **** cluster 4 **** //
			System.out.println("\n");
			System.out.println(" cluster 4 :\n");
			for (int j = 0; j < cluster4.length; j++) {
				System.out.print(cluster4[j] + "\t");
			}
			// **** cluster 5 **** //
			System.out.println("\n");
			System.out.println(" cluster 5 :\n");
			for (int j = 0; j < cluster5.length; j++) {
				System.out.print(cluster5[j] + "\t");
			}
			
			System.out.println("\n");
			System.out.println("prev means values: \n");
			System.out.println("a=" + a + "   b=" + b + "  c=" + c + "   d=" + d + "  e=" + e+ "\n");
			System.out.println("current means values: \n");
			System.out.println("m1=" + m1 + "   m2=" + m2 + "  m3=" + m3 + "   m4=" + m4 + "  m5=" + m5+ "\n"); 
			
			System.out.println("-----------------" +"End of iteretion: "+n+" -----------------------");
			
			
			System.out.println("\n");

			System.out.println("************************");
			
			if (flag == true) {
				System.out.println("there was an update, we go for another iteration...");
			}
			if (flag == false) {
				System.out.println("flag= " + flag + ", " + "no updates, done.");
			}
			
			System.out.println("************************");
			
			// if nothing has changed (means didnt update) , we done and printing the clusters

		} while (flag);

		// final clusters
		
		System.out.println("Final Clusters:");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Final cluster 1 :\n");
		for (int j = 0; j < cluster1.length; j++) {
			System.out.print(cluster1[j] + "\t");
		}

		System.out.println();
		System.out.println("Final cluster 2 :\n");
		for (int j = 0; j < cluster2.length; j++) {
			System.out.print(cluster2[j] + "\t");
		}

		System.out.println();
		System.out.println("Final cluster 3 :\n");
		for (int j = 0; j < cluster3.length; j++) {
			System.out.print(cluster3[j] + "\t");
		}
		System.out.println();
		System.out.println("Final cluster 4 :\n");
		for (int j = 0; j < cluster4.length; j++) {
			System.out.print(cluster4[j] + "\t");
		}
		System.out.println();
		System.out.println("Final cluster 5 :\n");
		for (int j = 0; j < cluster5.length; j++) {
			System.out.print(cluster5[j] + "\t");
		}

		System.out.println("_______________________________");
		System.out.println("_______________________________");
		System.out.println("_______________________________");
		System.out.println("_______________________________");
		System.out.println("ALGORITHM FINISHED");
		
		clusters.add(cluster1);
		clusters.add(cluster2);
		clusters.add(cluster3);
		clusters.add(cluster4);
		clusters.add(cluster5);
		
		return clusters;
		
	}
}
