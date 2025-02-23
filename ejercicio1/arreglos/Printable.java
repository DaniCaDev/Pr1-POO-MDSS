package ejercicio1.arreglos;

/**
 * Interfaz que define el contrato para imprimir
 * una representación en texto de un objeto.
 */
public interface Printable {
    /**
     * Devuelve una representación formateada en texto
     * del objeto que la implemente.
     * @return String con la descripción del objeto.
     */
    String toFormattedString();

    /**
     * Método por defecto que simplemente llama a toFormattedString().
     * Así, si haces System.out.println(obj), usará esta versión.
     */
    default String toString() {
        return toFormattedString();
    }
}