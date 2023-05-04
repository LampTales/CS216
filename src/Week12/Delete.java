package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Delete {
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
        String oriStr = Reader.next();
        char[] str = oriStr.toCharArray();
        int[][] min = new int[n][n];
        for (int i = 0; i < n; i++) {
            min[i][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                min[j][i] = min[j + 1][i] + 1;
                if (str[i] == str[j]) {
                    min[j][i] = min[j + 1][i];
                }
                for (int k = i - 1; k > j; k--) {
                    if (str[k] == str[j]) {
                        min[j][i] = Math.min(min[j][i], min[j + 1][k] + min[k + 1][i]);
                    }
                }
            }
        }
        System.out.println(min[0][n - 1]);
    }
}
