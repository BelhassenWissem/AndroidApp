package tn.wissem.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tn.wissem.app.database.AppDataBase;
import tn.wissem.app.entity.Produit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 99;

    private Button btnAddProd, btnLogout;
    private RecyclerView mRecyclerView;
    private ProduitAdapter produitAdapter;

    private List<Produit> produits;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Phone Store");

        btnAddProd = findViewById(R.id.addProd);
        btnLogout = findViewById(R.id.logout);
        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mPreferences = getSharedPreferences(MainActivity.sharedPrefFile, MODE_PRIVATE);

        produits = new ArrayList<Produit>();

        produits = AppDataBase.getAppDatabase(getApplicationContext()).produitDao().getAllProduits();

        produitAdapter = new ProduitAdapter(this, produits);

        mRecyclerView.setAdapter(produitAdapter);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.clear();
                preferencesEditor.apply();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });

        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AjouterProduitActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        produits = AppDataBase.getAppDatabase(getApplicationContext()).produitDao().getAllProduits();
        produitAdapter = new ProduitAdapter(this, produits);
        mRecyclerView.setAdapter(produitAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            produits = AppDataBase.getAppDatabase(getApplicationContext()).produitDao().getAllProduits();
            produitAdapter = new ProduitAdapter(this, produits);
            mRecyclerView.setAdapter(produitAdapter);
        }
    }
}
