package ejercicio1.series;

import java.util.ArrayList; // Importamos la clase ArrayList para poder utilization

/**
 * Clase Padre para representar las series tanto de fibonacci como primos
 * @author Daniel Alejandro Álvarez Casablanca
 */
public class Serie {
    // Declaramos un ArrayList de enteros para almacenar la secuencia generada
    protected ArrayList<Integer> secuenciaGenerada = new ArrayList<Integer>();

    //Declaramos el límite inferior y superior de la serie
    protected int limiteInferior;
    protected int limiteSuperior;

    /**
     * Constructor por defecto
     */
    public Serie() {
        this.limiteInferior = 1;
        this.limiteSuperior = 100;
    }

    /**
     * Constructor con 1 parámetro
     * @param superior: Límite superior del arreglo
     */
    public Serie(int superior) {
        this.limiteInferior = 1;
        this.limiteSuperior = superior;
    }

    /**
     * Constructor con 2 parámetros
     * @param inferior: Límite inferior del arreglo
     * @param superior: Límite superior del arreglo
     */
    public Serie(int inferior, int superior) {
        this.limiteInferior = inferior;
        this.limiteSuperior = superior;
    }

    /**
     * Getter para obtener el limiteSuperior
     *
     */
    public long getLimiteSuperior() { return this.limiteSuperior; }

    /**
     * Setter para establecer el límite superior
     * @param limite: limite superior que se quiere establecer
     */

    public void setLimiteSuperior(int limite) { this.limiteSuperior = limite; }

    /**
     * Getter para obtener el limiteInferior
     */
    public long getLimiteInferior() { return this.limiteInferior; }

    /**
     * Setter para establecer el límite inferior
     * @param limite: limite inferior que se quiere establecer
     */
    public void setLimiteInferior(int limite) { this.limiteInferior = limite; }

    /**
     * Getter para obtener el número de elementos generados
     * @return : números de elementos generados
     */
    public int tamanio() { return this.secuenciaGenerada.size(); }

    /**
     * Getter para obtener el valor de una posición determinada
     * @param valor : indice
     */
    public int get(int valor) { return this.secuenciaGenerada.get(valor); }

}
