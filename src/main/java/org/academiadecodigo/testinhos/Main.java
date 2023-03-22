package org.academiadecodigo.testinhos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        LeagueService service = new LeagueService();
        service.setEmf(emf);

        League p1 = new League("fighter", "jungler", "Graves");
        League p2 = new League("mage", "jungler", "Diana");
        League p3 = new League("tank", "jungler", "Udyr");
        League p4 = new League("marksman", "bottom", "Caitlyn");
        League p5 = new League("marksman", "bottom", "Ezreal");
        League p6 = new League("mage", "middle", "Ryze");

        p1.setItem(new Item("eclipse"));
        p2.setItem(new Item("jakSho"));
        p3.setItem(new Item("jakSho"));
        p4.setItem(new Item("galeforce"));
        p5.setItem(new Item("shieldbow"));
        p6.setItem(new Item("rod of ages"));

        service.add(p1);
        service.add(p2);
        service.add(p3);
        service.add(p4);
        service.add(p5);
        service.add(p6);

        System.out.println(service.get(3));



        emf.close();

    }
}
