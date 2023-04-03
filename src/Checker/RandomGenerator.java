package Checker;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
    static Random innerRand = new Random();

    public static void caseGenerator() throws FileNotFoundException {
        int n = RandomGenerator.getInt(1, 100);
        int m = RandomGenerator.getInt(30000, 40000);
        int t = RandomGenerator.getInt(1, 1000);
        StringBuilder str = new StringBuilder();
        str.append(n).append(" ").append(m).append(" ").append(t).append("\n");
        for (int i = 0; i < m; i++) {
            int op = RandomGenerator.getInt(1, 2);
            str.append(op).append(" ");
            if (op == 1) {
                str.append(RandomGenerator.getInt(0,200)).append("\n");
            } else {
                str.append(RandomGenerator.getInt(0,200)).append(" ").append(RandomGenerator.getInt(1, 1000)).append("\n");
            }
        }
        InOutWriter.writeInput(str.toString());
    }

    public static int[] getNonRepeatArray(int min, int max, int num, boolean withSpecialRequirement) {
        Random random = new Random();
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() < num) {
            int x = random.nextInt(max - min + 1) + min;
            if (specialCheck(x)) {
                set.add(x);
            }
        }
        return Arrays.stream(set.toArray(new Integer[0])).mapToInt(Integer::valueOf).toArray();
    }

    static boolean specialCheck(int x) {
        return x != 0;
    }

    public static int getInt(int min, int max) {
        return innerRand.nextInt(max - min + 1) + min;
    }
}
