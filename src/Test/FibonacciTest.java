package Test;

import ejercicio1.series.Fibonacci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciTest {

    @Test
    public void testConstructorWithSuperior() {
        Fibonacci fibonacci = new Fibonacci(10);
        assertEquals(1, fibonacci.getLimiteInferior());
        assertEquals(10, fibonacci.getLimiteSuperior());
    }

    @Test
    public void testConstructorWithInferiorAndSuperior() {
        Fibonacci fibonacci = new Fibonacci(5, 15);
        assertEquals(5, fibonacci.getLimiteInferior());
        assertEquals(15, fibonacci.getLimiteSuperior());
    }

    @Test
    public void testGenerarSerie() {
        Fibonacci fibonacci = new Fibonacci(1, 10);
        fibonacci.generarSerie();
        assertEquals("[1, 1, 2, 3, 5, 8]", fibonacci.getSecuenciaGenerada().toString());
    }

    @Test
    public void testEsFibonacci() {
        assertTrue(Fibonacci.esFibonacci(1));
        assertTrue(Fibonacci.esFibonacci(2));
        assertTrue(Fibonacci.esFibonacci(3));
        assertTrue(Fibonacci.esFibonacci(5));
        assertFalse(Fibonacci.esFibonacci(4));
        assertFalse(Fibonacci.esFibonacci(6));
    }

    @Test
    public void testSumar() {
        Fibonacci fibonacci = new Fibonacci(1, 10);
        fibonacci.generarSerie();
        assertEquals(20, fibonacci.sumarSerie());
    }
}