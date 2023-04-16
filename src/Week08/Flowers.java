package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Flowers {
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

    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int k = Reader.nextInt();
        int[][][][] ans = new int[m + n + 1][n + 1][k + 1][k + 1];
        int mod = 12345678;
        ans[0][0][0][0] = 1;
        for (int a = 0; a < m + n; a++) {
            for (int b = 0; b <= n; b++) {
                for (int c = 0; c <= k; c++) {
                    for (int d = 0; d <= k; d++) {
                        if (ans[a][b][c][d] != 0) {
                            if (b < n && c < k) {
                                ans[a + 1][b + 1][c + 1][Math.max(d - 1, 0)] = (ans[a + 1][b + 1][c + 1][Math.max(d - 1, 0)] + ans[a][b][c][d]) % mod;
                            }
                            if (c < m && d < k) {
                                ans[a + 1][b][Math.max(c - 1, 0)][d + 1] = (ans[a + 1][b][Math.max(c - 1, 0)][d + 1] + ans[a][b][c][d]) % mod;
                            }
                        }

                    }
                }
            }
        }
        long result = 0;
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= k; j++) {
                result = (result + ans[n + m][n][i][j]) % mod;
            }
        }
        System.out.println(result);
    }
}
