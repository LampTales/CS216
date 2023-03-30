package Week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Win {
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
        Game[] games = new Game[n];
        for (int i = 0; i < n; i++) {
            games[i] = new Game();
            games[i].ddl = Reader.nextInt();
        }
        for (int i = 0; i < n; i++) {
            games[i].weight = Reader.nextInt();
        }

        Arrays.sort(games);
        MinHeap heap = new MinHeap(n + 2);

        int totalTime = 0;
        int spendTime = 0;
        int loss = 0;
        for (int i = 0; i < n; i++) {
            totalTime = games[i].ddl;
            if (spendTime < totalTime) {
                heap.insert(games[i]);
                spendTime++;
            } else if (games[i].weight > heap.peek().weight){
                loss += heap.pop().weight;
                heap.insert(games[i]);
            } else {
                loss += games[i].weight;
            }
        }
        System.out.println(loss);
    }

    static class Game implements Comparable<Game>{
        int ddl;
        int weight;

        Game() {
            ddl = 0;
            weight = 0;
        }

        Game(int ddl, int weight) {
            this.ddl = ddl;
            this.weight = weight;
        }

        @Override
        public int compareTo(Game o) {
            return this.ddl - o.ddl;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "ddl=" + ddl +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class MinHeap {
        Game[] nodes;
        int size;

        MinHeap(int maxSize) {
            this.nodes = new Game[maxSize + 1];
            this.size = 0;
            this.nodes[0] = new Game(0, -1);
        }

        void insert(Game value) {
            size++;
            int spot = size;
            while (smaller(value, nodes[spot/2])) {
                nodes[spot] = nodes[spot/2];
                spot = spot/2;
            }
            nodes[spot] = value;
        }

        Game pop() {
            Game ans = nodes[1];
            Game value = nodes[size];
            size--;
            int pre = 1;
            for (int spot = 2; spot <= size;) {
                if (spot < size && smaller(nodes[spot + 1], nodes[spot])) {
                    spot++;
                }
                if (smaller(nodes[spot], value)) {
                    nodes[pre] = nodes[spot];
                    pre = spot;
                    spot = spot * 2;
                } else {break;}
            }
            nodes[pre] = value;
            return ans;
        }

        Game peek() {
            return nodes[1];
        }

        boolean isEmpty() {
            return size == 0;
        }

    }


    static boolean smaller(Game a, Game b) {
        return a.weight < b.weight;
    }
}
