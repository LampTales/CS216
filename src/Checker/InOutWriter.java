package Checker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class InOutWriter {
    public static void writeInput(String str) throws FileNotFoundException {
        new PrintWriter("input.txt").close();
        PrintWriter pw = new PrintWriter("input.txt");
        pw.print(str);
        pw.close();
    }

    public static void writeOutput(String str) throws FileNotFoundException {
        new PrintWriter("out_2.txt").close();
        PrintWriter pw = new PrintWriter("out_2.txt");
        pw.print(str);
        pw.close();
    }
}
