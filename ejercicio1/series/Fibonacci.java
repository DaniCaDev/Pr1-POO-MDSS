package ejercicio1.series;

/**
 * Clase para generar la serie de Fibonacci.
 * Interpreta limiteSuperior como "n" (n primeros Fibonacci).
 */
public class Fibonacci extends Serie {

    /**
     * Constructor que establece un limite superior
     */
    public Fibonacci(int superior) {
        super(superior);
    }

    /**
     * Constructor que establece limite inferior y superior
     */
    public Fibonacci(int inferior, int superior) {
        super(inferior, superior);
    }

    /**
     * Calcula si un número dado pertenece a la secuencia de Fibonacci.
     * @param numero  El número que se quiere comprobar si pertenece a la secuencia de Fibonacci.
     * @return true si el número pertenece a la secuencia de Fibonacci, false en caso contrario.
     */
    private static boolean calcularFibonacci(int numero) {
        int a = 0, b = 1;
        while (b < numero) b = a + (a = b);
        return b == numero;
    }

    /**
     * Método que comprueba si un número pertenece a la secuencia de Fibonacci o no
     * @param numero: Número que se quiere comprobar si pertenece a la secuencia
     */
    public static boolean esFibonacci(int numero) {
        if (numero <= 1) { return true;	}
        return calcularFibonacci(numero);
    }

    /**
     * Método que genera la secuencia de Fibonacci
     */
    @Override
    public void generarSerie() {
        secuenciaGenerada.clear();
        int a = 1, b = 1;
        while (a <= limiteSuperior) {
            if (a >= limiteInferior) {
                secuenciaGenerada.add(a);
            }
            int temp = a + b;
            a = b;
            b = temp;
        }
    }
}
