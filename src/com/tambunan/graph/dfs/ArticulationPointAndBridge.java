package com.tambunan.graph.dfs;

import java.io.File;
import java.util.*;

/**
 * Created by Welly on 7/29/2016.
 */
public class ArticulationPointAndBridge {
    private static final int DFS_WHITE = -1; // normal DFS
    private static final int DFS_BLACK = 1;
    private static Vector < Boolean > articulation_vertex, visited;
    private static Stack< Integer > S; // additional information for SCC
    private static Vector < Integer > topologicalSort; // additional information for toposort
    private static int numComp, dfsNumberCounter, dfsRoot, rootChildren;
    private static Vector < Integer > dfs_num, dfs_low, dfs_parent;
    private static Vector < Vector < IntegerPair > > AdjList =
            new Vector < Vector < IntegerPair > >();

    private static void initGraphCheck(int V) {
        initDFS(V);
        dfs_parent = new Vector < Integer > ();
        dfs_parent.addAll(Collections.nCopies(V, 0));
        numComp = 0;
    }

    private static void initArticulationPointBridge(int V) {
        initGraphCheck(V);
        dfs_low = new Vector < Integer > ();
        dfs_low.addAll(Collections.nCopies(V, 0));
        articulation_vertex = new Vector < Boolean > ();
        articulation_vertex.addAll(Collections.nCopies(V, false));
        dfsNumberCounter = 0;
    }

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


    private static void articulationPointAndBridge(int u) {
        dfs_low.set(u, dfsNumberCounter);
        dfs_num.set(u, dfsNumberCounter++); // dfs_low[u] <= dfs_num[u]
        Iterator it = AdjList.get(u).iterator();
        while (it.hasNext()) { // try all neighbors v of vertex u
            IntegerPair v = (IntegerPair)it.next();
            if (dfs_num.get(v.first()) == DFS_WHITE) { // a tree edge
                dfs_parent.set(v.first(), u); // parent of this children is me
                if (u == dfsRoot) // special case
                    rootChildren++; // count children of root
                articulationPointAndBridge(v.first());
                if (dfs_low.get(v.first()) >= dfs_num.get(u)) // for articulation point
                    articulation_vertex.set(u, true); // store this information first
                if (dfs_low.get(v.first()) > dfs_num.get(u)) // for bridge
                    System.out.printf(" Edge (%d, %d) is a bridge\n", u, v.first());
                dfs_low.set(u, Math.min(dfs_low.get(u), dfs_low.get(v.first()))); // update dfs_low[u]
            }
            else if (v.first() != dfs_parent.get(u)) // a back edge and not direct cycle
                dfs_low.set(u, Math.min(dfs_low.get(u), dfs_num.get(v.first()))); // update dfs_low[u]
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

        initArticulationPointBridge(V);
        printThis("Articulation Points & Bridges (the input graph must be UNDIRECTED)");
        System.out.printf("Bridges:\n");
        for (i = 0; i < V; i++)
            if (dfs_num.get(i) == DFS_WHITE) {
                dfsRoot = i; rootChildren = 0;
                articulationPointAndBridge(i);
                articulation_vertex.set(dfsRoot, (rootChildren > 1)); // special case
            }

        System.out.printf("Articulation Points:\n");
        for (i = 0; i < V; i++)
            if (articulation_vertex.get(i))
                System.out.printf(" Vertex %d\n", i);

    }
}
