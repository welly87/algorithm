package company;

import com.company.mst.IntegerPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Welly on 8/9/2016.
 */
public class ShortestPathDijsktra {

    ArrayList<IntegerPair>[] AdjList;
    int V;
    static final int INF = 10000000;

    ShortestPathDijsktra(int V) {
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

        PriorityQueue<IntegerPair> pq = new PriorityQueue<>(1, new Comparator<IntegerPair>() {
            @Override
            public int compare(IntegerPair i, IntegerPair j) {
                return i.first() - j.first();
            }
        });

        pq.offer(new IntegerPair(0, source));

        while (!pq.isEmpty()) {
            IntegerPair top = pq.poll();
            int d = top.first();
            int u = top.second();
            if (d > dist[u]) continue;

            for (IntegerPair p : AdjList[u]) {
                int v = p.first();
                int weight_u_v = p.second();
                if (dist[u] + weight_u_v < dist[v]) { // if can relax      (note: Record SP spanning tree)
                    dist[v] = dist[u] + weight_u_v; // relax                  (here if needed. This is similar)
                    pq.offer(new IntegerPair(dist[v], v)); //      (as printpath in BFS)
                    // enqueue this neighbor regardless whether it is already in pq or not
                }
            }
        }

        for (int i = 0; i < V; i++) // index + 1 for final answer
            System.out.printf("SSSP(%d, %d) = %d\n", source + 1, i + 1, dist[i]);

        return 0;
    }
}
