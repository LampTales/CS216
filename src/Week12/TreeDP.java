package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeDP {
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
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
             nodes[i] = new Node(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int a = Reader.nextInt() - 1;
            int b = Reader.nextInt() - 1;
            nodes[a].addChild(nodes[b]);
            nodes[b].addChild(nodes[a]);
        }
        for (int i = 0; i < n; i++) {
            nodes[i].value = Reader.nextInt();
        }

        dfs(nodes[0], new Node(-1));
        System.out.println(nodes[0].add + nodes[0].min);
    }

    static void dfs(Node node, Node fa) {
        for (Node son : node.children) {
            if (fa.id != son.id) {
                dfs(son, node);
                node.add = Math.max(node.add, son.add);
                node.min = Math.max(node.min, son.min);
            }
        }
        node.value = node.value + node.add - node.min;
        if (node.value > 0) {
            node.min += node.value;
        } else if (node.value < 0) {
            node.add -= node.value;
        }
    }

    static class Node {
        int id;
        long value;
        ArrayList<Node> children;
        long add;
        long min;

        Node(int id) {
            this.id = id;
            this.value = 0;
            this.children = new ArrayList<>();
            this.add = 0;
            this.min = 0;
        }

        void addChild(Node node) {
            children.add(node);
        }
    }
}
