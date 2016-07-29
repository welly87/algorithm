package com.tambunan.graph.dfs;

import com.tambunan.graph.IntegerPair;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Welly on 7/29/2016.
 */
public class TopologicalSort {
    private static final int DFS_WHITE = -1; // normal DFS
    private static final int DFS_BLACK = 1;
    private static Vector < Integer > topologicalSort; // additional information for toposort
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

    private static void initTopologicalSort(int V) {
        initDFS(V);
        topologicalSort = new Vector < Integer > ();
    }

    private static void topoVisit(int u) {
        dfs_num.set(u, DFS_BLACK);
        Iterator it = AdjList.get(u).iterator();
        while (it.hasNext()) {
            IntegerPair v = (IntegerPair)it.next();
            if (dfs_num.get(v.first()) == DFS_WHITE)
                topoVisit(v.first());
        }
        topologicalSort.add(u);
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

        initTopologicalSort(V);
        printThis("Topological Sort (the input graph must be DAG)");
        for (i = 0; i < V; i++)
            if (dfs_num.get(i) == DFS_WHITE)
                topoVisit(i);
        for (i = topologicalSort.size() - 1; i >= 0; i--) // access from back to front
            System.out.printf(" %d", topologicalSort.get(i));
        System.out.printf("\n");
    }
}
