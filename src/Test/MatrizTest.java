package Test;

import ejercicio1.arreglos.Matriz;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MatrizTest {

    @Test
    public void testConstructorDefault() {
        Matriz<Double> matriz = new Matriz<>();
        assertEquals(1, matriz.getNumRow());
        assertEquals(1, matriz.getNumCol());
        assertEquals(0.0, matriz.getFila(0).getElemento(0));
    }

    @Test
    public void testConstructorWithDimensions() {
        Matriz<Double> matriz = new Matriz<>(3, 3);
        assertEquals(3, matriz.getNumRow());
        assertEquals(3, matriz.getNumCol());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0.0, matriz.getFila(i).getElemento(j));
            }
        }
    }

    @Test
    public void testConstructorWithArray() {
        double[][] coef = {{1.0, 2.0}, {3.0, 4.0}};
        Matriz<Double> matriz = new Matriz<>(2, 2, coef);
        assertEquals(2, matriz.getNumRow());
        assertEquals(2, matriz.getNumCol());
        assertEquals(1.0, matriz.getFila(0).getElemento(0));
        assertEquals(2.0, matriz.getFila(0).getElemento(1));
        assertEquals(3.0, matriz.getFila(1).getElemento(0));
        assertEquals(4.0, matriz.getFila(1).getElemento(1));
    }

    @Test
    public void testProducto() {
        double[][] coef1 = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] coef2 = {{2.0, 0.0}, {1.0, 2.0}};
        Matriz<Double> matriz1 = new Matriz<>(2, 2, coef1);
        Matriz<Double> matriz2 = new Matriz<>(2, 2, coef2);
        Matriz<Double> producto = matriz1.producto(matriz2);
        assertEquals(2, producto.getNumRow());
        assertEquals(2, producto.getNumCol());
        assertEquals(4.0, producto.getFila(0).getElemento(0));
        assertEquals(4.0, producto.getFila(0).getElemento(1));
        assertEquals(10.0, producto.getFila(1).getElemento(0));
        assertEquals(8.0, producto.getFila(1).getElemento(1));
    }

    @Test
    public void testTransponer() {
        double[][] coef = {{1.0, 2.0}, {3.0, 4.0}};
        Matriz<Double> matriz = new Matriz<>(2, 2, coef);
        Matriz<Double> transpuesta = matriz.transponer();
        assertEquals(2, transpuesta.getNumRow());
        assertEquals(2, transpuesta.getNumCol());
        assertEquals(1.0, transpuesta.getFila(0).getElemento(0));
        assertEquals(3.0, transpuesta.getFila(0).getElemento(1));
        assertEquals(2.0, transpuesta.getFila(1).getElemento(0));
        assertEquals(4.0, transpuesta.getFila(1).getElemento(1));
    }

    @Test
    public void testWriteAndRead() throws IOException {
        double[][] coef = {{1.0, 2.0}, {3.0, 4.0}};
        Matriz<Double> matriz = new Matriz<>(2, 2, coef);
        String filename = "test_matriz.txt";
        matriz.write(filename);

        // Leer el archivo para verificar que se ha escrito correctamente
        // Ahora el archivo debe contener directamente las filas, sin las dimensiones
        String fileContent = Files.readString(Paths.get(filename));
        assertTrue(fileContent.contains("[1.0, 2.0]"));
        assertTrue(fileContent.contains("[3.0, 4.0]"));

        // Leer la matriz desde el archivo
        Matriz<Double> matrizLeida = new Matriz<>();
        matrizLeida.read(filename);

        // Verificar que se han leído correctamente las dimensiones y los datos
        assertEquals(2, matrizLeida.getNumRow());
        assertEquals(2, matrizLeida.getNumCol());
        assertEquals(1.0, matrizLeida.getFila(0).getElemento(0));
        assertEquals(2.0, matrizLeida.getFila(0).getElemento(1));
        assertEquals(3.0, matrizLeida.getFila(1).getElemento(0));
        assertEquals(4.0, matrizLeida.getFila(1).getElemento(1));

        Files.delete(Paths.get(filename));
    }

    @Test
    public void testReadFromScanner() throws IOException {
        // La cadena ahora solo contiene los vectores, sin dimensiones
        String data = "[1.0, 2.0]\n[3.0, 4.0]\n";
        Scanner sc = new Scanner(data);
        Matriz<Double> matriz = new Matriz<>();
        matriz.read(sc);
        assertEquals(2, matriz.getNumRow());
        assertEquals(2, matriz.getNumCol());
        assertEquals(1.0, matriz.getFila(0).getElemento(0));
        assertEquals(2.0, matriz.getFila(0).getElemento(1));
        assertEquals(3.0, matriz.getFila(1).getElemento(0));
        assertEquals(4.0, matriz.getFila(1).getElemento(1));
    }
}