package tn.wissem.app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import tn.wissem.app.dao.ProduitDao;
import tn.wissem.app.entity.Produit;

@Database(entities = {Produit.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract ProduitDao produitDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "AppWissem")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
