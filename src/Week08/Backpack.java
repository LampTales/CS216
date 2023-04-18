package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backpack {
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
    static int maxSize = 44000;
    static int[] F = new int[maxSize];
    static int[] V = new int[maxSize];
    static int[] W = new int[maxSize];

    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();
        int c = Reader.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int s = Reader.nextInt();
            int w = Reader.nextInt();
            int v = Reader.nextInt();
            for (int j = 1; j <= s; j *= 2) {
                V[cnt] = v * j;
                W[cnt] = w * j;
                s -= j;
                cnt++;
            }
            if (s != 0) {
                V[cnt] = v * s;
                W[cnt] = w * s;
                cnt++;
            }
        }
        for (int i = 0; i < cnt; i++) {
            for (int j = c; j >= W[i]; j--) {
                F[j] = Math.max(F[j], F[j - W[i]] + V[i]);
            }
        }
        System.out.println(F[c]);
    }
}
