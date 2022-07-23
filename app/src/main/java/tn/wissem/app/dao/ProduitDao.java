package tn.wissem.app.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import tn.wissem.app.entity.Produit;

@Dao
public interface ProduitDao {

    @Insert
    void insertOne(Produit pdt);

    @Delete
    void delete(Produit pdt);

    @Query("SELECT * FROM produit_table")
    List<Produit> getAllProduits();

    @Query("SELECT * FROM produit_table WHERE uid == :id LIMIT 1")
    Produit findById(int id);

    @Query("SELECT * FROM produit_table WHERE libelle == :libelle LIMIT 1")
    Produit findByLibelle(String libelle);

}
