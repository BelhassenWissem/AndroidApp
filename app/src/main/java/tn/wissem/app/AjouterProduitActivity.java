package tn.wissem.app;

import androidx.appcompat.app.AppCompatActivity;
import tn.wissem.app.database.AppDataBase;
import tn.wissem.app.entity.Produit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AjouterProduitActivity extends AppCompatActivity {

    private EditText libelle, description, quantite;
    private RadioButton rbSamsung, rbApple, rbHuawei;
    private Button saveProd;

    private Produit produit;

    private AppDataBase database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        getSupportActionBar().setTitle("Ajouter un produit");

        libelle = findViewById(R.id.libelle);
        description = findViewById(R.id.description);
        quantite = findViewById(R.id.quantite);

        rbSamsung = findViewById(R.id.rbSamsung);
        rbApple = findViewById(R.id.rbApple);
        rbHuawei = findViewById(R.id.rbHuawei);

        saveProd = findViewById(R.id.saveProd);

        database = AppDataBase.getAppDatabase(this);

        saveProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validator()) {

                    produit= new Produit(libelle.getText().toString(), Double.parseDouble(description.getText().toString()),
                            Integer.parseInt(quantite.getText().toString()));

                    if(rbHuawei.isChecked()){
                        produit.setMarque("huawei");
                    }else if(rbSamsung.isChecked()){
                        produit.setMarque("samsung");
                    }else if(rbApple.isChecked()){
                        produit.setMarque("apple");
                    }

                    database.produitDao().insertOne(produit);

                    setResult(RESULT_OK);

                    finish();
                }
            }
        });

    }

    public boolean validator(){
        if (libelle.getText().toString().length() == 0
                || description.getText().toString().length() == 0
                || quantite.getText().toString().length() == 0){
            Toast.makeText(this, "Data must not be empty !", Toast.LENGTH_SHORT).show();
            return false;
        }

        Produit tmpProduit = database.produitDao().findByLibelle(libelle.getText().toString());

        if (tmpProduit != null){
            Toast.makeText(this, "Produit exist in database !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
