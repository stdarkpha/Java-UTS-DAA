/* 
 * UTS DAA 2023 - Farouq Mulya Al Simabua - 2022071087
 * 
 * --------------------------------------------
 * Program untuk mencari rute dari sebuah Graph
 * --------------------------------------------
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class Graph {
    // Membuat representasi graph dengan adjacency list
    private final HashMap<String, LinkedList<String>> adjList;
    private int countVertex, countEdge; // Jumlah vertex dan edge

    // Constructor Graph
    public Graph() {
        adjList = new HashMap<>();
        countEdge = countVertex = 0;
    }

    // Method untuk menambahkan vertex dan edge
    public void addVertex(String vertex) {
        adjList.put(vertex, new LinkedList<>());
        countVertex++;
    }

    // Method untuk menambahkan edge
    public void addEdge(String src, String dst) {
        if (adjList.containsKey(src) && adjList.containsKey(dst)) {
            adjList.get(src).add(dst);
            countEdge++;
        }
    }

    // Method untuk menghitung jumlah vertex dan edge
    public int lengthVertex() {
        return countVertex;
    }

    public int lengthEdge() {
        return countEdge;
    }

    public List<String> findShortestPath(String start, String end) {
        // Inisialisasi struktur data yang dibutuhkan
        HashMap<String, Integer> distances = new HashMap<>();
        HashMap<String, String> previousNodes = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        HashSet<String> visited = new HashSet<>();

        // Inisialisasi jarak awal untuk semua simpul sebagai tak terbatas
        for (String vertex : adjList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        // Set jarak awal untuk simpul awal sebagai 0
        distances.put(start, 0);
        priorityQueue.add(start);

        // Algoritma Dijkstra
        while (!priorityQueue.isEmpty()) {
            String current = priorityQueue.poll();
            visited.add(current);

            LinkedList<String> neighbors = adjList.get(current);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    int newDistance = distances.get(current) + 1; // Bobot setiap edge dianggap 1

                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previousNodes.put(neighbor, current);
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }

        // Rekonstruksi jalur terpendek dari start ke end
        List<String> shortestPath = new ArrayList<>();
        String current = end;
        while (current != null) {
            shortestPath.add(0, current);
            current = previousNodes.get(current);
        }

        return shortestPath;
    }

    // Method untuk mencari semua rute yang mungkin dilewati dari start ke end
    public List<List<String>> findAllPaths(String start, String end) {
        List<List<String>> allPaths = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        findAllPathsUtil(start, end, visited, path, allPaths);

        return allPaths;
    }

    // Method rekursif untuk mencari semua rute yang mungkin dilewati dari start ke
    // end
    private void findAllPathsUtil(String current, String end, HashSet<String> visited, List<String> path,
            List<List<String>> allPaths) {
        visited.add(current);
        path.add(current);

        if (current.equals(end)) {
            allPaths.add(new ArrayList<>(path));
        } else {
            LinkedList<String> neighbors = adjList.get(current);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    findAllPathsUtil(neighbor, end, visited, path, allPaths);
                }
            }
        }

        visited.remove(current);
        path.remove(path.size() - 1);
    }

    // Method untuk mencetak graph
    public void printGraph() {
        for (String vertex : adjList.keySet()) {
            System.out.println(vertex + ":");
            for (String data : adjList.get(vertex))
                System.out.print(" - " + data + " ");
            System.out.println();
        }
    }
}

class Main {
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex("V1");
        g.addVertex("V2");
        g.addVertex("V3");
        g.addVertex("V4");
        g.addVertex("V5");
        g.addVertex("V6");
        g.addVertex("V7");

        g.addEdge("V2", "V3"); // 1
        g.addEdge("V3", "V4"); // 1
        g.addEdge("V5", "V6"); // 2
        g.addEdge("V6", "V7"); // 3
        g.addEdge("V1", "V2"); // 5
        g.addEdge("V3", "V5"); // 5
        g.addEdge("V2", "V5"); // 6
        g.addEdge("V1", "V3"); // 7
        g.addEdge("V5", "V7"); // 7
        g.addEdge("V3", "V6"); // 10
        g.addEdge("V1", "V4"); // 12
        g.addEdge("V4", "V6"); // 13

        System.out.println("Graph:");
        g.printGraph();

        System.out.print("\nTerdapat " + g.lengthVertex() + " vertex dan ");
        System.out.println("Ada " + g.lengthEdge() + " edge");

        List<List<String>> allPaths = g.findAllPaths("V1", "V7");
        System.out.println("\nSemua rute yang mungkin dilewati dari V1 ke V7:");

        for (int i = 0; i < allPaths.size(); i++) {
            List<String> path = allPaths.get(i);
            System.out.println("Rute " + (i + 1) + ": " + path);
        }

        List<String> shortestPath = g.findShortestPath("V1", "V7");
        System.out.println("\nJarak terdekat dari V1 ke V7: " + (shortestPath.size() - 1));
        System.out.println("Rute yang mungkin dilewati: " + shortestPath);
    }
}