package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@NoArgsConstructor
@Data
public class Transaction {
    @Expose
    private Date Timestamp;
    @Expose
    private String Reference;
    @Expose
    private List<String> ComptesIds = new ArrayList<>();
    @Expose
    private Types Type;
    @Expose
    private double Amount;

    public Transaction(double amount, Compte Debit, List<Compte> Credit) {
        super();
        this.Reference = UUID.randomUUID().toString();
        this.Amount = amount;
        this.Timestamp = new Date();
        this.Type = determineTransactionType(Debit, Credit);
        this.ComptesIds.add(Debit.getNumCompte());
        for (Compte c : Credit) {
            this.ComptesIds.add(c.getNumCompte());
        }
    }

    private Types determineTransactionType(Compte Debit, List<Compte> Credit) {
        if (Credit.size() == 1) {
            Compte creditCompte = Credit.get(0);
            if (Debit.getPBanque().equals(creditCompte.getPBanque())) {
                return Types.VIRINT;
            } else if (Debit.getPBanque().getPays().equals(creditCompte.getPBanque().getPays())) {
                return Types.VIREST;
            } else {
                return Types.VIRCHAC;
            }
        }
        return Types.VIRMULTA;
    }

    @Override
    public String toString(){
        String info="Transaction Reference   "+" Date   "+" Montant  "+" Type  "+" Compte Debit  "+" Compte Credit  "+"\n";
        info += Reference + "  " + Timestamp + "  " + Amount + " " + Type + " ";
        for (String c : ComptesIds) info += c + "  ";
        info += "\n";
        return info;
    }

    public String to_json() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }
    public void fromjson(String json) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Transaction t = gson.fromJson(json, Transaction.class);
        this.Reference = t.getReference();
        this.Timestamp = t.getTimestamp();
        this.ComptesIds = t.getComptesIds();
        this.Type = t.getType();
        this.Amount = t.getAmount();
    }
}
