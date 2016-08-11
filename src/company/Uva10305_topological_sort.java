package company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Welly on 8/8/2016.
 */
public class Uva10305_topological_sort {

    private static ArrayList<Integer>[] AdjList;
    private static boolean[] visited;
    private static ArrayList<Integer> topological;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        AdjList = (ArrayList<Integer>[]) new ArrayList[n + 1];
        for (int j = 0; j < n + 1; j++) {
            AdjList[j] = new ArrayList<>();
        }

        visited = new boolean[n + 1];
        topological = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            AdjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            AdjList[from].add(to);
        }

        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        for (int i = topological.size() - 1; i >= 0; i--) {
            System.out.print(" " + topological.get(i));
        }
    }

    private static void dfs(int i) {
        visited[i] = true;

        for (int v : AdjList[i]) {
            if (!visited[v]) {
                dfs(v);
            }
        }

        topological.add(i);
    }
}
