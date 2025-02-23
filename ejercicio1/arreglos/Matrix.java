package ejercicio1.arreglos;

public class Matrix<T> implements Printable, IWriteable {
    // Se comienza estableciendo un array de vectores que contendrá los elementos de la matriz
    protected ArrayList<Vector<T> > matriz = new ArrayList<Vector<T> >();

    // -- Constructores -- 

    /**
     * Constructor que crea una matriz de dimensión 1x1 con un único elemento 0.
     */
    public Matriz() {
        Vector<T> fila = new Vector<>();
        addElemento(fila, 1, null);
    }

    /**
     * Constructor que crea una matriz de dimensión mxn con todos sus elementos a 0.
     * @param m Número de filas
     * @param n Número de columnas
     */
    public Matriz(int m, int n) {
        for (int i = 0; i < m; i++) {
            Vector<T> fila = new Vector<>();
            addElemento(fila, n, null);
        }
    }

    /**
     * Constructor que crea una matriz de dimensión mxn con los elementos del array bidimensional coef.
     * @param m Número de filas.
     * @param n Número de columnas.
     * @param coef Array bidimensional con los coeficientes de la matriz.
     */
    public Matriz(int m, int n, double[][] coef) {
        for (int i = 0; i < m; i++) {
            Vector<T> fila = new Vector<>();
            for (int j = 0; j < n; j++) { fila.getVector().add((Double) coef[i][j]); }
            matriz.add(fila);
        }
    }

    // -- Getters y setters --

    /**
     * Getter para obtener el número de filas de la matriz
     * @return Número de filas de la matriz
     */
    public int getNumRow() {	return matriz.size();	}

    /**
     * Getter para obtener el número de columnas de la matriz
     * @return Número de columnas de la matriz
     */
    public int getNumCol() {
        if (matriz.isEmpty()) {	return 0;	}
        return matriz.get(0).getLongitud();
    }

    /**
     * Getter para obtener un vector fila de la matriz
     * @param indice: Índice de la fila que se quiere obtener
     * @return Vector fila correspondiente al índice dado
     */
    public Vector<T> getFila(int indice) {	return matriz.get(indice);	}

    /**
     * Setter para establecer un vector fila en una posición dada
     * @param indice: Índice de la fila en la que se quiere establecer el vector
     * @param fila: Vector fila a establecer
     */
    public void setFila(int indice, Vector<T> fila) {
        filaVacia(indice);
        matriz.set(indice, fila);
    }

    // -- Métodos auxiliares --

    /**
     * Método para eliminar una fila de la matriz
     * @param indice: posición en que se encuentra la fila a eliminar
     */
    public void eliminarFila(int indice) {	matriz.remove(indice);	}

    /**
     * Método que intenta agregar las filas necesarias hasta que la matriz cumpla con
     * las condiciones de espacio requeridas
     * @param indice: numero de 
     */
    public void filaVacia(int indice) {
        while (matriz.size() <= indice) {
            Vector<T> nuevaFila = new Vector<>();
            addElemento(nuevaFila, getNumCol(), null); // Agregar fila vacía hasta alcanzar el índice deseado
        }
    }

    /**
     * Método para actualizar el número de columnas si la longitud de la fila 
     * es mayor que el número actual de columnas
     * @param columnas: Número actual de columnas en la matriz
     * @param fila: Vector fila a establecer
     */
    public void actualizarColumnas(int columnas, Vector<T> fila) {
        ajustarColumnasNulas(fila);
        eliminarColumnasExcedentes(fila);
    }

    /**
     * Método para agregar columnas nulas si la longitud de la fila es mayor que el número actual de columnas
     * @param fila: Vector fila a establecer
     */
    public void ajustarColumnasNulas(Vector<T> fila) {
        if (fila.getLongitud() > getNumCol()) {
            for (Vector<T> vec : matriz) {
                vec.getVector().add(null); // Añadir valores nulos para igualar la longitud de todas las filas
            }
        }
    }

    /**
     * Método para eliminar columnas excedentes si la longitud de la fila es menor que el número actual de columnas
     * @param fila: Vector fila a establecer
     */
    public void eliminarColumnasExcedentes(Vector<T> fila) {
        if (fila.getLongitud() <  getNumCol()) {
            for (Vector<T> vec : matriz) {
                while (vec.getLongitud() > fila.getLongitud()) {
                    vec.getVector().remove(vec.getLongitud() - 1); // Eliminar elementos excedentes
                }
            }
        }
    }

    /**
     * Método que añade filas que contienen un único elemento vacío dentro de la matriz.
     * @param fila: Fila a añadir en la matriz.
     * @param n: Número de valores nulos que se quieren añadir en la matriz
     */
    public void addElemento(Vector<T> fila, int n, T elemento) {
        for (int j = 0; j < n; j++)
            fila.getVector().add(elemento); // Agregar un elemento nulo para luego ser inicializado
        matriz.add(fila); // Agrega un vector con un único elemento 0.0
    }

    /**
     * Método que convierte una matriz a un tipo String
     */
    public String toFormattedString() {
        String stringADevolver = "";
        for (Vector<T> fila : matriz) {
            stringADevolver += fila.toString() + "\n";
        }
        return stringADevolver;
    }

