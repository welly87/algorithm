package com.tambunan.graph.dfs;

import com.tambunan.graph.IntegerPair;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Welly on 7/29/2016.
 */
public class SimpleDfs  {
    private static final int DFS_WHITE = -1; // normal DFS
    private static final int DFS_BLACK = 1;

    private static int numComp;
    private static Vector < Vector <IntegerPair> > AdjList =
            new Vector < Vector < IntegerPair > >();
    private static Vector < Integer > dfs_num;

    private static void printThis(String message) {
        System.out.printf("==================================\n");
        System.out.printf("%s\n", message);
        System.out.printf("==================================\n");
    }

    private static void initDFS(int V) { // used in normal DFS
        dfs_num = new Vector < Integer > ();
        dfs_num.addAll(Collections.nCopies(V, DFS_WHITE));
        numComp = 0;
    }

    private static void dfs(int u) { // DFS for normal usage
        System.out.printf(" %d", u); // this vertex is visited

        dfs_num.set(u, DFS_BLACK); // mark as visited

        for (IntegerPair v: AdjList.get(u)) {
            if (dfs_num.get(v.first()) == DFS_WHITE) // avoid cycle
                dfs(v.first()); // v is a (neighbor, weight) pair
        }
    }

    public static void main(String[] args)  throws Exception  {
        int i, j, V, total_neighbors, id, weight;

        File f = new File("in_01.txt");
        Scanner sc = new Scanner(f);

        V = sc.nextInt();
        AdjList.clear();
        for (i = 0; i < V; i++) {
            Vector< IntegerPair > Neighbor = new Vector < IntegerPair >(); // create vector of pair<int, int>
            AdjList.add(Neighbor); // store blank vector first
        }

        for (i = 0; i < V; i++) {
            total_neighbors = sc.nextInt();
            for (j = 0; j < total_neighbors; j++) {
                id = sc.nextInt();
                weight = sc.nextInt();
                AdjList.get(i).add(new IntegerPair(id, weight));
            }
        }

        initDFS(V); // call this first before running DFS
        printThis("Standard DFS Demo (the input graph must be UNDIRECTED)");
        for (i = 0; i < V; i++) // for each vertex i in [0..V-1]
            if (dfs_num.get(i) == DFS_WHITE) { // if not visited yet
                System.out.printf("Component %d, visit:", ++numComp);
                dfs(i);
                System.out.printf("\n");
            }
        System.out.printf("There are %d connected components\n", numComp);
    }
}
