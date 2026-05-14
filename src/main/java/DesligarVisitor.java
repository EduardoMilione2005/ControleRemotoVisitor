public class DesligarVisitor implements Visitor {

    @Override
    public void visitar(TV tv) {
        tv.desligarTV();
    }

    @Override
    public void visitar(Som som) {
        som.desligarSom();
    }

    @Override
    public void visitar(ArCondicionado ar) {
        ar.desligarAr();
    }
}