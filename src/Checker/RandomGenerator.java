package Checker;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
    static Random innerRand = new Random();

    public static void caseGenerator() throws FileNotFoundException {
        int n = RandomGenerator.getInt(450000, 500000);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int ran = RandomGenerator.getInt(0,2);
            switch (ran) {
                case 0:
                    str.append('0');
                    break;
                case 1:
                    str.append('1');
                    break;
                case 2:
                    str.append('?');
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
