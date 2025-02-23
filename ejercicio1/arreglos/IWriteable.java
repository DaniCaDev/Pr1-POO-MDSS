package ejercicio1.arreglos;

import java.io.IOException;

/**
 * Interfaz que define la capacidad de escribir
 * (y opcionalmente leer) datos de un objeto a un fichero.
 */
public interface IWriteable {
    /**
     * Escribe los datos del objeto en un fichero de texto,
     * en el formato que la clase decida implementar.
     * @param filename nombre del fichero
     * @throws IOException si ocurre un error de E/S
     */
    void write(String filename) throws IOException;

    /**
     * Método opcional para leer datos de un fichero.
     * Por defecto, no está implementado.
     * Quien quiera usarlo, puede sobrescribirlo.
     */
    default void read(String filename) throws IOException {
        throw new UnsupportedOperationException("Método read no implementado");
    }
}