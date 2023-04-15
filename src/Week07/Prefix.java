package Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prefix {
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
        String str = Reader.next();

        int n =str.length();
        int len = 1;
        int time = 0;
        while (len < 2 * n) {
            len = len << 1;
            time++;
        }
        int[] rev = new int[len];
        for (int i = 0; i < len; i++) {
            rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (time - 1));
        }

        int[] F = new int[len];
        int[] G = new int[len];
        for (int i = 0; i < n; i++) {
            switch (str.charAt(i)) {
                case '?':
                    F[i] = 0;
                    G[n - 1 - i] = 0;
                    break;
                case '0':
                    F[i] = 1;
                    G[n - 1 - i] = 1;
                    break;
                case '1':
                    F[i] = 2;
                    G[n - 1 - i] = 2;
                    break;
            }
        }

        Complex[] fc = new Complex[len];
        Complex[] gc = new Complex[len];
        for (int i = 0; i < len; i++) {
            fc[i] = new Complex();
            gc[i] = new Complex();
        }
        double[] H = new double[n / 2];

        for (int i = 0; i < len; i++) {
            fc[i].r = F[i] * F[i] * F[i];
//            fc[i].i = 0;
            gc[i].r = G[i];
//            gc[i].i = 0;
        }
        FFT(fc, 1, rev);
        FFT(gc, 1, rev);
        for (int i = 0; i < len; i++) {
            fc[i] = fc[i].mul(gc[i]);
        }
        FFT(fc, -1, rev);
        for (int i = 0; i < H.length; i++) {
            H[i] += fc[i].r;
        }

        for (int i = 0; i < len; i++) {
            fc[i].r = F[i] * F[i];
            fc[i].i = 0;
            gc[i].r = -2 * G[i] * G[i];
            gc[i].i = 0;
        }
        FFT(fc, 1, rev);
        FFT(gc, 1, rev);
        for (int i = 0; i < len; i++) {
            fc[i] = fc[i].mul(gc[i]);
        }
        FFT(fc, -1, rev);
        for (int i = 0; i < H.length; i++) {
            H[i] += fc[i].r;
        }

        for (int i = 0; i < len; i++) {
            fc[i].r = F[i];
            fc[i].i = 0;
            gc[i].r = G[i] * G[i] * G[i];
            gc[i].i = 0;
        }
        FFT(fc, 1, rev);
        FFT(gc, 1, rev);
        for (int i = 0; i < len; i++) {
            fc[i] = fc[i].mul(gc[i]);
        }
        FFT(fc, -1, rev);
        for (int i = 0; i < H.length; i++) {
            H[i] += fc[i].r;
        }

        long ans = isZero(H[0]) ? 1 : 0;
        for (int i = 1; i < H.length; i++) {
            ans = ans ^ ((isZero(H[i]) ? 1 : 0) * ((long)(i + 1)) * ((long)(i + 1)));
        }
        System.out.println(ans);
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

    static boolean isZero(double d) {
        return Math.abs(d) < 0.25;
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
