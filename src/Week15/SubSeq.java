package Week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class SubSeq {
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
    static int edgeCnt = 0;

    public static void main(String[] args) throws IOException {
        int n = Reader.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Reader.nextInt();
            dp[i] = 1;
        }
        long[] caps = new long[n];
        for (int i = 0; i < n; i++) {
            caps[i] = Reader.nextInt();
        }

        int len = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[i] <= dp[j] && arr[j] <= arr[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            len = Math.max(len, dp[i]);
        }


        nodes = new Node[2 * n + 2];
        s = 2 * n;
        t = 2 * n + 1;
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = nodes[i] = new Node(i);
        }

        edges = new Edge[6 * n];
        for (int i = 0; i < n; i++) {
            addEdge(i * 2, i * 2 + 1, caps[i]);
            if (dp[i] == 1) {
                addEdge(s, i * 2, caps[i]);
            }
            if (dp[i] == len) {
                addEdge(i * 2 + 1, t, caps[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] <= arr[j] && dp[j] == dp[i] + 1) {
                    addEdge(i * 2 + 1, j * 2, caps[i]);
                }
            }
        }

        long ans = 0;
        while (bfs()) {
            long flow = dfs(nodes[s], Long.MAX_VALUE);
            while (flow != 0) {
                ans += flow;
                flow = dfs(nodes[s], Long.MAX_VALUE);
            }
        }

        System.out.println(len);
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

    static void addEdge(int u, int v, long cap) {
        edges[edgeCnt] = new Edge(edgeCnt, v, nodes[u].head, cap);
        nodes[u].head = edgeCnt;
        edgeCnt++;
        edges[edgeCnt] = new Edge(edgeCnt, u, nodes[v].head, 0);
        nodes[v].head = edgeCnt;
        edgeCnt++;
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
