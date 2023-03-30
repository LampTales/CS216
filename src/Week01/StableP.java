package Week01;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class StableP {
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
        HashMap<String, Integer> menH = new HashMap<>();
        HashMap<String, Integer> womenH = new HashMap<>();
        Queue queue = new Queue(n * n + 2);
        String[] men = new String[n];
        String[] women = new String[n];

        for (int i = 0; i < n; i++) {
            String str = Reader.next();
            men[i] = str;
            queue.enq(i);
            menH.put(str, i);
        }
        for (int i = 0; i < n; i++) {
            String str = Reader.next();
            women[i] = str;
            womenH.put(str, i);
        }

        int[][] menPre = new int[n][n];
        int[][] womenRank = new int[n][n];
        int[] menCnt = new int[n];
        int[] womenHas = new int[n];
        for (int i = 0; i < n; i++) {
            womenHas[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                menPre[i][j] = womenH.get(Reader.next());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                womenRank[i][menH.get(Reader.next())] = j;
            }
        }

        while (!queue.isEmpty()) {
            int m = queue.deq();
            int w = menPre[m][menCnt[m]];
            menCnt[m]++;
            if (womenHas[w] == -1) {
                womenHas[w] = m;
            } else {
                if (womenRank[w][m] < womenRank[w][womenHas[w]]) {
                    queue.enq(womenHas[w]);
                    womenHas[w] = m;
                } else {
                    queue.enq(m);
                }
            }
        }

        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            out.print(men[womenHas[i]] + " " + women[i]);
            if (i != n - 1) {
                out.println();
            }
        }
        out.flush();
    }

    static class Queue {
        int[] arr;
        int start;
        int end;

        public Queue(int maxSize) {
            start = 0;
            end = 0;
            arr = new int[maxSize];
        }

        void enq(int str) {
            arr[end] = str;
            end++;
        }

        int deq() {
            start++;
            return arr[start - 1];
        }

        int pek() {
            return arr[start];
        }

        boolean isEmpty() {
            return start == end;
        }
    }
}
