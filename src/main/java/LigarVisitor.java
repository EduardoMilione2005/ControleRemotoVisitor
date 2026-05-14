public class LigarVisitor implements Visitor {

    @Override
    public void visitar(TV tv) {
        tv.ligarTV();
    }

    @Override
    public void visitar(Som som) {
        som.ligarSom();
    }

    @Override
    public void visitar(ArCondicionado ar) {
        ar.ligarAr();
    }
}