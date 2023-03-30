package Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Chess {
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
    static int[][] goal_board = {
            {-1, -1, -1, -1, -1},
            {1, -1, -1, -1, -1},
            {1, 1, 0, -1, -1},
            {1, 1, 1, 1, -1},
            {1, 1, 1, 1, 1}
    };
    static int min;

    public static void main(String[] args) throws IOException {
        int T = Reader.nextInt();
        for (int caseCnt = 0; caseCnt < T; caseCnt++) {
            code();
        }
    }

    public static void code() throws IOException {
        int[][] chessBoard = new int[5][5];
        min = 15;
        int spacex = 0;
        int spacey = 0;
        for (int i = 0; i < 5; i++) {
            String str = Reader.next();
            for (int j = 0; j < 5; j++) {
                switch(str.charAt(j)) {
                    case '1':
                        chessBoard[i][j] = -1;
                        break;
                    case '0':
                        chessBoard[i][j] = 1;
                        break;
                    case '*':
                        chessBoard[i][j] = 0;
                        spacex = i;
                        spacey = j;
                        break;
                }
            }
        }
        dfs(chessBoard, spacex, spacey, -1, -1, 0);
        if (min < 15) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }

    public static void dfs(int[][] chessBoard, int spacex, int spacey, int prex, int prey, int step) {
        if (goal(chessBoard)) {
            min = Math.min(min, step);
            return;
        }
        if (step >= min) {
            return;
        }
        for (int[] dir : directions) {
            int x = dir[0] + spacex;
            int y = dir[1] + spacey;
            if (x >= 0 && x < 5 && y >= 0 && y < 5 && !(x == prex && y == prey)) {
                chessBoard[spacex][spacey] = chessBoard[x][y];
                chessBoard[x][y] = 0;
                dfs(chessBoard, x, y, spacex, spacey, step + 1);
                chessBoard[x][y] = chessBoard[spacex][spacey];
                chessBoard[spacex][spacey] = 0;
            }
        }
    }

    public static void bfs(int[][] chessBoard, int spacex, int spacey, int prex, int prey, int step) {

    }

    public static boolean goal(int[][] chessBoard) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (goal_board[i][j] != chessBoard[i][j]) {return false;}
            }
        }
        return true;
    }
}