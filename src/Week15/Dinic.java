package Week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Dinic {
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

        static long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

    static Edge[] edges = null;
    static Node[] nodes = null;
    static int s = 0;
    static int t = 0;

    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        s = Reader.nextInt() - 1;
        t = Reader.nextInt() - 1;

        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }


        edges = new Edge[2 * m];

        int edgeCnt = 0;
        for (int i = 0; i < m; i++) {
            int u = Reader.nextInt() - 1;
            int v = Reader.nextInt() - 1;
            long cap = Reader.nextLong();
            edges[edgeCnt] = new Edge(edgeCnt, v, nodes[u].head, cap);
            nodes[u].head = edgeCnt;
            edgeCnt++;
            edges[edgeCnt] = new Edge(edgeCnt, u, nodes[v].head, 0);
            nodes[v].head = edgeCnt;
            edgeCnt++;
        }

        long ans = 0;
        while (bfs()) {
            long flow = dfs(nodes[s], Long.MAX_VALUE);
            while (flow != 0) {
                ans += flow;
                flow = dfs(nodes[s], Long.MAX_VALUE);
            }
        }
        System.out.println(ans);
    }

    static boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();

        for (Node node : nodes) {
            node.dep = -1;
            node.now = node.head;
        }
        queue.add(s);
        nodes[s].dep = 0;
        while (!queue.isEmpty()) {
            Node node = nodes[queue.poll()];
            for (int i = node.head; i != -1; i = edges[i].next) {
                Edge edge = edges[i];
                if (nodes[edge.to].dep == -1 && edge.cap != 0) {
                    nodes[edge.to].dep = node.dep + 1;
                    queue.add(edge.to);
                }
            }
        }
        return nodes[t].dep != -1;
    }

    static long dfs(Node node, long limit) {
        if (node == nodes[t] || limit == 0) {
            return limit;
        }

        for (int i = node.now; i != -1; i = edges[i].next) {
            node.now = i;
            Edge edge = edges[i];
            Node son = nodes[edge.to];
            if (son.dep == node.dep + 1 && edge.cap != 0) {
                long ans = dfs(son, Math.min(edge.cap, limit));
                if (ans != 0) {
                    edge.cap -= ans;
                    edges[i^1].cap += ans;
                    return ans;
                }
            }
        }
        return 0;
    }

    static class Node {
        int id;
        int head;
        int now;
        int dep;

        Node(int id) {
            this.id = id;
            this.head = -1;
            this.now = 0;
            this.dep = -1;
        }
    }

    static class Edge {
        int id;
        int to;
        int next;
        long cap;

        Edge(int id, int to, int next, long cap) {
            this.id = id;
            this.to = to;
            this.next = next;
            this.cap = cap;
        }
    }

}
