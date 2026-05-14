public class TV implements Dispositivo {

    @Override
    public void aceitar(Visitor visitor) {
        visitor.visitar(this);
    }

    public void ligarTV() {
        System.out.println("TV ligada");
    }

    public void desligarTV() {
        System.out.println("TV desligada");
    }
}