package Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ChessBeta {
    static class Reader {
        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokenizer = new StringTokenizer("");

        static String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        static int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    static int[][] directions = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
    static int[] goal_board = {
            1, 1, 1, 1, 1,
            0, 1, 1, 1, 1,
            0, 0, 2, 1, 1,
            0, 0, 0, 0, 1,
            0, 0, 0, 0, 0};
    static long[] hashtable = new long[25];
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        int T = Reader.nextInt();
        hashtable[0] = 1;
        for (int i = 1; i < 25; i++) {
            hashtable[i] = hashtable[i - 1] * 3;
        }
        ans = hash(goal_board);
        for (int caseCnt = 0; caseCnt < T; caseCnt++) {
            code();
        }
    }

    public static void code() throws IOException {
        int[] chessBoard = new int[25];
        int spacex = 0;
        int spacey = 0;
        for (int i = 0; i < 5; i++) {
            String str = Reader.next();
            for (int j = 0; j < 5; j++) {
                switch(str.charAt(j)) {
                    case '1':
                        chessBoard[i* 5 + j] = 1;
                        break;
                    case '0':
                        chessBoard[i* 5 + j] = 0;
                        break;
                    case '*':
                        chessBoard[i* 5 + j] = 2;
                        spacex = i;
                        spacey = j;
                        break;
                }
            }
        }
        Node start = new Node(chessBoard,spacex,spacey,-1,-1,0);
        Node end = start;
        HashSet<Long> map = new HashSet<>();


        while (start != null) {
            if (start.step > 15) {
                break;
            }
            long curHash = hash(start.chessboard);
            if (curHash == ans) {
                System.out.println(start.step);
                return;
            }
            if (map.contains(curHash)) {
                start = start.next;
                continue;
            }
            map.add(curHash);
            for (int[] dir : directions) {
                int x = dir[0] + start.spacex;
                int y = dir[1] + start.spacey;
                if (x >= 0 && x < 5 && y >= 0 && y < 5 && !(x == start.prex && y == start.prey)) {
                    chessBoard = start.chessboard.clone();
                    chessBoard[start.spacex * 5 + start.spacey] = chessBoard[x * 5 + y];
                    chessBoard[x * 5 + y] = 2;
                    end.next = new Node(chessBoard, x, y, start.spacex, start.spacey, start.step + 1);
                    end = end.next;
                }
            }
            start = start.next;
        }
        System.out.println(-1);
    }

    static long hash(int[] chessboard) {
        long ans = 0;
        for (int i = 0; i < 25; i++) {
            ans += chessboard[i] * hashtable[i];
        }
        return ans;
    }

    static class Node {
        Node next;
        int[] chessboard;
        int spacex, spacey, prex, prey, step;

        public Node(int[] chessboard, int spacex, int spacey, int prex, int prey, int step) {
            this.next = null;
            this.chessboard = chessboard;
            this.spacex = spacex;
            this.spacey = spacey;
            this.prex = prex;
            this.prey = prey;
            this.step = step;
        }
    }
}
