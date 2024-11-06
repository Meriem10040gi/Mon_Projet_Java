package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Expose
    private String NumClient;
    @Expose
    private String Nom;
    @Expose
    private String Prenom;
    @Expose
    private String Address;
    @Expose
    private String Phone;
    @Expose
    private String Email;
    @Expose
    private List<Compte> Comptes = new ArrayList<>();

    public Client(String id, String nom, String prenom, String address, String phone, String email) {
        super();
        this.NumClient = id;
        this.Nom = nom;
        this.Prenom = prenom;
        this.Address = address;
        this.Phone = phone;
        this.Email = email;
    }

    public void AddCompte(Compte c) {
        Comptes.add(c);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("Numero Client : " + NumClient + " Nom Complet : " + Nom + " " + Prenom +
                " Address : " + Address + " Phone : " + Phone + " Email : " + Email + "\n");
        info.append("Numero Compte   Date de creation   Date de modification  Devise\n");
        for (Compte c : Comptes) {
            info.append(c.getNumCompte()).append("  ")
                    .append(c.getDateCreation()).append("  ")
                    .append(c.getDateUpdate()).append("  ")
                    .append(c.getDevise()).append("\n");
        }
        return info.toString();
    }

    public String to_json() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }

    public void fromjson(String json) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Client e = gson.fromJson(json, Client.class);
        this.NumClient = e.getNumClient();
        this.Nom = e.getNom();
        this.Prenom = e.getPrenom();
        this.Address = e.getAddress();
        this.Phone = e.getPhone();
        this.Email = e.getEmail();
        this.Comptes = e.getComptes();
    }
}
