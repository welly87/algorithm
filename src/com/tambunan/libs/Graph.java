package com.tambunan.libs;

import java.util.ArrayList;

public class Graph {
    private int V; // number of vertices
    private int E; // number of edges
    private ArrayList<Integer>[] adj; // adjacency lists

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V]; // Create array of lists.
        for (int v = 0; v < V; v++) // Initialize all lists
            adj[v] = new ArrayList<Integer>(); // to empty.
    }

//    public Graph(In in) {
//        this(in.readInt()); // Read V and construct this graph.
//        int E = in.readInt(); // Read E.
//        for (int i = 0; i < E; i++) { // Add an edge.
//            int v = in.readInt(); // Read a vertex,
//            int w = in.readInt(); // read another vertex,
//            addEdge(v, w); // and add edge connecting them.
//        }
//    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v’s list.
        adj[w].add(v); // Add v to w’s list.
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}