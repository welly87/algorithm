package company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Welly on 8/8/2016.
 */
public class Uva315_articulation_point_and_bridge {
    static ArrayList<Integer>[] AdjList;
    static final int UNVISITED = 1;
    static int dfsNumberCounter = 0;
    static int[] dfs_num;
    static int[] dfs_low;
    static int[] dfs_parent;
    static boolean[] articulation_vertex;

    static int dfsRoot;
    static int rootChildren;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            int noV = sc.nextInt();
            sc.nextLine();

            AdjList = (ArrayList<Integer>[]) new ArrayList[noV + 1];
            for (int j = 0; j < noV + 1; j++) {
                AdjList[j] = new ArrayList<>();
            }

            dfsNumberCounter = 0;
            dfs_num = new int[noV + 1];
            Arrays.fill(dfs_num, UNVISITED);

            dfs_low = new int[noV + 1];
            Arrays.fill(dfs_low, 0);

            dfs_parent = new int[noV + 1];
            Arrays.fill(dfs_parent, 0);

            articulation_vertex = new boolean[noV + 1];
            Arrays.fill(articulation_vertex, false);


            while (true) {
                String line = sc.nextLine();
//                System.out.println(line);
                if (line.equals("0")) break;

                String[] nodes = line.split(" ");
                int centralNode = Integer.parseInt(nodes[0]);

                for (int i = 1; i < nodes.length; i++) {
                    int otherNode = Integer.parseInt(nodes[i]);
                    AdjList[centralNode].add(otherNode);
                    AdjList[otherNode].add(centralNode);
                }
            }

            for (int i = 1; i <= noV; i++) {
                if (dfs_num[i] == UNVISITED) {
                    dfsRoot = i;
                    rootChildren = 0;
                    articulationPointAndBridge(i);
                    articulation_vertex[dfsRoot] = (rootChildren > 1);
                }
            }

            for (int i = 1; i <= noV; i++) {
                if (articulation_vertex[i]) {
                    System.out.println(i);
                }
            }

        }
    }

    private static void articulationPointAndBridge(int u) {
        dfs_low[u] = dfs_num[u] = dfsNumberCounter++;

        for (int v : AdjList[u]) {
            if (dfs_num[v] == UNVISITED) {
                dfs_parent[v] = u;
                if (u == dfsRoot) rootChildren++;

                articulationPointAndBridge(v);

                if (dfs_low[v] >= dfs_num[u])
                    articulation_vertex[u] = true;
                if (dfs_low[v] > dfs_num[u])
                    System.out.println("Bridge " + u + "-" + v);
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
            } else if (v != dfs_parent[u]) {
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
            }
        }
    }
}
