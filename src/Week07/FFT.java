package Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class FFT {
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

        static double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }

//    static void FFT(Complex[] arr, )

    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();

        int len = 1;
        int time = 0;
        while (len < 2 * n) {
            len = len << 1;
            time++;
        }

        double[] F = new double[len];
        double[] G = new double[len];
        for (int i = 0; i < n; i++) {
            F[i] = Reader.nextDouble();
        }
        for (int i = 0; i < n - 1; i++) {
            G[i] = -1 / ((double)((n - i) * (n - i)));
        }

        int[] rev = new int[len];
        for (int i = 0; i < len; i++) {
            rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (time - 1));
        }



    }

    static class Complex {
        double r;
        double i;

        Complex(double r, double i) {
            this.r = r;
            this.i = i;
        }

        Complex neg() {
            return new Complex(-this.r, -this.i);
        }

        Complex add(Complex com) {
            return new Complex(this.r + com.r, this.i + com.i);
        }

        Complex sub(Complex com) {
            return new Complex(this.r - com.r, this.i - com.i);
        }

        Complex mul(Complex com) {
            return new Complex(this.r * com.r - this.i * com.i, this.i * com.r +this.r * com.i);
        }
    }
}
