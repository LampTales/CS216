package Checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DMST {

    static int INF = 2147483647;

    public static void code() throws IOException {
        InOutReader Reader = new InOutReader();
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
                    InOutWriter.writeOutput("impossible\n");
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
                while (x != root && nodes[x].visit == -1) {
                    nodes[x].visit = i;
                    x = nodes[x].enter.from;
                }
                if (x == root && flag) {
                    InOutWriter.writeOutput("impossible\n");
                    return;
                } else {
                    flag = true;
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
                if (e.from != e.to) {
                    edges[i].cost -= nodes[oriTo].minEnterCost;
                } else {
                    m--;
                    edges[i] = edges[m];
                    i--;
                }
            }

            n = spc + 1;
            root = nodes[root].sp;
        }
        ans -= INF;
        for (int i = 0; i < m; i++) {
            if (edges[i].from == root) {
                InOutWriter.writeOutput(String.format("%d %d\n",ans, edges[i].ori));
                return;
            }
        }
    }

    static class Node {
        Edge enter;
        int visit; // the path in which the node is visited
        int sp; // the node id for the next loop
        int minEnterCost;

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
        int cost;
        int ori;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
            this.ori = to;
        }
    }
}
