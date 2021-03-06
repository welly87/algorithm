package com.tambunan.graph.mst;

import com.tambunan.graph.IntegerPair;
import com.tambunan.graph.IntegerTriple;
import com.tambunan.graph.UnionFind;

import java.io.File;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class Prim {
    static Vector<Vector<IntegerPair>> AdjList = new Vector<Vector<IntegerPair>>();
    static Vector<Boolean> taken = new Vector<Boolean>(); // global boolean flag to avoid cycle
    static PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>(); // priority queue to help choose shorter edges

    static void process(int vtx) { //  we do not need to use -ve sign to reverse the sort order
        taken.set(vtx, true);
        for (int j = 0; j < (int) AdjList.get(vtx).size(); j++) {
            IntegerPair v = AdjList.get(vtx).get(j);
            if (!taken.get(v.first()))
                pq.offer(new IntegerPair(v.second(), v.first()));
        }
    }

    public static void main(String[] args) throws Exception {
        int V, E, u, v, w;

        File f = new File("in_03.txt");
        Scanner sc = new Scanner(f);

        V = sc.nextInt();
        E = sc.nextInt();
        // Kruskal's algorithm merged with Prim's algorithm

        AdjList.clear();
        for (int i = 0; i < V; i++) {
            Vector<IntegerPair> Neighbor = new Vector<IntegerPair>(); // create vector of pair<int, int>
            AdjList.add(Neighbor); // store blank vector first
        }
//    Vector<IntegerTriple> EdgeList = new Vector<IntegerTriple>();

        // sort by edge weight O(E log E)
        // PQ default: sort descending. Trick: use <(negative) weight(i, j), <i, j> >
        for (int i = 0; i < E; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            w = sc.nextInt();
            AdjList.get(u).add(new IntegerPair(v, w));
            AdjList.get(v).add(new IntegerPair(u, w));
        }

        int mst_cost = 0;           // all V are disjoint sets at the beginning
        // inside int main() --- assume the graph is stored in AdjList, pq is empty
        for (int i = 0; i < V; i++)
            taken.add(false);                // no vertex is taken at the beginning
        process(0);   // take vertex 0 and process all edges incident to vertex 0
        mst_cost = 0;
        while (!pq.isEmpty()) { // repeat until V vertices (E=V-1 edges) are taken
            IntegerPair front = pq.peek();
            pq.poll();
            u = front.second();
            w = front.first();   // no need to negate id/weight
            if (!taken.get(u)) {           // we have not connected this vertex yet
                mst_cost += w;
                process(u); // take u, process all edges incident to u
            }
        }                                        // each edge is in pq only once!
        System.out.printf("MST cost = %d (Prim's)\n", mst_cost);
    }
}
