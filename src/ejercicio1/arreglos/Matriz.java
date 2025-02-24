package ejercicio1.arreglos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Representa una matriz m x n de números (T extends Number) usando un ArrayList de Vector<T>.
 * Implementa Printable e IWriteable para mostrar, leer y escribir la matriz.
 *
 * @param <T> Tipo numérico que extiende Number.
 */
public class Matriz<T extends Number> implements Printable, IWriteable {
    protected ArrayList<Vector<T>> matriz;

    /**
     * Crea una matriz 1x1 con el valor 0.0.
     */
    @SuppressWarnings("unchecked")
    public Matriz() {
        matriz = new ArrayList<>();
        matriz.add(new Vector<T>((T) Double.valueOf(0.0)));
    }

    /**
     * Crea una matriz m x n con todos sus elementos inicializados a 0.0.
     *
     * @param m Número de filas.
     * @param n Número de columnas.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int m, int n) {
        matriz = IntStream.range(0, m)
                .mapToObj(i -> new Vector<T>(Arrays.toString(IntStream.range(0, n)
                        .mapToObj(j -> (T) Double.valueOf(0.0))
                        .toArray(Number[]::new))))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Crea una matriz m x n a partir de un array bidimensional de coeficientes.
     *
     * @param m Número de filas.
     * @param n Número de columnas.
     * @param coef Array bidimensional con los coeficientes.
     */
    @SuppressWarnings("unchecked")
    public Matriz(int m, int n, double[][] coef) {
        matriz = IntStream.range(0, m)
                .mapToObj(i -> new Vector<T>(Arrays.toString(IntStream.range(0, n)
                        .mapToObj(j -> (T) Double.valueOf(coef[i][j]))
                        .toArray(Number[]::new))))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna el número de filas de la matriz.
     *
     * @return Cantidad de filas.
     */
    public int getNumRow() { return matriz.size(); }

    /**
     * Retorna el número de columnas de la matriz.
     *
     * @return Cantidad de columnas (0 si la matriz está vacía).
     */
    public int getNumCol() { return matriz.isEmpty() ? 0 : matriz.get(0).getLongitud(); }

    /**
     * Retorna la fila (Vector) en el índice especificado.
     *
     * @param i Índice de la fila.
     * @return Vector correspondiente a la fila i.
     */
    public Vector<T> getFila(int i) { return matriz.get(i); }

    /**
     * Establece la fila en el índice especificado.
     *
     * @param i Índice de la fila.
     * @param fila Vector a asignar en esa posición.
     */
    public void setFila(int i, Vector<T> fila) { while(matriz.size() <= i) matriz.add(new Vector<>()); matriz.set(i, fila); }

    /**
     * Retorna la representación formateada de la matriz, con cada fila en una línea.
     *
     * @return Cadena con la matriz en formato de filas.
     */
    @Override
    public String toFormattedString() {
        return matriz.stream().map(Vector::toFormattedString).collect(Collectors.joining("\n"));
    }

    /**
     * Imprime la matriz en consola.
     */
    @Override
    public void print() { System.out.println(toFormattedString()); }

    /**
     * Calcula el producto de esta matriz por otra.
     *
     * @param other Matriz multiplicadora.
     * @return Matriz resultante del producto.
     * @throws IllegalArgumentException Si el número de columnas de esta matriz no es igual al número de filas de la otra.
     */
    @SuppressWarnings("unchecked")
    public Matriz<T> producto(Matriz<T> other) {
        if(getNumCol() != other.getNumRow()) throw new IllegalArgumentException("Dimensiones incompatibles");
        Matriz<T> res = new Matriz<>(getNumRow(), other.getNumCol());
        for (int i = 0; i < getNumRow(); i++)
            for (int j = 0; j < other.getNumCol(); j++) {
                int finalI = i;
                int finalJ = j;
                res.getFila(i).getVector().set(j, (T) Double.valueOf(
                        IntStream.range(0, getNumCol())
                                .mapToDouble(k -> getFila(finalI).getElemento(k).doubleValue() * other.getFila(k).getElemento(finalJ).doubleValue())
                                .sum()));
            }
        return res;
    }

    /**
     * Transpone la matriz (intercambia filas por columnas).
     *
     * @return Matriz transpuesta.
     */
    public Matriz<T> transponer() {
        Matriz<T> trans = new Matriz<>(getNumCol(), getNumRow());
        for (int i = 0; i < getNumCol(); i++) {
            Vector<T> fila = new Vector<>();
            for (int j = 0; j < getNumRow(); j++)
                fila.getVector().add(getFila(j).getElemento(i));
            trans.matriz.add(fila);
        }
        return trans;
    }

    /**
     * Escribe la matriz en un fichero de texto con el siguiente formato:
     * línea 1: número de filas, línea 2: número de columnas, luego cada fila en una línea.
     *
     * @param filename Nombre del fichero de salida.
     * @throws IOException Si ocurre un error de E/S.
     */
    @Override
    public void write(String filename) throws IOException {
        String content = getNumRow() + "\n" + getNumCol() + "\n" + toFormattedString();
        Files.write(Paths.get(filename), content.getBytes());
    }

    /**
     * Lee una matriz desde un fichero de texto con el siguiente formato:
     * línea 1: número de filas, línea 2: número de columnas, luego cada fila con elementos separados por comas.
     *
     * @param filename Nombre del fichero de entrada.
     * @throws IOException Si ocurre un error de E/S.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void read(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        int rows = Integer.parseInt(lines.get(0).trim()), cols = Integer.parseInt(lines.get(1).trim());
        matriz = IntStream.range(0, rows)
                .mapToObj(i -> new Vector<T>(
                        Arrays.toString(Arrays.stream(lines.get(i+2).split(","))
                                .map(String::trim)
                                .map(s -> (T) Double.valueOf(Double.parseDouble(s)))
                                .toArray(Number[]::new))))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Lee la matriz desde un Scanner.
     *
     * @param sc Scanner desde el cual se leerá la matriz.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void read(java.util.Scanner sc) throws IOException {
        int rows = sc.nextInt(), cols = sc.nextInt();
        sc.nextLine();
        matriz = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            Vector<T> row = new Vector<T>(Arrays.toString(Arrays.stream(line.split(","))
                    .map(String::trim)
                    .map(s -> (T) Double.valueOf(Double.parseDouble(s)))
                    .toArray(Number[]::new)));
            matriz.add(row);
        }
    }
}


