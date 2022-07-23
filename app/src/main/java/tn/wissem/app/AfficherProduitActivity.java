package tn.wissem.app;

import androidx.appcompat.app.AppCompatActivity;
import tn.wissem.app.database.AppDataBase;
import tn.wissem.app.entity.Produit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AfficherProduitActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView libelle, quantite, prix;
    private Button delete;

    private Produit produit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_produit);

        getSupportActionBar().setTitle("Informations");

        imageView = findViewById(R.id.imageProduit);
        libelle = findViewById(R.id.libelle);
        quantite = findViewById(R.id.quantite);
        prix = findViewById(R.id.prix);
        delete = findViewById(R.id.delete);

        produit = AppDataBase.getAppDatabase(getApplicationContext()).produitDao().findById(getIntent().getIntExtra("ID_PROD",0));

        libelle.setText(produit.getLibelle());
        quantite.setText(produit.getQuantite()+"");
        prix.setText(produit.getPrix().toString() + " DT");

        if(produit.getMarque().equals("samsung")){
            imageView.setImageResource(R.drawable.ic_samsung);
        }else if(produit.getMarque().equals("huawei")){
            imageView.setImageResource(R.drawable.ic_huawei);
        }else if(produit.getMarque().equals("apple")){
            imageView.setImageResource(R.drawable.ic_apple);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase.getAppDatabase(getApplicationContext()).produitDao().delete(produit);
                finish();
            }
        });

//        Log.e("AFFICHER",getIntent().getIntExtra("ID_PROD",0) + "");
    }
}
