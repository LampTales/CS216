package Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DMST {
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

    static int INF = 2147483647;

    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();
        Node[] nodes = new Node[n + 1];
        int m = Reader.nextInt();
        Edge[] edges = new Edge[m + n];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(Reader.nextInt(), Reader.nextInt(), Reader.nextInt());
        }
        for (int i = 0; i < n; i++) {
            edges[m + i] = new Edge(n, i, INF);
        }
        int root = n;
        m = m + n;
        n = n + 1;

        long ans = 0;
        int ori = -1;

        while (true) {
            for (int i = 0; i < n; i++) {
                nodes[i].clean();
            }

            // find smallest enter edge
            for (int i = 0; i < m; i++) {
                Edge e = edges[i];
                if (e.from != e.to && (nodes[e.to].enter == null || nodes[e.to].enter.cost > e.cost)) {
                    nodes[e.to].enter = e;
                }
            }
            for (int i = 0; i < n; i++) {
                if (i != root && nodes[i].enter == null) {
                    System.out.println("impossible");
                    return;
                } else if (i != root){
                    nodes[i].minEnterCost = nodes[i].enter.cost;
                }
            }

            // detect circle and sum up cost
            int spc = -1;
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (i == root) {
                    continue;
                }
                ans += nodes[i].enter.cost;
                int x = i;
                Edge save = null;
                while (x != root && nodes[x].visit == -1) {
                    nodes[x].visit = i;
                    save = nodes[x].enter;
                    x = nodes[x].enter.from;
                }
                if (x == root) {
                    if (flag) {
                        System.out.println("impossible");
                        return;
                    } else {
                        flag = true;
                        ori = save.ori;
                    }
                }
                if (nodes[x].visit == i) {
                    spc++;
                    while (nodes[x].sp == -1) {
                        nodes[x].sp = spc;
                        x = nodes[x].enter.from;
                    }
                }
            }
            if (spc == -1) {
                break;
            }

            // relabel the nodes
            for (int i = 0; i < n; i++) {
                if (nodes[i].sp == -1) {
                    spc++;
                    nodes[i].sp = spc;
                }
            }

            //relabel the edges
            for (int i = 0; i < m; i++) {
                Edge e = edges[i];
                int oriTo = e.to;
                e.from = nodes[e.from].sp;
                e.to = nodes[e.to].sp;
                edges[i].cost -= nodes[oriTo].minEnterCost;
//                if (e.from != e.to) {
//                } else {
//                    m--;
//                    edges[i] = edges[m];
//                    i--;
//                }
            }

            n = spc + 1;
            root = nodes[root].sp;
        }
        ans -= INF;
        System.out.println(ans + " " + ori);
    }

    static class Node {
        Edge enter;
        int visit; // the path in which the node is visited
        int sp; // the node id for the next loop
        long minEnterCost;

        Node() {
            enter = null;
            visit = -1;
            sp = -1;
            minEnterCost = INF;
        }

        void clean() {
            enter = null;
            visit = -1;
            sp = -1;
            minEnterCost = INF;
        }
    }

    static class Edge {
        int from;
        int to;
        long cost;
        int ori;

        public Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
            this.ori = to;
        }
    }
}
