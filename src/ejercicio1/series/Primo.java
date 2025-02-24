package ejercicio1.series;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * La clase primo hereda de la clase Serie y se encarga
 * de generar todos los numeros primos dentro del intervalo
 * [limiteInferior, limiteSuperior]. Utiliza la Criba de Eratóstenes
 * para su cálculo y ofrece un método para sumar dichos primos.
 * @author Daniel Alejandro Álvarez Casablanca
 */

public class Primo extends Serie {
    
    /**
     * Contructor por defecto
     * Establece el límite inferior en 1 y el superior en 100
     */
    public Primo() {
        super();
    }

    /**
     * Constructor que establece un limite superior
     * El limite inferior se mantiene por defecto en 1
     * 
     * @param superior Límite superior del intervalo
     */
     public Primo(int superior) {
         super(1, superior);
     }

    /**
     * Contructor que establece limite inferior y superior
     * 
     * @param inferior Límite inferior para la generacion de primos.
     * @param superior Límite superior para la generacion de primos.
     */
    public Primo(int inferior, int superior) {
        super(inferior, superior);
    }

    /**
     * Genera todos los números primos en el intervalo
     * [limiteInferior..limiteSuperior] utilizando la
     * Criba de Eratóstenes y los almacena en secuenciaGenerada.
     */
    @Override
    public void generarSerie() {
       boolean[] esPrimo = inicializarCriba();
       aplicarCriba(esPrimo);
       agregarPrimos(esPrimo);
    }

    /**
     * Geenera un arreglo de booleanos (inicializa la Criba)
     * @return Un arreglo de booleanos con todos los elementos en true
     */
    private boolean[] inicializarCriba() {
        boolean[] esPrimo = new boolean[limiteSuperior + 1];
        Arrays.fill(esPrimo, true);
        return esPrimo;
    }

    /**
     * Aplica la Criba de Eratóstenes para generar los números primos
     * @param esPrimo
     */
    private void aplicarCriba(boolean[] esPrimo) {
        for (int p = 2; p * p <= limiteSuperior; p++) {
            if (esPrimo[p]) {
                for (int i = p * p; i <= limiteSuperior; i += p) { esPrimo[i] = false; }
            }
        }
    }

    /**
     * Agrega los números primos generados a la secuencia
     * @param esPrimo
     */
    private void agregarPrimos(boolean[] esPrimo) {
        for (int i = Math.max(2, limiteInferior); i <= limiteSuperior; i++) {
            if (esPrimo[i]) { secuenciaGenerada.add(i); }
        }
    }

    /**
     * Devuelve la secuencia generada de números primos
     * @return Lista de números primos generados
     */
    public List<Integer> getSecuenciaGenerada() {
        return secuenciaGenerada;
    }

    /**
     * Método estático de 1-línea para verificar primalidad de un número
     * @param numero: Número que se quiere comprobar si es primo
     */
    public static boolean esPrimo(int numero) {
        return numero > 1 && IntStream.rangeClosed(2, (int)Math.sqrt(numero)).noneMatch(i -> numero % i == 0);
    }

    /**
     * Suma todos los números primos generados en la serie
     *
     * @return La suma de los números primos generados
     */
    public int sumar() {
        return secuenciaGenerada.stream().mapToInt(Integer::intValue).sum();
    }
    
}
