package com.company;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Welly on 8/9/2016.
 */
public class DijstraMain {
    public static void main(String[] args) throws Exception {
        File f = new File("in_05.txt");
        Scanner sc = new Scanner(f);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int source = sc.nextInt();

        ShortestPathDijsktra dj = new ShortestPathDijsktra(V);

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            dj.addEdge(u, v, w);
        }

        dj.calcSp(source);
    }
}
