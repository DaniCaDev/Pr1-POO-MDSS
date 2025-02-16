import java.util.Scanner;

public class Primos {

    public boolean isPrime(int number) {
        boolean prime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }

    public int SumaPrimos(int number) {
        int sum = 0;
        for (int i = 2; i <= number; i++) {
            if(isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Primos p = new Primos();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un número: ");
        int number = sc.nextInt();
        System.out.println("La suma de los números primos hasta " + number + " es: " + p.SumaPrimos(number));
    }
}
