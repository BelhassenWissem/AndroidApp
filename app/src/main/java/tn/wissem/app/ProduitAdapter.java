package tn.wissem.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tn.wissem.app.entity.Produit;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.ProduitHolder> {

    private Context mContext;
    private List<Produit> produitList;

    public ProduitAdapter(Context mContext, List<Produit> produitList) {
        this.mContext = mContext;
        this.produitList = produitList;
    }

    @NonNull
    @Override
    public ProduitAdapter.ProduitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.single_row, parent, false);
        return new ProduitHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitAdapter.ProduitHolder holder, int position) {
        final Produit singleItem = produitList.get(position);

        holder.libelle.setText(singleItem.getLibelle());
        holder.prix.setText(singleItem.getPrix().toString() + " DT");

        if(singleItem.getMarque().equals("samsung")){
            holder.imageProduit.setImageResource(R.drawable.ic_samsung);
        }else if(singleItem.getMarque().equals("apple")){
            holder.imageProduit.setImageResource(R.drawable.ic_apple);
        }else if(singleItem.getMarque().equals("huawei")){
            holder.imageProduit.setImageResource(R.drawable.ic_huawei);
        }

        holder.imageProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AfficherProduitActivity.class);
                intent.putExtra("ID_PROD",singleItem.getUid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produitList != null ? produitList.size() : 0;
    }

    public class ProduitHolder extends RecyclerView.ViewHolder {

        private TextView libelle, prix;
        private ImageView imageProduit;

        public ProduitHolder(@NonNull View itemView) {
            super(itemView);

            libelle = itemView.findViewById(R.id.libelle);
            prix = itemView.findViewById(R.id.prix);
            imageProduit = itemView.findViewById(R.id.imageProduit);

        }
    }
}
