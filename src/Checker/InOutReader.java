package Checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class InOutReader {
    static class Reader {
        BufferedReader reader = null;
        StringTokenizer tokenizer = new StringTokenizer("");

        void fresh(String str) throws IOException {
            if (reader != null) {
                reader.close();
            }
            reader = new BufferedReader(new FileReader(str));
            tokenizer = new StringTokenizer("");
        }

        String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    Reader R;
    String fileName;

    public InOutReader() throws IOException {
        this.fileName = "input.txt";
        this.R = new Reader();
        R.fresh(fileName);
    }

    public InOutReader(String fileName) throws IOException {
        this.fileName = fileName;
        this.R = new Reader();
        R.fresh(fileName);
    }

    public void fresh() throws IOException {
        R.fresh(fileName);
    }

    public String next() throws IOException {
        return R.next();
    }

    public int nextInt() throws IOException {
        return R.nextInt();
    }
}
