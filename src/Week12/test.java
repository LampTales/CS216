package Week12;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 32; i++) {
            System.out.printf("%d: save[%d:%d] <= write_data;\n",i, i * 32 + 31, i * 32);
        }
    }
}
