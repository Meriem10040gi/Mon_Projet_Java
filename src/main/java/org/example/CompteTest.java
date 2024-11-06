package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompteTest {

    @Test
    public void testCompte_to_from_json() {
        Client e1=new Client("MP658589","Brahim","Mohamed","Safi,Morroco","0611223344","mohamedbrahim@gmail.com");
        Client e2=new Client("DE654555","Brahim","hicham","Marrakech,Morroco","0612345678","brahimhicham@gmail.com");
        Banque b=new Banque("AZ0065","Morocco");
        Compte c = new Compte("FM5968", "Dollar",b);
        Compte c2 = new Compte("PL2536", "Dollar",b);
        e1.AddCompte(c);
        e2.AddCompte(c2);
        List<Compte> Crediteurs=new ArrayList<>();
        Crediteurs.add(c2);
        Transaction t = new Transaction(2500.00,c,Crediteurs);
        c.AddTransaction(t);
        String json = c.to_json();
        System.out.println("L'instance de la classe Compte :\n" + c);
        System.out.println("Sa conversion en chaine JSON :\n" + json);
        Compte c3 = new Compte();
        c3.fromjson(json);
        System.out.println("L'instance créée à partir du JSON :\n" + c3);
        assertEquals(c.getNumCompte(), c3.getNumCompte(), "Les numeros des comptes ne correspond pas après la conversion Compte->json->Compte");
        System.out.println("Les données sont identiques ");
    }
}


