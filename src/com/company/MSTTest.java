package com.company;

import com.company.mst.Kruskal;
import com.company.mst.Prim;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Welly on 8/9/2016.
 */
public class MSTTest {


    public static void main(String[] args) throws Exception {


        File f = new File("in_03.txt");
        Scanner sc = new Scanner(f);

        int V = sc.nextInt();
        int E = sc.nextInt();

        Kruskal k = new Kruskal(V, E);
        Prim p = new Prim(V, E);

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            k.addEdge(w, u, v);
            p.addEdge(u, v, w);
        }

        System.out.println(k.calcMST());
        System.out.println(p.calcMST());
    }
}
