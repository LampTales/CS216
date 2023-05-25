package Week12;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 32; i++) {
            System.out.printf("            %d: simd_read_data1 = save[%d:%d];\n",i, (i + 3) * 32 + 31, i * 32);
        }
    }
}
