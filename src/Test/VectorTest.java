package Test;

import ejercicio1.arreglos.Vector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class VectorTest {

    @org.junit.Test
    @Test
    public void testConstructorWithElements() {
        Vector<Double> vector = new Vector<>(1.0, 2.0, 3.0);
        assertEquals(3, vector.getLongitud());
        assertEquals(1.0, vector.getElemento(0));
        assertEquals(2.0, vector.getElemento(1));
        assertEquals(3.0, vector.getElemento(2));
    }

    @Test
    public void testConstructorWithString() {
        Vector<Double> vector = new Vector<>("[1.0, 2.0, 3.0]");
        assertEquals(3, vector.getLongitud());
        assertEquals(1.0, vector.getElemento(0));
        assertEquals(2.0, vector.getElemento(1));
        assertEquals(3.0, vector.getElemento(2));
    }

    @Test
    public void testCopyConstructor() {
        Vector<Double> original = new Vector<>(1.0, 2.0, 3.0);
        Vector<Double> copy = new Vector<>(original);
        assertEquals(original.getLongitud(), copy.getLongitud());
        assertEquals(original.getElemento(0), copy.getElemento(0));
        assertEquals(original.getElemento(1), copy.getElemento(1));
        assertEquals(original.getElemento(2), copy.getElemento(2));
    }

    @Test
    public void testSetElemento() {
        Vector<Double> vector = new Vector<>(1.0, 2.0, 3.0);
        vector.setElemento(1, 4.0);
        assertEquals(4.0, vector.getElemento(1));
    }

    @Test
    public void testProductoEscalar() {
        Vector<Double> vector1 = new Vector<>(1.0, 2.0, 3.0);
        Vector<Double> vector2 = new Vector<>(4.0, 5.0, 6.0);
        assertEquals(32.0, vector1.productoEscalar(vector2));
    }

    @Test
    public void testToFormattedString() {
        Vector<Double> vector = new Vector<>(1.0, 2.0, 3.0);
        assertEquals("[1.0, 2.0, 3.0]", vector.toFormattedString());
    }
}
