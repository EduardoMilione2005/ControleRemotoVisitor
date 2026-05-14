public class Main {

    public static void main(String[] args) {

        Dispositivo tv = new TV();
        Dispositivo som = new Som();
        Dispositivo ar = new ArCondicionado();

        Visitor ligar = new LigarVisitor();
        Visitor desligar = new DesligarVisitor();

        System.out.println("=== LIGANDO DISPOSITIVOS ===");

        tv.aceitar(ligar);
        som.aceitar(ligar);
        ar.aceitar(ligar);

        System.out.println();

        System.out.println("=== DESLIGANDO DISPOSITIVOS ===");

        tv.aceitar(desligar);
        som.aceitar(desligar);
        ar.aceitar(desligar);
    }
}