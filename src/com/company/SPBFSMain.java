package com.company;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Welly on 8/9/2016.
 */
public class SPBFSMain {
    public static void main(String[] args) throws Exception {
        File f = new File("in_04.txt");
        Scanner sc = new Scanner(f);

        int V = sc.nextInt();
        int E = sc.nextInt();

        ShortestPathBFS bfs = new ShortestPathBFS(V);

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            bfs.addEdge(a, b);
        }

        System.out.println(bfs.calcSp(5, 7));


    }
}
