package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FindPath {
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
        int n = Reader.nextInt() + 1;
        int m = Reader.nextInt();
        Node[] nodes = new Node[n];
        Edge[] edges = new Edge[m];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(nodes[Reader.nextInt()], nodes[Reader.nextInt()], Reader.nextInt());
        }
        for (int i = 0; i < m; i++) {
            edges[i].cost = Reader.nextInt();
            edges[i].from.appendEdge(edges[i]);
            edges[i].to.appendEdge(new Edge(edges[i]));
        }
        for (int i = 1; i < n; i++) {
            nodes[0].appendEdge(new Edge(nodes[0], nodes[i], 0));
        }

        double precision = 0.01;
        double min = 0;
        double max = 200;

        while (max - min > precision) {
            double mid = (max + min) / 2;
            if (hasNegative(nodes, mid)) {
                min = mid;
            } else {
                max = mid;
            }
        }
        if (min == 0) {
            System.out.println(-1);
        } else {
            System.out.println((max + min) / 2);
        }
    }

    static boolean hasNegative(Node[] nodes, double best) {
        int n = nodes.length;
        Node.fresh(nodes);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(nodes[0]);
        nodes[0].dis = 0;
        nodes[0].inq = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.inq = false;
            for (Edge edge : node.edgeList) {
//                if (node != edge.from) {
//                    System.out.println("ERR!");
//                }
                Node son = edge.to;
                if (son != node.pre) {
                    if (son.dis > node.dis + best * edge.cost - edge.value) {
                        son.dis = node.dis + best * edge.cost - edge.value;
                        son.pre = node;
                        son.cnt = node.cnt + 1;
                        if (son.cnt >= n) {
                            return true;
                        }
                        if (!son.inq) {
                            queue.offer(son);
                            son.inq = true;
                        }
                    }
                }
            }
        }
        return false;
    }


    static class Edge {
        Node from;
        Node to;
        int value;
        int cost;

        Edge(Node from, Node to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
            this.cost = 0;
        }

        Edge(Edge ori) {
            this.from = ori.to;
            this.to = ori.from;
            this.value = ori.value;
            this.cost = ori.cost;
        }
    }

    static class Node {
        int id;
        double dis;
        ArrayList<Edge> edgeList;
        boolean inq;
        int cnt;
        Node pre;

        Node(int id) {
            this.id = id;
            this.dis = Double.MAX_VALUE;
            this.edgeList = new ArrayList<>();
            this.pre = null;
            this.cnt = 0;
            this.inq = false;
        }

        void appendEdge(Edge edge) {
            this.edgeList.add(edge);
        }

        static void fresh(Node[] nodes) {
            for (Node node : nodes) {
                node.dis = Double.MAX_VALUE;
                node.pre = null;
                node.inq = false;
                node.cnt = 0;
            }
        }
    }

}
