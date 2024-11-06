package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

        @Test
        public void testTransaction_to_from_json() {
            Client e1=new Client("MP658589","Brahim","Mohamed","Safi,Morroco","0611223344","mohamedbrahim@gmail.com");
            Client e2=new Client("DE654555","Brahim","hicham","Marrakech,Morroco","0612345678","brahimhicham@gmail.com");
            Banque b=new Banque("AZ0065","Morocco");
            Compte c1 = new Compte("FM5968", "Dollar",b);
            Compte c2 = new Compte("PL2536", "Dollar",b);
            e1.AddCompte(c1);
            e2.AddCompte(c2);
            List<Compte> Crediteurs=new ArrayList<>();
            Crediteurs.add(c2);
            Transaction t = new Transaction(2500.00,c1,Crediteurs);
            String json = t.to_json();
            System.out.println("L'instance de la classe Transaction :\n" + t);
            System.out.println("Sa conversion en chaine JSON :\n" + json);
            Transaction t2 = new Transaction();
            t2.fromjson(json);
            System.out.println("L'instance créée à partir du JSON :\n" + t2);
            assertEquals(t.getReference(), t2.getReference(), "Les references des transactions ne correspond pas après la conversion Transaction->json->Transaction");
            assertEquals(t.getComptesIds().get(0), t2.getComptesIds().get(0), "Les comptes debiteurs ne correspond pas après la conversion ");
            System.out.println("Les données sont identiques ");
        }
    }


