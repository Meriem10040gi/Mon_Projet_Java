package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest{

        @Test
        public void testClient_to_from_json() {
            Client e = new Client();
            e.setNumClient("DE654555");
            e.setPrenom("Mohamed");
            e.setNom("hicham");
            e.setAddress("Marrakech,Morroco");
            e.setEmail("mohamedhicham@gmail.com");
            e.setPhone("0612345678");
            Banque b=new Banque("AZ0065","Morocco");
            Compte c = new Compte("Fc1235", "Dollar",b);
            e.AddCompte(c);
            String json = e.to_json();
            System.out.println("L'instance de la classe Client :\n" + e);
            System.out.println("Sa conversion en chaine JSON :\n" + json);
            Client e2 = new Client();
            e2.fromjson(json);
            System.out.println("L'instance créée à partir du JSON :\n" + e2);
            assertEquals(e.getNumClient(), e2.getNumClient(), "Les ids des client ne correspond pas après la conversion Client->json->Client.");
            assertEquals(e.getComptes().get(0).getNumCompte(), e2.getComptes().get(0).getNumCompte(), "Les comptes ne correspond pas après la conversion ");
            System.out.println("Les données sont identiques ");
        }
    }

