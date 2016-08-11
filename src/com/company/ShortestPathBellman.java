package com.company;

import com.company.mst.IntegerPair;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Welly on 8/9/2016.
 */
public class ShortestPathBellman {
    ArrayList<IntegerPair>[] AdjList;
    int V;
    static final int INF = 10000000;

    ShortestPathBellman(int V) {
        this.V = V;

        AdjList = (ArrayList<IntegerPair>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            AdjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int w) {
        AdjList[u].add(new IntegerPair(v, w));
    }

    public int calcSp(int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int i = 0; i < V - 1; i++) // relax all E edges V-1 times, O(V)
            for (int u = 0; u < V; u++) { // these two loops = O(E)
                for (IntegerPair v : AdjList[u]) {
                    dist[v.first()] = Math.min(dist[v.first()], dist[u] + v.second());
                }
            }

        boolean negative_cycle_exist = false;
        for (int u = 0; u < V; u++) { // one more pass to check

            for (IntegerPair v : AdjList[u]) {
                if (dist[v.first()] > dist[u] + v.second()) // should be false, but if possible
                    negative_cycle_exist = true;            // then negative cycle exists!
            }
        }

        System.out.printf("Negative Cycle Exist? %s\n", negative_cycle_exist ? "Yes" : "No");
        if (!negative_cycle_exist)
            for (int i = 0; i < V; i++)
                System.out.printf("SSSP(%d, %d) = %d\n", source, i, dist[i]);
        return 0;
    }
}
