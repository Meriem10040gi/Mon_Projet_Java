package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @Expose
    private String NumCompte;
    @Expose
    private Date DateCreation;
    @Expose
    private Date DateUpdate;
    @Expose
    private String Devise;
    @Expose
    private Banque PBanque;
    @Expose
    private List<Transaction> Transactions = new ArrayList<>();

    public Compte(String id, String devise, Banque b) {
        this();
        this.NumCompte = id;
        this.DateCreation = new Date();
        this.DateUpdate = this.DateCreation;
        this.Devise = devise;
        this.PBanque = b;
    }

    public void AddTransaction(Transaction t) {
        Transactions.add(t);
        if (!t.getComptesIds().contains(this.NumCompte)) {
            t.getComptesIds().add(this.NumCompte);
        }
    }

    @Override
    public String toString() {
        String info = "Numero Compte : " + NumCompte + " Date de creation : " + DateCreation +
                " Date de modification : " + DateUpdate + " Devise : " + Devise + " Banque : " + PBanque.getId() + "\n";
        info += "Transaction Reference   Date   Montant  Type  Compte Debit  Compte Credit\n";
        if(Transactions!=null){
        for (Transaction t : Transactions) {
            info += t.getReference() + "  " + t.getTimestamp() + "  " + t.getAmount() + " " + t.getType() + " ";
            for (String c : t.getComptesIds()) info += c + "  ";
            info += "\n";
        }}
        return info;
    }

    public String to_json() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }

    public void fromjson(String json) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Compte c = gson.fromJson(json, Compte.class);
        this.NumCompte = c.getNumCompte();
        this.DateCreation = c.getDateCreation();
        this.DateUpdate = c.getDateUpdate();
        this.Devise = c.getDevise();
        this.PBanque = c.getPBanque();
        this.Transactions = c.getTransactions();
    }
}
