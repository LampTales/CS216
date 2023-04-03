package Checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Closer {

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
        Point[] xRank = new Point[n];
        Point[] yRank = new Point[n];
        for (int i = 0; i < n; i++) {
            Point p = new Point(Reader.nextInt(), Reader.nextInt());
            xRank[i] = p;
            yRank[i] = p;
        }
        Arrays.sort(xRank, Closer::xCompare);
        Arrays.sort(yRank, Comparator.comparingInt(o -> o.y));
//        System.out.println(Arrays.toString(xRank));
//        System.out.println(Arrays.toString(yRank));
        System.out.println(getMin(xRank, 0, n - 1, yRank));
    }

    static long getMin(Point[] xRank, int xStart, int xEnd, Point[] yRank) {
        if (xEnd - xStart + 1 <= 1) {
            return 80000000000L;
        }
        if (xEnd - xStart + 1 == 2) {
//            System.out.println("From the 111: " + distance(xRank[xStart], xRank[xEnd]));
            return distance(xRank[xStart], xRank[xEnd]);
        }
        if (xEnd - xStart + 1 == 3) {
//            System.out.println("From the 222: " + Math.min(Math.min(distance(xRank[xStart], xRank[xStart + 1]), distance(xRank[xStart], xRank[xEnd])), distance(xRank[xStart + 1], xRank[xEnd])));
            return Math.min(Math.min(distance(xRank[xStart], xRank[xStart + 1]), distance(xRank[xStart], xRank[xEnd])), distance(xRank[xStart + 1], xRank[xEnd]));
        }
        int leftLen = (xEnd - xStart + 1) / 2;
        int rightLen = xEnd - xStart + 1 - leftLen;
        Point[] leftY = new Point[leftLen], rightY = new Point[rightLen];
        int l = 0, r = 0;
        Point div = xRank[xStart + leftLen - 1];
        for (int i = 0; i < xEnd - xStart + 1; i++) {
            if (xCompare(yRank[i], div) <= 0) {
                leftY[l] = yRank[i];
                l++;
            } else {
                rightY[r] = yRank[i];
                r++;
            }
        }
        long leftMin = getMin(xRank, xStart, xStart + leftLen - 1, leftY);
        long rightMin = getMin(xRank, xStart + leftLen, xEnd, rightY);
        long totalMin = Math.min(leftMin, rightMin);

        int cnt = 0;
        for (int i = 0; i < xEnd - xStart + 1; i++) {
            if (((long)(yRank[i].x - div.x)) * (yRank[i].x - div.x) < totalMin) {
                yRank[cnt] = yRank[i];
                cnt++;
            }
        }
        for (int i = 0; i < cnt; i++) {
            for (int j = i + 1; j <= i + 7 && j < cnt; j++) {
                long dis = distance(yRank[i], yRank[j]);
                totalMin = Math.min(dis, totalMin);
            }
        }
//        System.out.println("From the end: " + totalMin);
        return totalMin;
    }

    static long distance(Point a, Point b) {
        return ((long)(a.x - b.x)) * (a.x - b.x) + ((long)(a.y - b.y)) * (a.y - b.y);
    }

    static int xCompare(Point o1, Point o2) {
        if (o1.x == o2.x) {
            return o1.y - o2.y;
        } else {
            return o1.x - o2.x;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
