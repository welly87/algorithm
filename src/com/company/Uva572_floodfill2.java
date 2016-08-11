package com.company;

import java.util.Scanner;

public class Uva572_floodfill2 {

    static int m, n;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
    static int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            m = sc.nextInt();
            n = sc.nextInt();

            if (m == 0 && n == 0) break;

            sc.nextLine();

            map = new char[m][n];

            for (int i = 0; i < m; i++) {
                String str = sc.nextLine();
                map[i] = str.toCharArray();
            }

            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == '@') {
                        count++;
                        floodfill(i, j);
                    }
                }
            }

            System.out.println(count);
        }


    }

    private static void floodfill(int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n) return;

        if (map[x][y] != '@') return;

        map[x][y] = '*';

        for (int i = 0; i < dx.length; i++) {
            for (int j = 0; j < dy.length; j++) {
                floodfill(x + dx[i], y + dy[j]);
            }
        }
    }
}
