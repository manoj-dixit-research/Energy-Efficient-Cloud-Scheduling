package Code;

import java.util.ArrayList;
import java.util.Random;

public class KMeans {
    public ArrayList<ArrayList<Double>> data;
    public int k;
    public ArrayList<ArrayList<Double>> centroids;
    public ArrayList<Integer> clusters;

    public KMeans(ArrayList<ArrayList<Double>> data, int k) {
        this.data = data;
        this.k = k;
        this.centroids = new ArrayList<>();
        this.clusters = new ArrayList<>();
    }

    public void fit() {
        // initialize centroids randomly
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            int index = rand.nextInt(data.size());
            centroids.add(data.get(index));
        }

        // assign points to clusters and update centroids
        boolean changed = true;
        while (changed) {
            changed = false;

            // assign points to clusters
            for (int i = 0; i < data.size(); i++) {
                ArrayList<Double> point = data.get(i);
                double minDistance = Double.MAX_VALUE;
                int cluster = -1;
                for (int j = 0; j < k; j++) {
                    ArrayList<Double> centroid = centroids.get(j);
                    double distance = euclideanDistance(point, centroid);
                    if (distance < minDistance) {
                        minDistance = distance;
                        cluster = j;
                    }
                }
                if (clusters.size() <= i || clusters.get(i) != cluster) {
                    clusters.add(i, cluster);
                    changed = true;
                }
            }

            // update centroids
            for (int i = 0; i < k; i++) {
                ArrayList<Double> sum = new ArrayList<>();
                for (int j = 0; j < data.get(0).size(); j++) {
                    sum.add(0.0);
                }
                int count = 0;
                for (int j = 0; j < data.size(); j++) {
                    if (clusters.get(j) == i) {
                        ArrayList<Double> point = data.get(j);
                        for (int l = 0; l < point.size(); l++) {
                            sum.set(l, sum.get(l) + point.get(l));
                        }
                        count++;
                    }
                }
                if (count > 0) {
                    for (int j = 0; j < sum.size(); j++) {
                        sum.set(j, sum.get(j) / count);
                    }
                    centroids.set(i, sum);
                }
            }
        }
    }

    public ArrayList<Integer> getClusters() {
        return clusters;
    }

    public double euclideanDistance(ArrayList<Double> a, ArrayList<Double> b) {
        double sum = 0.0;
        for (int i = 0; i < a.size(); i++) {
            sum += Math.pow(a.get(i) - b.get(i), 2);
        }
        return Math.sqrt(sum);
    }
}
