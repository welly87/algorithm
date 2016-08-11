package company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Welly on 8/8/2016.
 */
public class Uva10004_bipartite {
    static ArrayList<Integer>[] AdjList;
    static int[] color;
    static boolean isBipartite;
    static final int INF = 10000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            int n = sc.nextInt();
            if (n == 0) break;

            int l = sc.nextInt();

            AdjList = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                AdjList[i] = new ArrayList<>();
            }

            color = new int[n];

            Arrays.fill(color, INF);

            for (int i = 0; i < l; i++) {
                int src = sc.nextInt();
                int to = sc.nextInt();

                AdjList[src].add(to);
                AdjList[to].add(src);
            }

//            Queue<Integer> queue = new LinkedList<>();
//            queue.offer(0);
            isBipartite = true;
            color[0] = 0;
            dfs(0, 0);
//            boolean isBipartite = true;
//            while (!queue.isEmpty() & isBipartite) {
//                int u = queue.poll();
//
//                for (int v : AdjList[u]) {
//                    if (color[v] == INF) {
//                        color[v] = 1 - color[u];
//                        queue.offer(v);
//                    } else if (color[v] == color[u]) {
//                        isBipartite = false;
//                        break;
//                    }
//                }
//            }

            System.out.println(isBipartite ? "BICOLORABLE." : "NOT BICOLORABLE.");
        }
    }

    private static void dfs(int v, int clr) {

//        if (!isBipartite) return;

        color[v] = clr;

        for (int u : AdjList[v]) {
            if (color[u] == INF) {
                // coloring
                dfs(u, 1 - clr);
            } else if (color[v] == color[u]) {
                isBipartite = false;
                return;
            }
        }
    }
}
