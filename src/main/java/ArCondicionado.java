public class ArCondicionado implements Dispositivo {

    @Override
    public void aceitar(Visitor visitor) {
        visitor.visitar(this);
    }

    public void ligarAr() {
        System.out.println("Ar-condicionado ligado");
    }

    public void desligarAr() {
        System.out.println("Ar-condicionado desligado");
    }
}