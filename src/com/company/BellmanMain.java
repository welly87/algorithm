package com.company;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Welly on 8/9/2016.
 */
public class BellmanMain {
    public static void main(String[] args) throws Exception {
        File f = new File("in_06.txt");
        Scanner sc = new Scanner(f);

        int V = sc.nextInt();
        int E = sc.nextInt();
        int s = sc.nextInt();

        ShortestPathBellman bellman = new ShortestPathBellman(V);

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            bellman.addEdge(a, b, w);
        }

        bellman.calcSp(s);
    }
}
