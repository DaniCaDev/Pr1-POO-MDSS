package ejercicio1.arreglos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Representa un vector n-dimensional de números (T extends Number) usando un ArrayList<T>
 * para almacenar sus componentes. Permite obtener la dimensión, imprimir los componentes,
 * calcular el producto escalar con otro vector y leer/escribir los datos en un fichero.
 *
 * @param <T> Tipo numérico que extiende Number.
 */
public class Vector<T extends Number> implements Printable, IWriteable {
    private ArrayList<T> data;

    /**
     * Crea un vector a partir de los elementos dados.
     *
     * @param elementos Componentes del vector.
     */
    @SafeVarargs
    public Vector(T... elementos) { data = new ArrayList<>(Arrays.asList(elementos)); }

    /**
     * Constructor de copia.
     *
     * @param vector Vector que se desea copiar.
     */
    public Vector(Vector<T> vector) { data = new ArrayList<>(vector.data); }

    /**
     * Crea un vector a partir de una cadena de números separados por comas.
     * Se asume que T es Double.
     *
     * @param cadena Cadena con los componentes del vector.
     */
    @SuppressWarnings("unchecked")
    public Vector(String cadena) {
        data = Arrays.stream(cadena.replaceAll("[\\[\\]]", "").split(","))
                .map(String::trim)
                .map(Double::parseDouble)
                .map(x -> (T) x)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna el ArrayList con los componentes del vector.
     *
     * @return Lista de componentes.
     */
    public ArrayList<T> getVector() { return data; }

    /**
     * Retorna la longitud (número de componentes) del vector.
     *
     * @return Número de componentes.
     */
    public int getLongitud() { return data.size(); }

    /**
     * Retorna el componente en la posición indicada.
     *
     * @param pos Índice del componente a obtener.
     * @return Componente en la posición especificada.
     */
    public T getElemento(int pos) { return data.get(pos); }

    /**
     * Establece el componente en la posición indicada.
     *
     * @param pos Índice donde se asigna el nuevo valor.
     * @param valor Nuevo valor a establecer.
     */
    public void setElemento(int pos, T valor) { data.set(pos, valor); }

    /**
     * Imprime el vector en consola usando su representación formateada.
     */
    @Override
    public void print() { System.out.println(toFormattedString()); }

    /**
     * Calcula el producto escalar entre este vector y otro vector.
     *
     * @param otro Vector para calcular el producto escalar.
     * @return Producto escalar de ambos vectores.
     * @throws IllegalArgumentException Si las dimensiones son distintas.
     */
    public double productoEscalar(Vector<T> otro) {
        if(getLongitud() != otro.getLongitud()) throw new IllegalArgumentException("Dimensiones distintas.");
        double suma = 0.0;
        for (int i = 0; i < getLongitud(); i++)
            suma += data.get(i).doubleValue() * otro.data.get(i).doubleValue();
        return suma;
    }

    /**
     * Escribe los componentes del vector en un fichero, separados por comas.
     *
     * @param filename Nombre del fichero de salida.
     * @throws IOException Si ocurre un error durante la escritura.
     */
    @Override
    public void write(String filename) throws IOException {
        Files.write(Paths.get(filename),
                data.stream().map(String::valueOf)
                        .collect(Collectors.joining(",")).getBytes());
    }

    /**
     * Lee un fichero con una única línea de números separados por comas y reconstruye el vector.
     * Se asume que T es Double.
     *
     * @param filename Nombre del fichero de entrada.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void read(String filename) throws IOException {
        data = new ArrayList<>(Arrays.stream(Files.readString(Paths.get(filename)).split(","))
                .map(String::trim).map(Double::parseDouble).map(x -> (T)x)
                .collect(Collectors.toList()));
    }

    /**
     * Retorna una representación formateada del vector en el formato [x1, x2, ...].
     *
     * @return Cadena con los componentes formateados.
     */
    @Override
    public String toFormattedString() {
        return data.stream().map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    /**
     * Lee el vector desde un Scanner (se espera una única línea con números separados por comas).
     *
     * @param sc Scanner desde el cual se leerá la línea.
     * @throws IOException Si no se encuentra una línea para leer.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void read(Scanner sc) throws IOException {
        if(sc.hasNextLine())
            data = new ArrayList<>(Arrays.stream(sc.nextLine().split(","))
                    .map(String::trim)
                    .map(Double::parseDouble)
                    .map(x -> (T)x)
                    .collect(Collectors.toList()));
        else
            throw new IOException("No se encontró una línea en el Scanner.");
    }
}