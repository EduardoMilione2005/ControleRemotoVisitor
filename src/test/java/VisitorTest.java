import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class VisitorTest {

    @Test
    void deveLigarTV() {

        TV tv = new TV();
        Visitor ligar = new LigarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        tv.aceitar(ligar);

        assertTrue(output.toString().contains("TV ligada"));
    }

    @Test
    void deveDesligarTV() {

        TV tv = new TV();
        Visitor desligar = new DesligarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        tv.aceitar(desligar);

        assertTrue(output.toString().contains("TV desligada"));
    }

    @Test
    void deveLigarSom() {

        Som som = new Som();
        Visitor ligar = new LigarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        som.aceitar(ligar);

        assertTrue(output.toString().contains("Som ligado"));
    }

    @Test
    void deveDesligarArCondicionado() {

        ArCondicionado ar = new ArCondicionado();
        Visitor desligar = new DesligarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ar.aceitar(desligar);

        assertTrue(output.toString().contains("Ar-condicionado desligado"));
    }
}