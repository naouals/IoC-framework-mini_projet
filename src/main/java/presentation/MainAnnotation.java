package presentation;

import framework.MyInjector;
import metier.MetierImpl;
import dao.DaoImpl;

public class MainAnnotation {
    public static void main(String[] args) throws Exception {
        MyInjector injector = new MyInjector();
        injector.loadFromAnnotations(DaoImpl.class, MetierImpl.class);

        MetierImpl metier = (MetierImpl) injector.getBean("MetierImpl");
        System.out.println("Résultat Annotation = " + metier.calcul());
    }
}
