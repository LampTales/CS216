package Week01;

import java.io.*;
import java.util.StringTokenizer;

public class Sch {
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
        int[] caps = new int[m];
        int[][] stuG = new int[n][m];
        int[][] schG = new int[m][n];
        //loading caps
        for (int i = 0; i < m; i++) {
            caps[i] = Reader.nextInt();
        }
        //loading values stu
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                stuG[i][j] = Reader.nextInt();
            }
        }
        //loading values sch
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                schG[i][j] = Reader.nextInt();
            }
        }

        Queue queue = new Queue(m * (n + 1) + 2);
        boolean[] inq = new boolean[m];
        for (int i = 0; i < m; i++) {
            queue.enq(i);
            inq[i] = true;
        }
        int[] stuSigns = new int[n];
        for (int i = 0; i < n; i++) {
            stuSigns[i] = -1;
        }

        int[][] schRank = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                schRank[i][j] = j;
            }
        }
        for (int i = 0; i < m; i++) {
            mergeSort(0, n - 1, schRank[i], schG[i]);
        }

//        System.out.println(Arrays.deepToString(schRank));

        int[] schCheck = new int[m];

        while (!queue.isEmpty()) {
            int school = queue.deq();
            inq[school] = false;
            int student = schRank[school][schCheck[school]];
            schCheck[school]++;

//            System.out.println(Arrays.toString(schCheck));

            if (stuG[student][school] > 0 && schG[school][student] > 0) {
                if (stuSigns[student] == -1) {
                    stuSigns[student] = school;
                    caps[school]--;
                } else {
                    int currSch = stuSigns[student];
                    if (stuG[student][school] > stuG[student][currSch]) {
                        stuSigns[student] = school;
                        caps[school]--;
                        caps[currSch]++;
                        if (schCheck[currSch] < n && schG[currSch][schRank[currSch][schCheck[currSch]]] > 0 && (!inq[currSch])) {
                            queue.enq(currSch);
                            inq[currSch] = true;
                        }
                    }

                }
            }
            if (schCheck[school] < n && caps[school] > 0 && schG[school][schRank[school][schCheck[school]]] > 0 && (!inq[school])) {
                queue.enq(school);
                inq[school] = true;
            }
        }

        //output
        int[] schIn = new int[m];
        int[][] schInId = new int[m][n];
        for (int i = 0; i < n; i++) {
            int school = stuSigns[i];
            if (school != -1) {
                schInId[school][schIn[school]] = i + 1;
                schIn[school]++;
            }
        }
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            out.print(schIn[i]);
            for (int j = 0; j < n && schInId[i][j] != 0; j++) {
                out.print(" " + schInId[i][j]);
            }
            if (i != m - 1) {
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

    public static void mergeSort(int start, int end, int[] array, int[] schG){
        if (start == end){
            return;
        }
        int spot = (end + start + 1) / 2;
        mergeSort(start, spot - 1, array, schG);
        mergeSort(spot, end, array, schG);

        int[] temp = new int[end - start + 1];
        int ia = start; int ib = spot;
        for (int i = 0; i < temp.length; i++){
            if (ia == spot){
                temp[i] = array[ib];
                ++ib;
            } else if (ib == end + 1){
                temp[i] = array[ia];
                ++ia;
            } else {
                if (better(array[ia], array[ib], schG)){
                    temp[i] = array[ia];
                    ++ia;
                } else {
                    temp[i] = array[ib];
                    ++ib;
                }
            }
        }
        System.arraycopy(temp, 0, array, start, temp.length);
    }

    public static boolean better(int A, int B, int[]schG) {
        return schG[A] >= schG[B];
    }

}
