package ejercicio1.arreglos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
        // Inicializa la matriz con una fila y una columna con valor 0.0
        Vector<T> fila = new Vector<>((T) Double.valueOf(0.0));
        matriz.add(fila);
    }

    /**
     * Crea una matriz 1x1 con el valor 0.0.
     */
    @SuppressWarnings("unchecked")
    public Matriz(double[][] datos) {
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
     * Retorna una representación formateada de la matriz (cada fila en una línea).
     *
     * @return Cadena con la matriz formateada.
     */
    @Override
    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();
        for (Vector<T> fila : matriz)
            sb.append(fila.toFormattedString()).append("\n");
        return sb.toString();
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
     * @return La matriz transpuesta.
     */
    public Matriz<T> transponer() {
        int numRows = getNumRow();
        int numCols = getNumCol();
        Matriz<T> trans = new Matriz<>(numCols, numRows); // Crea una matriz con numCols filas y numRows columnas
        for (int i = 0; i < numCols; i++) {
            Vector<T> fila = new Vector<>();
            for (int j = 0; j < numRows; j++) {
                fila.getVector().add(getFila(j).getElemento(i));
            }
            trans.setFila(i, fila); // Reemplaza la fila existente en lugar de agregar una nueva
        }
        return trans;
    }

    /**
     * Método auxiliar para leer una fila de la matriz desde una línea de texto.
     * Se espera que la línea contenga números separados por comas (ej.: "1.0,2.0").
     *
     * @param linea Línea de texto con los elementos de la fila.
     * @param fila  Índice de la fila a asignar.
     * @throws IOException Si ocurre un error al parsear la línea.
     */
    @SuppressWarnings("unchecked")
    public void escribirMatriz(String linea, int fila) throws IOException {
        String[] tokens = linea.split(",");
        Double[] temp = new Double[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            String clean = tokens[i].replaceAll("[\\[\\]]", "").trim();
            temp[i] = Double.valueOf(clean);
        }
        T[] arr = (T[]) temp;
        Vector<T> vector = new Vector<>(arr);
        setFila(fila, vector);
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
    @Override
    public void read(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        int rows = Integer.parseInt(lines.get(0).trim());
        int cols = Integer.parseInt(lines.get(1).trim());
        matriz = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            escribirMatriz(lines.get(i + 2), i);
        }
    }

    /**
     * Lee la matriz desde un Scanner. Se espera que el Scanner contenga:
     * - Primera línea: número de filas.
     * - Segunda línea: número de columnas.
     * - Líneas siguientes: cada fila con elementos separados por comas.
     *
     * @param sc Scanner desde el cual se leerá la matriz.
     * @throws IOException Si no hay suficientes líneas en el Scanner o error al parsear.
     */
    @Override
    public void read(Scanner sc) throws IOException {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine();
        matriz = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                escribirMatriz(line, i);
            } else {
                throw new IOException("No hay suficientes líneas en el Scanner.");
            }
        }
    }
}


