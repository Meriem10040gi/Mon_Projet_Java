package org.example;
import com.google.gson.Gson;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banque {
    private String Id;
    private String Pays;


    public Client AddClient(String id, String nom, String prenom, String address, String email, String phone) {
        return new Client(id, nom, prenom, address, phone, email);
    }

    public Compte CreateAccount(String id, String devise) {
        return new Compte(id, devise, this);
    }

    public void AddCompteClient(Compte c, Client e) {
        e.AddCompte(c);
    }

    @Override
    public String toString(){
        String info="Numuro Banque : "+Id+" Pays : "+Pays+"\n" ;
        return info;
    }

    public String to_json() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void fromjson(String json) {
        Gson gson = new Gson();
        Banque b = gson.fromJson(json, Banque.class);
        this.Id = b.getId();
        this.Pays = b.getPays();
    }
}
