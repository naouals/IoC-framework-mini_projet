package presentation;

import framework.MyInjector;
import metier.MetierImpl;

public class Main {
    public static void main(String[] args) {
        try {
            MetierImpl metier = (MetierImpl) MyInjector.loadFromXML("config.xml");
            System.out.println("Résultat XML = " + metier.calcul());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
