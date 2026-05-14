public class Som implements Dispositivo {

    @Override
    public void aceitar(Visitor visitor) {
        visitor.visitar(this);
    }

    public void ligarSom() {
        System.out.println("Som ligado");
    }

    public void desligarSom() {
        System.out.println("Som desligado");
    }
}