import java.util.Scanner;

/**
 * @brief Fibonacci series
 *
 */

public class Fibonacci {
    void fibonacci(int n) {
        int a = 0, b = 1, c;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < n; i++) {
            c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of terms: ");
        int n = sc.nextInt();
        Fibonacci obj = new Fibonacci();
        obj.fibonacci(n);
        sc.close();
    }

}