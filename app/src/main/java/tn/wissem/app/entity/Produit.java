package tn.wissem.app.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "produit_table")
public class Produit {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "libelle")
    private String libelle;

    @ColumnInfo(name = "prix")
    private Double prix;

    @ColumnInfo(name = "quantite")
    private int quantite;

    @ColumnInfo(name = "marque")
    private String marque;

    public Produit(String libelle, Double prix, int quantite, String marque) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.prix = prix;
        this.marque = marque;
    }

    @Ignore
    public Produit(String libelle, Double prix, int quantite) {
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "uid=" + uid +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", quantite='" + quantite + '\'' +
                ", marque='" + marque + '\'' +
                '}';
    }
}
