package ejercicio1.arreglos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase que representa un vector n-dimensional de doubles,
 * usando un ArrayList<Double> para almacenar los coeficientes.
 * Permite mostrar la dimensión, imprimir sus componentes,
 * realizar el producto escalar con otro vector,
 * y leer/escribir sus datos en un fichero.
 * <p>
 * Implementa las interfaces Printable (para proporcionar
 * un toFormattedString) e IWriteable (para leer/escribir en fichero).
 */
public class Vector<T> implements Printable, IWriteable {
    // Se comienza estableciendo un arreglo básico que contendrá los elementos del arreglo
    protected ArrayList<Object> arreglo = new ArrayList<>();

    // -- Constructores --

    /**
     * Constructor base de la clase Vector
     * @param elementos: Componentes del vector
     */
    public Vector(Object... elementos) {	// Cantidad variable de argumentos
        for (Object elemento : elementos)
            this.arreglo.add(elemento);
    }

    /**
     * Constructor de copia
     * @param vector: Vector que se quiere copiar
     */
    public Vector(Vector<T> vector) { this.arreglo = vector.arreglo; }

    /**
     * Constructor en base a strings de la clase Vector
     * @param cadena: Cadena de componentes del vector
     */
    public Vector(String cadena) {
        String[] elementos = cadena.split(",");
        for (String elemento : elementos) {
        	this.arreglo.add((T) elemento.trim());
        }
    }

    // -- Getters y setters --

    /**
     * Getter para obtener el vector por completo
     * @return arreglo del que se compone el vector
     */
    public ArrayList<Object> getVector() {
    	return arreglo;
    }

    /**
     * Getter para obtener el tamaño del vector
     * @return Tamaño del vector
     */
    public int getLongitud() {	return arreglo.size();	}

    /**
     * Setter para establecer un nuevo valor en una posición
     * @param posicion: Posición en la que se quiere establecer el valor
     * @param valor: Valor que se quiere establecer
     */
    public Object getElemento(int posicion) { return arreglo.get(posicion); }

    /**
     * Getter para obtener un valor en una posición
     * @param posicion: Posición del valor que se quiere obtener
     * @return Valor en la posición indicada
     */
    public void setElemento(int posicion, Object elemento) { arreglo.set(posicion, elemento); }

    /**
     * Setter para establecer un elemento en una posición dada
     * @param posicion: Posición de la que se quiere sacar el elemento en cuestión
     * @param elemento: Extraído de la posición posicion
     */
    public void setElemento(int posicion, Object elemento) { arreglo.set(posicion, elemento); }

    /**
     * Imprime la representación formateada del vector.
     */
    public void print() {
        System.out.println(toFormattedString());
    }

    /**
     * Calcula el producto escalar con otro Vector<T>.
     * Convierte los valores a double para la multiplicación.
     */
    public double productoEscalar(Vector<T> otro) {
        if (this.getDimension() != otro.getDimension()) {
            throw new IllegalArgumentException("Dimensiones distintas.");
        }
        double suma = 0.0;
        for (int i = 0; i < this.getDimension(); i++) {
            double val1 = this.data.get(i).doubleValue();
            double val2 = otro.data.get(i).doubleValue();
            suma += val1 * val2;
        }
        return suma;
    }

    /**
     * Escribe los coeficientes del vector en un fichero,
     * separados por comas, en una sola línea.
     * Manejamos IOException con try/catch.
     */
    @Override
    public void write(String filename) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                sb.append(data.get(i).toString());
                if (i < data.size() - 1) {
                    sb.append(",");
                }
            }
            Files.write(Paths.get(filename), sb.toString().getBytes());
        } catch (IOException e) {
            System.err.println("[ERROR] No se pudo escribir el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee un fichero donde haya una sola línea,
     * con valores separados por comas, y reconstruye el ArrayList<T>.
     * Maneja IOException y NumberFormatException con try/catch.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void read(String filename) {
        try {
            String line = Files.readString(Paths.get(filename));
            String[] tokens = line.split(",");
            data.clear();
            for (String t : tokens) {
                try {
                    // Asumiendo que T=Double
                    Double valor = Double.parseDouble(t);
                    data.add((T) valor);
                } catch (NumberFormatException ex) {
                    System.err.println("[ERROR] Formato no válido: " + t);
                }
            }
        } catch (IOException e) {
            System.err.println("[ERROR] No se pudo leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Retorna el contenido del ArrayList en formato [x1, x2, ...].
     */
    @Override
    public String toFormattedString() {
        return data.toString();
    }


}
