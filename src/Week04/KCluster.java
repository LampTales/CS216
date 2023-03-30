package Week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KCluster {
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
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(i, Reader.nextInt(), Reader.nextInt());
        }
        Edge[] edges = new Edge[n * (n - 1) / 2];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point a = points[i];
                Point b = points[j];
                edges[cnt] = new Edge(a, b, Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2)));
                cnt++;
            }
        }
        Arrays.sort(edges);

        DisjointSet set = new DisjointSet(n);
        int total = n;
        cnt = 0;
        while (total > k) {
            Edge e = edges[cnt];
            if (set.find(e.a.id) != set.find(e.b.id)) {
                set.arr[set.find(e.a.id)] = set.find(e.b.id);
                total--;
            }
            cnt++;
        }

        while (true) {
            Edge e = edges[cnt];
            if (set.find(e.a.id) != set.find(e.b.id)) {
                System.out.printf("%.2f\n", e.dis);
                return;
            }
            cnt++;
        }
    }

    static class Point {
        int id;
        int x;
        int y;

        public Point(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        Point a;
        Point b;
        double dis;

        public Edge(Point a, Point b, double dis) {
            this.a = a;
            this.b = b;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.dis == o.dis) {return 0;}
            return this.dis > o.dis ? 1 : -1;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "a=" + a.id +
                    ", b=" + b.id +
                    ", dis=" + dis +
                    '}';
        }
    }

    static class DisjointSet {
        int[] arr;
        DisjointSet(int n) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = -1;
            }
        }

        int find(int x) {
            if (arr[x] != -1) {
                int c = find(arr[x]);
                arr[x] = c;
                return c;
            } else {return x;}
        }

        void join(int a, int b) {
            int ap = find(a);
            int bp = find(b);
            if (ap != bp) {
                arr[ap] = bp;
            }
        }
    }
}
