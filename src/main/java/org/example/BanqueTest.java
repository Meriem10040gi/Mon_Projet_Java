package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BanqueTest {
    @Test
    public void testBanque_from_to_json() {
        Banque b = new Banque();
        b.setId("A31231");
        b.setPays("Morocco");
        Compte c = b.CreateAccount("Fc1235", "Dollar");
        Client e = b.AddClient("G112252654", "Mohamed", "Hicham", "Gueliz, Marrakech", "mohamedhicham@gmail.com", "0677995544");
        b.AddCompteClient(c, e);
        String json = b.to_json();
        System.out.println("L'instance de la classe Banque :\n" + b);
        System.out.println("Sa conversion en chaine JSON :\n" + json);
        Banque b2 = new Banque();
        b2.fromjson(json);
        System.out.println("L'instance créée à partir du JSON :\n" + b2);
        assertEquals(b.getId(), b2.getId(), "L'ID de la banque ne correspond pas après la conversion banque->json->banque.");
        assertEquals(b.getPays(), b2.getPays(), "Le pays de la banque ne correspond pas après la conversion banque->json->banque.");
        System.out.println("Les données sont identiques ");
    }
}
