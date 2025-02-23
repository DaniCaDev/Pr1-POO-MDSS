package ejercicio1.series;

public class Fibonacci extends Serie {
    /**
     * Constructor por defecto
     */
    public Fibonacci() {
        super();
    }

    /**
     * Constructor que establece un limite superior
     */
    public Fibonacci() {
        super(1, superior);
    }

    /**
     * Constructor que establece limite inferior y superior
     */
    public Fibonacci(int inferior, int superior) {
        super(inferior, superior);
    }

    /**
     * Genera la serie de Fibonacci en el intervalo
     * [limiteInferior..limiteSuperior] y los almacena en secuenciaGenerada.
     *
     */
    @Override
    public void generarSerie() {
        int a = 0, b = 1, c = 0;
        while (c <= limiteSuperior) {
            c = a + b;
            if (c >= limiteInferior && c <= limiteSuperior) {
                secuenciaGenerada.add(c);
            }
            a = b;
            b = c;
        }
    }

    
}
