package company.mst;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Welly on 8/9/2016.
 */

class Edge implements Comparable {
    Integer _first, _second, _third;

    public Edge(Integer f, Integer s, Integer t) {
        _first = f;
        _second = s;
        _third = t;
    }

    public int compareTo(Object o) {
        if (!this.weight().equals(((Edge) o).weight()))
            return this.weight() - ((Edge) o).weight();
        else if (!this.u().equals(((Edge) o).u()))
            return this.u() - ((Edge) o).u();
        else
            return this.v() - ((Edge) o).v();
    }

    public Integer weight() {
        return _first;
    }

    public Integer u() {
        return _second;
    }

    public Integer v() {
        return _third;
    }

    public String toString() {
        return weight() + " " + u() + " " + v();
    }
}

public class Kruskal {

    ArrayList<Edge> EdgeList;
    int edgeCounter;
    int V;
    int E;


    public Kruskal(int V, int E) {
        EdgeList = new ArrayList<Edge>();
        this.V = V;
        this.E = E;
    }

    public void addEdge(int weight, int u, int v) {
        EdgeList.add(new Edge(weight, u, v));
    }

    public int calcMST() {
        Collections.sort(EdgeList);

        int mst_cost = 0;
        UnionFind uf = new UnionFind(V);
        for (Edge e : EdgeList) {
            if (!uf.isSameSet(e.u(), e.v())) {
                mst_cost += e.weight();
                uf.unionSet(e.u(), e.v());
            }
        }

        return mst_cost;
    }
}
