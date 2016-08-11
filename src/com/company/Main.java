package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Welly on 8/8/2016.
 */
public class Main {

    static ArrayList<Integer>[] AdjList;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noCase = sc.nextInt();
        String l = sc.nextLine();
        System.out.println(l);


        for (int i = 0; i < noCase; i++) {

            int noVertices = sc.nextLine().charAt(0) - 'A' + 1;

            AdjList = (ArrayList<Integer>[]) new ArrayList[noVertices];

            for (int j = 0; j < noVertices; j++) {
                AdjList[j] = new ArrayList<>();
            }

            visited = new boolean[noVertices];

            int count = 0;

            while (true) {
                String line = sc.nextLine();

                if (line.isEmpty()) break;

                int from = line.charAt(0) - 'A';
                int to = line.charAt(1) - 'A';

                AdjList[from].add(to);
                AdjList[to].add(from);
            }
// print graph

//            for (int j = 0; j < noVertices; j++) {
//                System.out.print(j + " => ");
//
//                for (int v : AdjList[j]) {
//                    System.out.print(v + " ");
//                }
//                System.out.println();
//            }

            for (int j = 0; j < noVertices; j++) {
                if (!visited[j]) {
                    dfs(j);
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static void dfs(int i) {
        visited[i] = true;

        for (int v : AdjList[i]) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }
}
