package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Perfect {
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
        int p = Reader.nextInt();


    }

    static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19};

    static class Num implements Comparable<Num>{
        int value;
        int big;
        int k;

        Num (int num) {
            this.value = num;
            this.k = 0;
            this.big = num;
            for (int i = 0; i < 8; i++) {
                if (num % primes[i] == 0) {
                    this.k |= (1 << i);
                    this.big /= primes[i];
                }
            }
            if (this.big == 1) {
                this.big = 0;
            }
        }

        @Override
        public int compareTo(Num o) {
            return this.big - o.big;
        }
    }
}
