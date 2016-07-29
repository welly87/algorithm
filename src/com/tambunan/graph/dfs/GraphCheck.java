package com.tambunan.graph.dfs;

import com.tambunan.graph.IntegerPair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Welly on 7/29/2016.
 */
public class GraphCheck {
    private static final int DFS_WHITE = -1; // normal DFS
    private static final int DFS_BLACK = 1;
    private static final int DFS_GRAY = 2;

    private static int numComp;
    private static Vector < Vector <IntegerPair> > AdjList =
            new Vector < Vector < IntegerPair > >();

    private static Vector < Integer > dfs_num, dfs_low, dfs_parent;

    private static void initGraphCheck(int V) {
        initDFS(V);
        dfs_parent = new Vector < Integer > ();
        dfs_parent.addAll(Collections.nCopies(V, 0));
        numComp = 0;
    }

    private static void initDFS(int V) { // used in normal DFS
        dfs_num = new Vector < Integer > ();
        dfs_num.addAll(Collections.nCopies(V, DFS_WHITE));
        numComp = 0;
    }

    private static void printThis(String message) {
        System.out.printf("==================================\n");
        System.out.printf("%s\n", message);
        System.out.printf("==================================\n");
    }

    private static void graphCheck(int u) { // DFS for checking graph edge properties...
        dfs_num.set(u, DFS_GRAY); // color this as DFS_GRAY (temporary)
        Iterator it = AdjList.get(u).iterator();
        while (it.hasNext()) { // traverse this AdjList
            IntegerPair v = (IntegerPair)it.next();
            if (dfs_num.get(v.first()) == DFS_WHITE) { // DFS_GRAY to DFS_WHITE
                // System.out.printf("  Tree Edge (%d, %d)\n", u, v.first());
                dfs_parent.set(v.first(), u); // parent of this children is me
                graphCheck(v.first());
            }
            else if (dfs_num.get(v.first()) == DFS_GRAY) { // DFS_GRAY to DFS_GRAY
                if (v.first() == dfs_parent.get(u))
                    System.out.printf(" Bidirectional Edge (%d, %d) - (%d, %d)\n", u, v.first(), v.first(), u);
                else
                    System.out.printf(" Back Edge (%d, %d) (Cycle)\n", u, v.first());
            }
            else if (dfs_num.get(v.first()) == DFS_BLACK) // DFS_GRAY to DFS_BLACK
                System.out.printf(" Forward/Cross Edge (%d, %d)\n", u, v.first());
        }
        dfs_num.set(u, DFS_BLACK); // now color this as DFS_BLACK (DONE)
    }

    public static void main(String[] args) throws FileNotFoundException {
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

        initGraphCheck(V);
        printThis("Graph Edges Property Check");
        for (i = 0; i < V; i++)
            if (dfs_num.get(i) == DFS_WHITE) {
                System.out.printf("Component %d:\n", ++numComp);
                graphCheck(i);
            }
    }
}