    /**
     * Método para mostrar una matriz por pantalla
     */
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Valida si las dimensiones de las matrices son compatibles para realizar el producto.
     * @param matriz: La segunda matriz con la que se realiza el producto.
     * @throws IllegalArgumentException si el número de columnas de esta matriz no es igual al número de filas de la matrizB.
     */
    private void validarDimensiones(Matriz<T> matriz) {
        if (this.getNumCol() != matriz.getNumRow()) {
            throw new IllegalArgumentException("No se puede realizar el producto de matrices: las dimensiones no son compatibles.");
        }
    }

    /**
     * Calcula el producto de dos matrices y guarda el resultado en la matrizC.
     * @param matrizB La segunda matriz con la que se realiza el producto.
     * @param matrizC La matriz resultante del producto.
     */
    private void calcularProducto(Matriz<T> matrizB, Matriz<T> matrizC) {
        for (int i = 0; i < matrizC.getNumRow(); i++) {
            for (int j = 0; j < matrizC.getNumCol(); j++) {
                double valor = calcularValorElemento(i, j, matrizB);
                matrizC.getFila(i).setElemento(j, valor);
            }
        }
    }

    /**
     * Calcula el valor de un elemento de la matriz resultante (matrizC) en la posición (i, j).
     * @param i Índice de fila en la matrizC.
     * @param j Índice de columna en la matrizC.
     * @param matrizB La segunda matriz con la que se realiza el producto.
     * @return El valor del elemento en la posición (i, j) de la matrizC.
     */
    private double calcularValorElemento(int i, int j, Matriz<T> matrizB) {
        double valor = 0.0;
        for (int k = 0; k < this.getNumCol(); k++) {	valor += (Double.parseDouble((String) this.getFila(i).getElemento(k)) * (Double.parseDouble((String) matrizB.getFila(k).getElemento(j))));	}
        return valor;
    }

    /**
     * Método que realiza el producto de dos matrices.
     * @param matriz: La segunda matriz con la que se realiza el producto.
     * @return La matriz resultante del producto.
     * @throws IllegalArgumentException si el número de columnas de esta matriz no es igual al número de filas de la matrizB.
     */
    public Matriz<T> producto(Matriz<T> matriz) throws IllegalArgumentException {
        validarDimensiones(matriz);
        Matriz<T> resultado = new Matriz<T>(this.matriz.size(), matriz.getFila(0).getLongitud());
        calcularProducto(matriz, resultado);
        return resultado;
    }

    /**
     * Método para generar una matriz a partir de los elementos de un fichero de texto
     * @param scanner: Referencia a un Scanner
     * @param filas: número de filas de la matriz
     * @param columnas: número de columnas de la matriz
     * @throws IOException
     */
    public void escribirMatriz(String linea, int fila) throws IOException {
        Vector<T> vector = new Vector<T>(linea);
        this.setFila(fila, vector);
    }

    /**
     * Método para transponer la matriz, es decir, cambiar las filas por columnas y viceversa.
     * @return La matriz transpuesta.
     */
    public Matriz<T> transponer() {
        Matriz<T> matrizTranspuesta = new Matriz<>(this.getNumCol(), this.getNumRow());
        for (int i = 0; i < this.getNumCol(); i++) {
            Vector<T> filaTranspuesta = obtenerFilaTranspuesta(i);
            matrizTranspuesta.setFila(i, filaTranspuesta);
        }
        return matrizTranspuesta;
    }

    /**
     * Obtiene una fila de la matriz transpuesta a partir de una columna de la matriz original.
     * @param indiceColumna El índice de la columna de la matriz original.
     * @return La fila de la matriz transpuesta correspondiente a la columna especificada.
     */
    private Vector<T> obtenerFilaTranspuesta(int indiceColumna) {
        Vector<T> filaTranspuesta = new Vector<>();
        for (int j = 0; j < this.getNumRow(); j++) {
            filaTranspuesta.getVector().add(this.getFila(j).getElemento(indiceColumna));
        }
        return filaTranspuesta;
    }

    // -- Método read --

    /**
     * Método para leer una matriz desde una referencia Archivo
     * @param Archivo: Archivo de texto de entrada
     * @throws IOException
     */
    public Matriz<T> read(final File archivo) throws IOException {
        return read(new Scanner(archivo));
    }

    /**
     * Método para leer una matriz desde una referencia Scanner
     * @param scanner: Referencia a un Scanner
     * @return matriz leída
     * @throws IOException
     */
    public Matriz<T> read(final Scanner scanner) throws IOException {
        int filas = scanner.nextInt(), columnas = scanner.nextInt(), indice = 0;
        Matriz<T> matriz = new Matriz<T>(filas, columnas);	scanner.nextLine();
        while (scanner.hasNext()) {	matriz.escribirMatriz(scanner.nextLine(), indice++);	}
        return matriz;
    }

    // -- Método write --

    /**
     * Método para escribir los componentes de la matriz en un archivo de texto
     * @param archivo: Archivo de texto de salida
     * @throws IOException
     */
    public void write(final File archivo) throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(this.getNumRow() + "\n" + this.getNumCol() + "\n" + this.toString());
        }
    }

}
