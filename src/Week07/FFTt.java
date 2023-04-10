package Week07;

import java.io.*;
import java.util.StringTokenizer;

public class FFTt {
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

    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();

        int len = 1;
        int time = 0;
        while (len < 4 * n) {
            len = len << 1;
            time++;
        }

        Complex[] F = new Complex[len];
        Complex[] G = new Complex[len];
        for (int i = 0; i < len; i++) {
            F[i] = new Complex();
            G[i] = new Complex();
        }
        for (int i = 0; i < n; i++) {
            F[i].r = Reader.nextDouble();
        }
        for (int i = 0; i < n - 1; i++) {
            G[i].r = -1 / ((double)((n - 1 - i) * (n - 1 - i)));
        }
        for (int i = 0; i < n - 1; i++) {
            G[i + n].r = -G[n - 2 - i].r;
        }
//        System.out.println(Arrays.toString(G));
//        System.out.println(len);

        int[] rev = new int[len];
        for (int i = 0; i < len; i++) {
            rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (time - 1));
        }
        FFT(F, 1, rev);
        FFT(G, 1, rev);
        for (int i = 0; i < len; i++) {
            F[i] = F[i].mul(G[i]);
        }
        FFT(F, -1, rev);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            out.println(F[i + n - 1].r);
            out.flush();
        }

    }

    static void FFT(Complex[] com, int op, int[] rev) {
        for (int i = 0; i < com.length; i++) {
            if (i < rev[i]) {
                Complex temp = com[i];
                com[i] = com[rev[i]];
                com[rev[i]] = temp;
            }
        }

        for (int t = 1; t < com.length; t <<= 1) {
            Complex wn = new Complex(Math.cos(Math.PI * op / t), Math.sin(Math.PI * op / t));
            for (int i = 0; i < com.length; i += 2 * t) {
                Complex w = new Complex(1, 0);
                for (int j = 0; j < t; j++) {
                    Complex x = com[i + j];
                    Complex y =  w.mul(com[i + j + t]);
                    com[i + j] = x.add(y);
                    com[i + j + t] = x.sub(y);
                    w = w.mul(wn);
                }
            }
        }
        if (op == -1) {
            for (Complex complex : com) {
                complex.r = complex.r / com.length;
            }
        }
    }

    static class Complex {
        double r;
        double i;

        Complex(double r, double i) {
            this.r = r;
            this.i = i;
        }

        Complex() {
            this.r = 0;
            this.i = 0;
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

        @Override
        public String toString() {
            return String.format("%f + %fi", r, i);
        }
    }
}
