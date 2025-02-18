package ejercicio1.series;

import java.util.ArrayList;

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
        // Creamos un array de booleanos de tamaño limiteSuperior
        boolean[] esPrimo = new boolean[limiteSuperior + 1];
        // Inicializamos todos los elementos a true
        for (int i = 2; i <= limiteSuperior; i++) {
            esPrimo[i] = true;
        }
        // Marcamos los números que no son primos
        for (int i = 2; i * i <= limiteSuperior; i++) {
            if (esPrimo[i]) {
                for (int j = i * i; j <= limiteSuperior; j += i) {
                    esPrimo[j] = false;
                }
            }
        }
        // Añadimos los números primos al arraylist
        for (int i = limiteInferior; i <= limiteSuperior; i++) {
            if (esPrimo[i]) {
                secuenciaGenerada.add(i);
            }
        }
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
