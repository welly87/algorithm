package company.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Welly on 8/9/2016.
 */
public class Prim {
    ArrayList<IntegerPair>[] AdjList;
    boolean[] taken; // global boolean flag to avoid cycle
    PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>(); // priority queue to help choose shorter edges

    public Prim(int V, int E) {
        AdjList = (ArrayList<IntegerPair>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            AdjList[i] = new ArrayList<>();
        }

        taken = new boolean[V];
        Arrays.fill(taken, false);
    }

    public void addEdge(int u, int v, int w) {
        AdjList[u].add(new IntegerPair(v, w));
        AdjList[v].add(new IntegerPair(u, w));
    }

    public int calcMST() {
        int mst_cost = 0;

        process(0);
        while (!pq.isEmpty()) {
            IntegerPair front = pq.poll();
//            pq.poll();

            int u = front.second();
            int w = front.first();

            if (!taken[u]) {
                mst_cost += w;
                process(u);
            }
        }

        return mst_cost;
    }

    private void process(int vtx) { //  we do not need to use -ve sign to reverse the sort order
        taken[vtx] = true;
        for (IntegerPair v : AdjList[vtx]) {
            if (!taken[v.first()]) {
                pq.offer(new IntegerPair(v.second(), v.first()));
            }
        }
    }
}
