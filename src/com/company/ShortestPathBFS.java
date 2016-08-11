package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Welly on 8/9/2016.
 */
public class ShortestPathBFS {
    ArrayList<Integer>[] AdjList;
    int[] dist;
    int[] parent;
    static final int INF = 10000000;

    ShortestPathBFS(int V) {
        AdjList = (ArrayList<Integer>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            AdjList[i] = new ArrayList<>();
        }

        dist = new int[V];
        Arrays.fill(dist, INF);

        parent = new int[V];
    }

    public void addEdge(int u, int v) {
        AdjList[u].add(v);
        AdjList[v].add(u);
    }

    public int calcSp(int s, int destination) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(s);
        dist[s] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : AdjList[u]) {
                if (dist[v] == INF) {
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                    q.offer(v);
                }
            }
        }

        printpath(destination, s);

        return dist[destination];
    }

    private void printpath(int u, int s) {
        if (u == s) {
            System.out.printf("%d", u);
            return;
        }
        printpath(parent[u], s);
        System.out.printf(" %d", u);
    }

}
