package Week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
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
        int k = Reader.nextInt();
        if (n % 2 == 0) {
            int x = Math.min(k, n + 1 - k);
            System.out.print(2 * x);
        } else {
            if (k == (n / 2) + 1) {
                System.out.print(n);
            } else {
                int y = Math.min(k, n + 1 - k);
                System.out.print(2 * y);
            }
        }
    }
}
