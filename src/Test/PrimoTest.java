package Test;

import ejercicio1.series.Serie;
import ejercicio1.series.Primo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimoTest {

    @Test
    public void testConstructorDefault() {
        Primo primo = new Primo();
        assertEquals(1, primo.getLimiteInferior());
        assertEquals(100, primo.getLimiteSuperior());
    }

    @Test
    public void testConstructorWithSuperior() {
        Primo primo = new Primo(50);
        assertEquals(1, primo.getLimiteInferior());
        assertEquals(50, primo.getLimiteSuperior());
    }

    @Test
    public void testConstructorWithInferiorAndSuperior() {
        Primo primo = new Primo(10, 50);
        assertEquals(10, primo.getLimiteInferior());
        assertEquals(50, primo.getLimiteSuperior());
    }

    @Test
    public void testGenerarSerie() {
        Primo primo = new Primo(1, 10);
        primo.generarSerie();
        assertEquals("[2, 3, 5, 7]", primo.getSecuenciaGenerada().toString());
    }

    @Test
    public void testEsPrimo() {
        assertTrue(Primo.esPrimo(2));
        assertTrue(Primo.esPrimo(3));
        assertFalse(Primo.esPrimo(4));
        assertTrue(Primo.esPrimo(5));
        assertFalse(Primo.esPrimo(9));
    }

    @Test
    public void testSumar() {
        Primo primo = new Primo(1, 10);
        primo.generarSerie();
        assertEquals(17, primo.sumar());
    }
}
