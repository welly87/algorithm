package com.tambunan.graph.dfs;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Welly on 7/29/2016.
 */
public class SimpleDfs {
    private static final int DFS_WHITE = -1; // normal DFS
    private static final int DFS_BLACK = 1;

    private static int numComp;
    private static Vector<Vector<Integer>> AdjList = new Vector<Vector<Integer>>();
    private static int[] dfs_num;

    private static void printThis(String message) {
        System.out.printf("==================================\n");
        System.out.printf("%s\n", message);
        System.out.printf("==================================\n");
    }

    private static void initDFS(int V) { // used in normal DFS
        initializeAdjList(V);

        dfs_num = new int[V];
        Arrays.fill(dfs_num, DFS_WHITE);

        numComp = 0;
    }

    private static void initializeAdjList(int V) {
        AdjList.clear();
        for (int i = 0; i < V; i++) {
            Vector<Integer> neighbor = new Vector<Integer>();
            AdjList.add(neighbor); // store blank vector first
        }
    }

    private static void dfs(int u) { // DFS for normal usage
        System.out.printf(" %d", u); // this vertex is visited

        dfs_num[u] = DFS_BLACK; // mark as visited

        for (Integer v : AdjList.get(u)) {
            if (dfs_num[v] == DFS_WHITE) // avoid cycle
                dfs(v); // v is a (neighbor, weight) pair
        }
    }

    public static void main(String[] args) throws Exception {

        File f = new File("in_01.txt");
        Scanner sc = new Scanner(f);

        int V = sc.nextInt();

        initDFS(V); // call this first before running DFS

        for (int i = 0; i < V; i++) {
            int total_neighbors = sc.nextInt();
            for (int j = 0; j < total_neighbors; j++) {
                int id = sc.nextInt();
                int weight = sc.nextInt();
                AdjList.get(i).add(id);
            }
        }

        printThis("Standard DFS Demo (the input graph must be UNDIRECTED)");
        for (int i = 0; i < V; i++) // for each vertex i in [0..V-1]
            if (dfs_num[i] == DFS_WHITE) { // if not visited yet
                System.out.printf("Component %d, visit:", ++numComp);
                dfs(i);
                System.out.printf("\n");
            }
        System.out.printf("There are %d connected components\n", numComp);
    }
}
