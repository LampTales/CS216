package Checker;

import java.io.*;
import java.util.Scanner;

import static Checker.RandomGenerator.caseGenerator;
import static Checker.LRU.code;

public class Verifier {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 1000; i++) {

            //verifying mode
            if (!compareOut()) {
                return;
            }

            //check the progress
            if ((i + 1) % 50 == 0) {
                System.out.printf("================================ case %d =======================================\n", i + 1);
            }
        }
    }


    public static void singleOut() throws IOException {
        //生成测试样例，可以到这个方法中去改参数
        caseGenerator();

            /* for java
            输入重定向：让你的接受输入定向到input.txt
                你可以用如下的方式：
                    InOutReader reader = new InoutReader(); //无需参数
                    reader.next(); reader.nextInt;
            输出重定向：让你的结果输出定向到output.txt
                你可以用如下的方式：
                    new PrintWriter("output.txt").close();
                    PrintWriter out = new PrintWriter("output.txt");
                    out.print(); out.print(); out.print():
                    out.close();
                你也可以用InOutWriter，将所有output先build成一个大string，然后writeOutput(str)
            把下面的那一行code()改成自己写的算法
            运行main
             */

//            code();



            /* for cpp
            cpp文件中需要做输入重定向，其input文件地址为input.txt，可以这样做：
                freopen("input.txt", "r", stdin);
            不需要做输出重定向
            在Windows中编译并将可执行文件a.exe放在根目录下（即与input.txt、output.txt同级）
            在idea下的terminal测试一下./a.exe是否可以运行：
              （1）将一个测试样例手动复制到input.txt中
              （2）执行./a.exe
              （3）如果一切正常，在命令行中应该会print出你的答案
              （4）如果不正常，bing搜： windows powerShell修改执行策略，只能说可能有关
            如果没有什么问题，就直接运行main
             */

//        try {
//            Runtime rt = Runtime.getRuntime();
//            Process p = rt.exec(".\\a.exe");
//            InputStream is = p.getInputStream();
//            StringBuilder str = new StringBuilder();
//            String line1 = null;
//            BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
//            while ((line1 = br1.readLine()) != null) {
//                str.append(line1).append("\n");
//            }
//            new PrintWriter("output.txt").close();
//            PrintWriter out = new PrintWriter("output.txt");
//            out.println(str);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        //verify
        //出错后可到input.txt与output.txt中去查看输入输出
        if (!singleVerify()) {
            System.out.println("ERROR!");
        }
    }

    public static boolean singleVerify() throws IOException {
        InOutReader InputReader = new InOutReader("input.txt");
        InOutReader OutputReader = new InOutReader("output.txt");
        int n = InputReader.nextInt();
        int m = InputReader.nextInt();
        int[] oriCaps = new int[m];

        int[][] stuG = new int[n][m];
        int[][] schG = new int[m][n];
        //loading caps
        for (int i = 0; i < m; i++) {
            oriCaps[i] = InputReader.nextInt();
        }
        //loading values stu
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                stuG[i][j] = InputReader.nextInt();
            }
        }
        //loading values sch
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                schG[i][j] = InputReader.nextInt();
            }
        }

        int[] stuSigns = new int[n];
        for (int i = 0; i < n; i++) {
            stuSigns[i] = -1;
        }
        int[] schIn = new int[m];


        for (int i = 0; i < m; i++) {
            schIn[i] = OutputReader.nextInt();
//            System.out.println(schIn[i]);
            for (int j = 0; j < schIn[i]; j++) {
                int x = OutputReader.nextInt() - 1;
//                System.out.println(x);
                stuSigns[x] = i;
            }
        }

        //verifying
        int[] schMin = new int[m];
        for (int i = 0; i < n; i++) {
            int school = stuSigns[i];
            if (school != -1) {
                if (schMin[school] == 0) {
                    schMin[school] = schG[school][i];
                } else {
                    schMin[school] = Math.min(schMin[school], schG[school][i]);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (schIn[i] > oriCaps[i]) {
                System.out.printf("school %d accepts too many students!\n", i + 1);
                return false;
            }
        }
        for (int i = 0; i < n; i++) {
            int school = stuSigns[i];
            if (school == -1) {
                for (int x = 0; x < m; x++) {
                    if (stuG[i][x] > 0 && (schG[x][i] > schMin[x] || (oriCaps[x] > schIn[x] && schG[x][i] > 0))) {
                        System.out.printf("student %d wants to go to %d\n", i + 1, x + 1);
                        return false;
                    }
                }
            } else {
                if (stuG[i][school] < 0 || schG[school][i] < 0) {
                    System.out.printf("student %d hates %d or reverse\n", i + 1, school + 1);
                    return false;
                }
                for (int x = 0; x < m; x++) {
                    if (x != school) {
                        if (stuG[i][x] > stuG[i][school] && (schG[x][i] > schMin[x] || (oriCaps[x] > schIn[x] && schG[x][i] > 0))) {
                            System.out.printf("student %d prefer %d but in %d\n", i + 1, x + 1, school + 1);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean compareOut() throws IOException {

        caseGenerator();

        //code sample 1
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(".\\a.exe");
            InputStream is = p.getInputStream();
            StringBuilder str = new StringBuilder();
            String line1 = null;
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
            while ((line1 = br1.readLine()) != null) {
                str.append(line1).append("\n");
            }
            new PrintWriter("out_1.txt").close();
            PrintWriter out = new PrintWriter("out_1.txt");
            out.println(str);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        //code sample 2
//        try {
//            Runtime rt = Runtime.getRuntime();
//            Process p = rt.exec(".\\b.exe");
//            InputStream is = p.getInputStream();
//            StringBuilder str = new StringBuilder();
//            String line1 = null;
//            BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
//            while ((line1 = br1.readLine()) != null) {
//                str.append(line1).append("\n");
//            }
//            new PrintWriter("sample_2_out.txt").close();
//            PrintWriter out = new PrintWriter("sample_2_out.txt");
//            out.println(str);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        code();

        if (!compareVerify()) {
            System.out.println("ERROR!");
            return false;
        }
        return true;
    }

    public static boolean compareVerify() throws IOException {
        Scanner scan1 = new Scanner(new FileInputStream(new File("out_1.txt")));
        Scanner scan2 = new Scanner(new FileInputStream(new File("out_2.txt")));
        if (scan1.hasNext()) {
            String str1 = scan1.nextLine();
            String str2 = scan2.nextLine();
            if (!str1.equals(str2)) {
                System.out.println("sample 1: " + str1);
                System.out.println("sample 2: " + str2);
                return false;
            }
        }

        return true;
    }
}
