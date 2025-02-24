package ejercicio1.app;

import java.io.IOException;
import java.util.Scanner;
import ejercicio1.series.Primo;
import ejercicio1.series.Fibonacci;
import ejercicio1.arreglos.Vector;
import ejercicio1.arreglos.Matriz;

/**
 * Menú principal para la Práctica 1 de Programación Orientada a Objetos.
 * Permite seleccionar entre:
 *  1. Mostrar números primos en un intervalo y su suma.
 *  2. Mostrar los n primeros elementos de la sucesión Fibonacci.
 *  3. Operaciones con Vectores (crear, producto escalar, guardar y cargar desde fichero).
 *  4. Operaciones con Matrices (crear, guardar y cargar desde fichero).
 *  0. Salir.
 */
public class MainMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            printMainMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea
            switch (opcion) {
                case 1:
                    handlePrimos(sc);
                    break;
                case 2:
                    handleFibonacci(sc);
                    break;
                case 3:
                    handleVectores(sc);
                    break;
                case 4:
                    handleMatrices(sc);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMainMenu() {
        System.out.println("======================================");
        System.out.println("       MENU PRINCIPAL - PRÁCTICA 1    ");
        System.out.println("======================================");
        System.out.println("1. Números Primos");
        System.out.println("2. Serie Fibonacci");
        System.out.println("3. Operaciones con Vectores");
        System.out.println("4. Operaciones con Matrices");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void handlePrimos(Scanner sc) {
        System.out.print("Ingrese el límite superior para primos: ");
        int limite = sc.nextInt();
        sc.nextLine();
        Primo primos = new Primo(1, limite);
        primos.generarSerie();
        System.out.println("Números primos:");
        primos.print();
        System.out.println("Suma de primos: " + primos.sumar());
    }

    private static void handleFibonacci(Scanner sc) {
        System.out.print("Ingrese el número de elementos de Fibonacci: ");
        int n = sc.nextInt();
        sc.nextLine();
        Fibonacci fib = new Fibonacci(n);
        fib.generarSerie();
        System.out.println("Serie Fibonacci:");
        fib.print();
    }

    private static void handleVectores(Scanner sc) {
        int subOpcion = -1;
        while (subOpcion != 0) {
            System.out.println("----- OPERACIONES CON VECTORES -----");
            System.out.println("1. Crear y mostrar vector (teclado)");
            System.out.println("2. Producto escalar de dos vectores");
            System.out.println("3. Guardar vector en fichero");
            System.out.println("4. Cargar vector desde fichero");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            subOpcion = sc.nextInt();
            sc.nextLine();
            switch (subOpcion) {
                case 1:
                    System.out.print("Ingrese los componentes del vector separados por coma: ");
                    String vecStr = sc.nextLine();
                    Vector<Double> v = new Vector<>(vecStr);
                    System.out.println("Vector: " + v.toFormattedString());
                    break;
                case 2:
                    System.out.print("Ingrese los componentes del primer vector separados por coma: ");
                    String vec1 = sc.nextLine();
                    System.out.print("Ingrese los componentes del segundo vector separados por coma: ");
                    String vec2 = sc.nextLine();
                    Vector<Double> vector1 = new Vector<>(vec1);
                    Vector<Double> vector2 = new Vector<>(vec2);
                    System.out.println("Producto escalar: " + vector1.productoEscalar(vector2));
                    break;
                case 3:
                    System.out.print("Ingrese los componentes del vector a guardar (separados por coma): ");
                    String vecSave = sc.nextLine();
                    Vector<Double> vSave = new Vector<>(vecSave);
                    System.out.print("Ingrese el nombre del fichero: ");
                    String fileV = sc.nextLine();
                    try {
                        vSave.write(fileV);
                        System.out.println("Vector guardado en " + fileV);
                    } catch (IOException e) {
                        System.err.println("Error al guardar el vector: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del fichero para cargar el vector: ");
                    String fileLoad = sc.nextLine();
                    Vector<Double> vLoad = new Vector<>("0"); // vector dummy para ser reemplazado
                    try {
                        vLoad.read(fileLoad);
                        System.out.println("Vector cargado: " + vLoad.toFormattedString());
                    } catch (IOException e) {
                        System.err.println("Error al cargar el vector: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
            System.out.println();
        }
    }

    private static void handleMatrices(Scanner sc) {
        int subOpcion = -1;
        while (subOpcion != 0) {
            System.out.println("----- OPERACIONES CON MATRICES -----");
            System.out.println("1. Crear y mostrar matriz (teclado)");
            System.out.println("2. Producto de Matrices");
            System.out.println("3. Guardar matriz en fichero");
            System.out.println("4. Cargar matriz desde fichero");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            subOpcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea
            switch (subOpcion) {
                case 1:
                    System.out.print("Ingrese el número de filas: ");
                    int filas = sc.nextInt();
                    System.out.print("Ingrese el número de columnas: ");
                    int columnas = sc.nextInt();
                    sc.nextLine(); // Consumir salto de línea
                    double[][] datos = new double[filas][columnas];
                    for (int i = 0; i < filas; i++) {
                        System.out.print("Ingrese los elementos de la fila " + (i + 1) + " separados por espacio: ");
                        String[] elementos = sc.nextLine().split(" ");
                        for (int j = 0; j < columnas; j++) {
                            datos[i][j] = Double.parseDouble(elementos[j]);
                        }
                    }
                    // Usamos el constructor que recibe filas, columnas y datos
                    Matriz<Double> matriz = new Matriz<>(filas, columnas, datos);
                    System.out.println("Matriz creada:");
                    matriz.print();
                    break;
                case 2:
                    System.out.print("Ingrese el número de filas de la primera matriz: ");
                    int filas1 = sc.nextInt();
                    System.out.print("Ingrese el número de columnas de la primera matriz: ");
                    int columnas1 = sc.nextInt();
                    sc.nextLine(); // Consumir salto de línea
                    double[][] datos1 = new double[filas1][columnas1];
                    for (int i = 0; i < filas1; i++) {
                        System.out.print("Ingrese los elementos de la fila " + (i + 1) + " de la primera matriz separados por espacio: ");
                        String[] elementos1 = sc.nextLine().split(" ");
                        for (int j = 0; j < columnas1; j++) {
                            datos1[i][j] = Double.parseDouble(elementos1[j]);
                        }
                    }
                    Matriz<Double> matriz1 = new Matriz<>(filas1, columnas1, datos1);

                    System.out.print("Ingrese el número de filas de la segunda matriz: ");
                    int filas2 = sc.nextInt();
                    System.out.print("Ingrese el número de columnas de la segunda matriz: ");
                    int columnas2 = sc.nextInt();
                    sc.nextLine(); // Consumir salto de línea
                    double[][] datos2 = new double[filas2][columnas2];
                    for (int i = 0; i < filas2; i++) {
                        System.out.print("Ingrese los elementos de la fila " + (i + 1) + " de la segunda matriz separados por espacio: ");
                        String[] elementos2 = sc.nextLine().split(" ");
                        for (int j = 0; j < columnas2; j++) {
                            datos2[i][j] = Double.parseDouble(elementos2[j]);
                        }
                    }
                    Matriz<Double> matriz2 = new Matriz<>(filas2, columnas2, datos2);
                    Matriz<Double> producto = matriz1.producto(matriz2);
                    System.out.println("Producto de matrices:");
                    producto.print();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del fichero para guardar la matriz: ");
                    String fileSave = sc.nextLine();
                    System.out.print("Ingrese el número de filas: ");
                    int filasSave = sc.nextInt();
                    System.out.print("Ingrese el número de columnas: ");
                    int columnasSave = sc.nextInt();
                    sc.nextLine(); // Consumir salto de línea
                    double[][] datosSave = new double[filasSave][columnasSave];
                    for (int i = 0; i < filasSave; i++) {
                        System.out.print("Ingrese los elementos de la fila " + (i + 1) + " separados por espacio: ");
                        String[] elementosSave = sc.nextLine().split(" ");
                        for (int j = 0; j < columnasSave; j++) {
                            datosSave[i][j] = Double.parseDouble(elementosSave[j]);
                        }
                    }
                    Matriz<Double> matrizSave = new Matriz<>(filasSave, columnasSave, datosSave);
                    try {
                        matrizSave.write(fileSave);
                        System.out.println("Matriz guardada en " + fileSave);
                    } catch (IOException e) {
                        System.err.println("Error al guardar la matriz: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del fichero para cargar la matriz: ");
                    String fileLoad = sc.nextLine();
                    Matriz<Double> matrizLoad = new Matriz<>();
                    try {
                        matrizLoad.read(fileLoad);
                        System.out.println("Matriz cargada:");
                        matrizLoad.print();
                    } catch (IOException e) {
                        System.err.println("Error al cargar la matriz: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
            System.out.println();
        }
        sc.close();
    }
}
