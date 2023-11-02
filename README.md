![Logo](logo.webp)

# Java Program - UTS PBO 2023
Pada repository ini mencakup tentang program Java yang dikerjakan untuk UTS mata kuliah Desain dan Analisis Algoritma (DAA).

Program ini akan mencakup konsep-konsep bahasa pemrograman seperti memodelkan graph, mengidentifikasi rute graph, dan menghitung jarak terpendek. Serta Memodelkan solusi dari penggunaan algoritma binary search pada sebuah program untuk mencari suatu nilai dari data yang telah ditentunkan

## List Program

 - [Program Rute Graph](#program-rute-graph)
 - [Program Binary Search](#program-binary-search)

## Program Rute Graph
Berikut kode program untuk mencari rute dari sebuah Graph

```bash
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
````

Output: 

```bash
Graph:
V6:
 - V7
V7:

V1:
 - V2  - V3  - V4
V2:
 - V3  - V5
V3:
 - V4  - V5  - V6
V4:
 - V6
V5:
 - V6  - V7

Terdapat 7 vertex dan Ada 12 edge

Semua rute yang mungkin dilewati dari V1 ke V7:
Rute 1: [V1, V2, V3, V4, V6, V7]
Rute 2: [V1, V2, V3, V5, V6, V7]
Rute 3: [V1, V2, V3, V5, V7]
Rute 4: [V1, V2, V3, V6, V7]
Rute 5: [V1, V2, V5, V6, V7]
Rute 6: [V1, V2, V5, V7]
Rute 7: [V1, V3, V4, V6, V7]
Rute 8: [V1, V3, V5, V6, V7]
Rute 9: [V1, V3, V5, V7]
Rute 10: [V1, V3, V6, V7]
Rute 11: [V1, V4, V6, V7]

Jarak terdekat dari V1 ke V7: 3
Rute yang mungkin dilewati: [V1, V4, V6, V7]
```

* [Download Source Code](Graph.java)


## Program Binary Search

Berikut kode program untuk mencari data menggunakan Binary Search

```bash
/* 
 * UTS DAA 2023 - Farouq Mulya Al Simabua - 2022071087
 * 
 * ----------------------------------------------------
 * Program untuk mencari data menggunakan Binary Search
 * ----------------------------------------------------
 */

class Searching {
    public static int[] data = null;
    public static int awal, tengah, akhir, temp, count;

    public static void main(String[] args) {
        // Data yang ingin dicari
        int cari = 10;

        // Data yang akan dicari
        data = new int[] { 4, 15, 10, 5, 0, 8 };

        // Menampilkan data yang akan dicari
        System.out.print("Data yang dicari: " + cari);

        // Sorting data
        sorting();

        // Menampilkan data setelah diurutkan
        System.out.print("\nData setelah diurutkan: ");
        for (int x = 0; x < data.length; x++)
            System.out.print(data[x] + " ");

        // Pencarian menggunakan Binary Search
        boolean temu = false;
        awal = 0;
        akhir = data.length - 1;
        temp = 0;
        count = 0;
        int iterasi = 0;
        System.out.println("\nIterasi Aw Ak Te Ni");

        while (temu != true) {
            tengah = (awal + akhir) / 2;
            iterasi++;

            if (data[tengah] == cari) {
                System.out.print(iterasi + " ");
                System.out.print(awal + " ");
                System.out.print(akhir + " ");
                System.out.print(tengah + " ");
                System.out.print(data[tengah] + "\n");

                temu = true;
                break;
            } else if (data[tengah] < cari) {
                System.out.print(iterasi + " ");
                System.out.print(awal + " ");
                System.out.print(akhir + " ");
                System.out.print(tengah + " ");
                System.out.print(data[tengah] + "\n");
                awal = tengah + 1;
            } else if (data[tengah] > cari) {
                System.out.print(iterasi + " ");
                System.out.print(awal + " ");
                System.out.print(akhir + " ");
                System.out.print(tengah + " ");
                System.out.print(data[tengah] + "\n");
                akhir = tengah - 1;
            }

            // Cek worst case
            if (temp != data[tengah])
                temp = data[tengah];
            else
                count++;

            // Batasan untuk worst case
            if (count == 3)
                break;
        }

        // Output
        if (temu == true)
            System.out.println(
                    "\nData " + cari + " ditemukan pada index ke-" + tengah + "\n" + "dan iterasi ke-" + iterasi);
        else
            System.out.println("\nData " + cari + " tidak ditemukan");

    }

    // Sorting ascending
    public static void sorting() {
        int temp = 0;
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length; y++) {
                if (x == y)
                    continue;
                else {
                    if (data[x] < data[y]) {
                        temp = data[y];
                        data[y] = data[x];
                        data[x] = temp;
                    }
                }
            }
        }
    }
}
```

Output:
```bash
Data yang dicari: 10
Data setelah diurutkan: 0 4 5 8 10 15 
Iterasi Aw Ak Te Ni
1 0 5 2 5
2 3 5 4 10

Data 10 ditemukan pada index ke-4
dan iterasi ke-2
```

* [Download Source Code](Search.java)

## Authors

- [@Farouq Mulya Al Simabua - 2022071087](https://github.com/stdarkpha/)
