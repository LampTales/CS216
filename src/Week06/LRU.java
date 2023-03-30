package Week06;

import Week04.ColorTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LRU {

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
        int m = Reader.nextInt();
        int t = Reader.nextInt();
        int curTime = 0;
        Heap heap = new Heap(n + 2);
        HashMap<Integer, Node> map = new HashMap<>();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < m; i++) {
            while (!heap.isEmpty()) {
                if (curTime - heap.peek().enterTime > t) {
                    Node node = heap.pop();
                    map.remove(node.key);
                } else {
                    break;
                }
            }

            int op = Reader.nextInt();
            if (op == 1) {
                Node node = map.get(Reader.nextInt());
                if (node == null) {
                    str.append(-1).append("\n");
                } else {
                    str.append(node.value).append("\n");
                    node.enterTime = curTime;
                    heap.modify(node);
                }
            } else {
                int key = Reader.nextInt();
                Node node = map.get(key);
                if (node == null) {
                    while (heap.size() > n - 1) {
                        node = heap.pop();
                        map.remove(node.key);
                    }
                    node = new Node(key, curTime, Reader.nextInt());
                    map.put(node.key, node);
                    heap.insert(node);
                } else {
                    node.value = Reader.nextInt();
                    node.enterTime = curTime;
                    heap.modify(node);
                }
            }
            curTime++;
        }
        while (!heap.isEmpty()) {
            str.append(heap.pop().value).append("\n");
        }
        System.out.println(str);
    }

    static class Node implements Comparable<Node> {
        int key;
        int enterTime;
        int value;
        int spot = 0;

        public Node(int key, int enterTime, int value) {
            this.key = key;
            this.enterTime = enterTime;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.enterTime - o.enterTime;
        }
    }

    static class Heap {
        Node[] nodes;
        int size;

        Heap(int maxSize) {
            this.nodes = new Node[maxSize + 1];
            this.size = 0;
            this.nodes[0] = new Node(-1, -1, -1);
            nodes[0].spot = 0;
        }

        void insert(Node value) {
            size++;
            int spot = size;
            while (value.compareTo(nodes[spot / 2]) < 0) {
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
                if (spot < size && nodes[spot + 1].compareTo(nodes[spot]) < 0) {
                    spot++;
                }
                if (nodes[spot].compareTo(move) < 0) {
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
            if (aim.compareTo(nodes[aim.spot / 2]) < 0) {
                int spot = aim.spot;
                while (aim.compareTo(nodes[spot / 2]) < 0) {
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
                    if (nextSpot < size && nodes[nextSpot + 1].compareTo(nodes[nextSpot]) < 0) {
                        nextSpot++;
                    }
                    if (nodes[nextSpot].compareTo(aim) < 0) {
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
    }
}
