package Checker;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
    static Random innerRand = new Random();

    public static void caseGenerator() throws FileNotFoundException {
        int n = RandomGenerator.getInt(6, 10);
        StringBuilder str = new StringBuilder();
        str.append(n).append(" ");
        StringBuilder aftStr = new StringBuilder();
        int m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = RandomGenerator.getInt(1,5); j >= 0; j--) {
                int x = RandomGenerator.getInt(0, n - 1);
                int cost = RandomGenerator.getInt(1, 100);
                aftStr.append(x).append(" ").append(i).append(" ").append(cost).append("\n");
                m++;
            }
        }
        str.append(m).append("\n");
        str.append(aftStr);

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
