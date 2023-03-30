package Week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ColorTree {
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
        int root = Reader.nextInt() - 1;
        Node[] arr = new Node[n];
        Heap heap = new Heap(n + 1);
        long cost = 0;
        for (int i = 0; i < n; i++) {
            Node node = new Node(i, Reader.nextInt());
            arr[i] = node;
            cost += node.sum;
            if (i != root) {
                heap.insert(node);
            }
        }
        for (int i = 0; i < n - 1; i++) {
            int fa = Reader.nextInt() - 1;
            int ch = Reader.nextInt() - 1;
            arr[fa].children.add(ch);
            arr[ch].father = fa;
        }
        for (int i = 0; i < n - 1; i++) {
            Node node = heap.pop();
            Node father = arr[node.father];
            cost += father.cnt * node.sum;
            father.sum += node.sum;
            father.cnt += node.cnt;
            for (int j = 0; j < node.children.size(); j++) {
                int x = node.children.get(j);
                arr[x].father = father.id;
                father.children.add(x);
            }
            if (father.id != root) {
                heap.modify(father);
            }
        }
        System.out.println(cost);
    }



    static class Node {
        int id;
        long sum;
        int cnt;
        int father;
        ArrayList<Integer> children;
        int spot;

        Node(int id, int weight) {
            this.id = id;
            this.sum = weight;
            this.cnt = 1;
            this.father = -1;
            this.children = new ArrayList<>();
            this.spot = 0;
        }

        static boolean larger(Node n1, Node n2) {
            return ((double)n1.sum) / n1.cnt > ((double)n2.sum) / n2.cnt;
        }
    }

    static class Heap {
        Node[] nodes;
        int size;

        Heap(int maxSize) {
            this.nodes = new Node[maxSize + 1];
            this.size = 0;
            this.nodes[0] = new Node(-1, 5000);
            nodes[0].spot = 0;
        }

        void insert(Node value) {
            size++;
            int spot = size;
            while (Node.larger(value, nodes[spot / 2])) {
                nodes[spot] = nodes[spot / 2];
                nodes[spot].spot = spot;
                spot = spot / 2;
            }
            nodes[spot] = value;
            nodes[spot].spot = spot;
        }

        Node pop() {
            Node ans = nodes[1];
            Node move = nodes[size];
            size--;
            int pre = 1;
            for (int spot = 2; spot <= size; ) {
                if (spot < size && Node.larger(nodes[spot + 1], nodes[spot])) {
                    spot++;
                }
                if (Node.larger(nodes[spot], move)) {
                    nodes[pre] = nodes[spot];
                    nodes[pre].spot = pre;
                    pre = spot;
                    spot = spot * 2;
                } else {
                    break;
                }
            }
            nodes[pre] = move;
            nodes[pre].spot = pre;
            return ans;
        }

        void modify(Node aim) {
            if (Node.larger(aim, nodes[aim.spot / 2])) {
                int spot = aim.spot;
                while (Node.larger(aim, nodes[spot / 2])) {
                    nodes[spot] = nodes[spot / 2];
                    nodes[spot].spot = spot;
                    spot = spot / 2;
                }
                nodes[spot] = aim;
                nodes[spot].spot = spot;
            } else {
                int spot = aim.spot;
                int nextSpot = 2 * spot;
                while (nextSpot <= size) {
                    if (nextSpot < size && Node.larger(nodes[nextSpot + 1], nodes[nextSpot])) {
                        nextSpot++;
                    }
                    if (Node.larger(nodes[nextSpot], aim)) {
                        nodes[spot] = nodes[nextSpot];
                        nodes[spot].spot = spot;
                        spot = nextSpot;
                        nextSpot = spot * 2;
                    } else {
                        break;
                    }
                }
                nodes[spot] = aim;
                nodes[spot].spot = spot;
            }
        }

        int size() {
            return size;
        }

        Node peek() {
            return nodes[1];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean checkSpot() {
            for (int i = 1; i <= size; i++) {
                if (nodes[i].spot != i) {
                    return false;
                }
            }
            return true;
        }
    }
}
