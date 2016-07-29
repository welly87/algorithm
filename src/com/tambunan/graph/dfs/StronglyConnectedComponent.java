package com.tambunan.graph.dfs;

import com.tambunan.graph.IntegerPair;

import java.io.File;
import java.util.*;

/**
 * Created by Welly on 7/29/2016.
 */
public class StronglyConnectedComponent {
    private static final int DFS_WHITE = -1; // normal DFS
    private static Vector<Boolean> articulation_vertex, visited;
    private static Stack<Integer> S; // additional information for SCC
    private static Vector<Integer> dfs_num, dfs_low, dfs_parent;
    private static Vector<Vector<IntegerPair>> AdjList =
            new Vector<Vector<IntegerPair>>();
    private static int numComp, dfsNumberCounter;

    private static void initGraphCheck(int V) {
        initDFS(V);
        dfs_parent = new Vector<Integer>();
        dfs_parent.addAll(Collections.nCopies(V, 0));
        numComp = 0;
    }

    private static void printThis(String message) {
        System.out.printf("==================================\n");
        System.out.printf("%s\n", message);
        System.out.printf("==================================\n");
    }

    private static void initDFS(int V) { // used in normal DFS
        dfs_num = new Vector<Integer>();
        dfs_num.addAll(Collections.nCopies(V, DFS_WHITE));
        numComp = 0;
    }

    private static void initTarjanSCC(int V) {
        initGraphCheck(V);
        dfs_low = new Vector<Integer>();
        dfs_low.addAll(Collections.nCopies(V, 0));
        dfsNumberCounter = 0;
        numComp = 0;
        S = new Stack<Integer>();
        visited = new Vector<Boolean>();
        visited.addAll(Collections.nCopies(V, false));
    }

    private static void tarjanSCC(int u) {
        dfs_num.set(u, dfsNumberCounter);
        dfs_low.set(u, dfsNumberCounter++); // dfs_low[u] <= dfs_num[u]
        S.push(u); // store u according to order of visitation
        visited.set(u, true);

        Iterator it = AdjList.get(u).iterator();
        while (it.hasNext()) { // try all neighbors v of vertex u
            IntegerPair v = (IntegerPair) it.next();
            if (dfs_num.get(v.first()) == DFS_WHITE) // a tree edge
                tarjanSCC(v.first());
            if (visited.get(v.first())) // condition for update
                dfs_low.set(u, Math.min(dfs_low.get(u), dfs_low.get(v.first())));
        }

        if (dfs_low.get(u) == dfs_num.get(u)) { // if this is a root (start) of an SCC
            System.out.printf("SCC %d: ", ++numComp);
            while (true) {
                int v = S.peek();
                S.pop();
                visited.set(v, false);
                System.out.printf(" %d", v);
                if (u == v) break;
            }
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        int i, j, V, total_neighbors, id, weight;

        File f = new File("in_01.txt");
        Scanner sc = new Scanner(f);

        V = sc.nextInt();
        AdjList.clear();
        for (i = 0; i < V; i++) {
            Vector<IntegerPair> Neighbor = new Vector<IntegerPair>(); // create vector of pair<int, int>
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


        initTarjanSCC(V);
        printThis("Strongly Connected Components (the input graph must be DIRECTED)");
        for (i = 0; i < V; i++)
            if (dfs_num.get(i) == DFS_WHITE)
                tarjanSCC(i);
    }


}
