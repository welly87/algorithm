package com.tambunan.graph.mst;

import com.tambunan.graph.IntegerPair;
import com.tambunan.graph.IntegerTriple;
import com.tambunan.graph.UnionFind;

import java.io.File;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class Kruskal {
    static Vector<Vector<IntegerPair>> AdjList = new Vector<Vector<IntegerPair>>();

    public static void main(String[] args) throws Exception {
        int V, E, u, v, w;

        File f = new File("in_03.txt");
        Scanner sc = new Scanner(f);

        V = sc.nextInt();
        E = sc.nextInt();

        AdjList.clear();
        for (int i = 0; i < V; i++) {
            Vector<IntegerPair> Neighbor = new Vector<IntegerPair>(); // create vector of pair<int, int>
            AdjList.add(Neighbor); // store blank vector first
        }
        Vector<IntegerTriple> EdgeList = new Vector<IntegerTriple>();

        // sort by edge weight O(E log E)
        for (int i = 0; i < E; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            w = sc.nextInt();
            EdgeList.add(new IntegerTriple(w, u, v));                // (w, u, v)
        }
        Collections.sort(EdgeList);

        int mst_cost = 0;           // all V are disjoint sets at the beginning
        UnionFind UF = new UnionFind(V);
        for (int i = 0; i < E; i++) {                   // for each edge, O(E)
            IntegerTriple front = EdgeList.get(i);
            if (!UF.isSameSet(front.second(), front.third())) {          // check
                mst_cost += front.first();            // add the weight of e to MST
                UF.unionSet(front.second(), front.third());            // link them
            }
        }

        // note: the number of disjoint sets must eventually be 1 for a valid MST
        System.out.printf("MST cost = %d (Kruskal's)\n", mst_cost);

    }
}
