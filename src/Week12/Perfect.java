package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        Num[] nums = new Num[n - 1];
        for (int i = 0; i < n - 1; i++) {
            nums[i] = new Num(i + 2);
        }
        Arrays.sort(nums);

        int[][] dp = new int[256][256];
        dp[0][0] = 1;
        int[][] f1 = new int[256][256];
        int[][] f2 = new int[256][256];

        for (int i = 0; i < n - 1; i++) {
            if (i == 0 || nums[i].big != nums[i - 1].big || nums[i].big == 0) {
                for (int x = 0; x < 256; x++) {
                    System.arraycopy(dp[x], 0, f1[x], 0, 256);
                    System.arraycopy(dp[x], 0, f2[x], 0, 256);

                }
            }

            for (int x = 255; x >= 0; x--) {
                for (int y = 255; y >= 0; y--) {
                    if ((x & y) == 0) {
                        if ((nums[i].k & y) == 0) {
                            f1[x|nums[i].k][y] = (f1[x|nums[i].k][y] + f1[x][y]) % p;
                        }
                        if ((nums[i].k & x) == 0) {
                            f2[x][y|nums[i].k] = (f2[x][y|nums[i].k] + f2[x][y]) % p;
                        }
                    }
                }
            }

            if (i == n - 2 || nums[i].big != nums[i + 1].big || nums[i].big == 0) {
                for (int x = 0; x < 256; x++) {
                    for (int y = 0; y < 256; y++) {
                        if ((x & y) == 0) {
                            dp[x][y] = (f1[x][y] + (f2[x][y] + (p - dp[x][y])) % p) % p;
                        }
                    }
                }
            }


        }

        long ans = 0;
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                ans += dp[x][y];
            }
        }
        System.out.println(ans % p);
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
                    while (this.big % primes[i] == 0) {
                        this.big /= primes[i];
                    }
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
