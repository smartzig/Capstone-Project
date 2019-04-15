package com.smartz.conexaodescontos.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smartz.conexaodescontos.R;

import com.smartz.conexaodescontos.model.Company;
import com.smartz.conexaodescontos.model.Promotion;
import com.smartz.conexaodescontos.ui.PromotionDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PromotionRecyclerViewAdapter extends RecyclerView.Adapter<PromotionRecyclerViewAdapter.ViewHolder> {

    private static final String LOG_TAG = PromotionRecyclerViewAdapter.class.toString();
    private final String EXTRA_PROMOTION_KEY ="promotion";
    private final String EXTRA_COMPANY_KEY ="company";
    private List<Promotion> promotionList ;
    private Context context;
    private FirebaseFirestore firestoreDB;
    private int mPromotionPosition;
    private Company companyToDetail;

    public PromotionRecyclerViewAdapter(List<Promotion> promotionList, Context context, FirebaseFirestore firestoreDB) {
        this.promotionList = promotionList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @Override
    public PromotionRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion, parent, false);

        final ViewHolder vh = new PromotionRecyclerViewAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPromotionPosition = vh.getAdapterPosition();

               final Promotion promotion =  promotionList.get(mPromotionPosition);

                Intent intent = new Intent(context, PromotionDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(EXTRA_PROMOTION_KEY, promotion);



                context.startActivity(intent);

            }
        });
        return vh;

    }

    @Override
    public void onBindViewHolder(PromotionRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPosition = position;
        final Promotion note = promotionList.get(itemPosition);

        holder.mName.setText(note.getName());
        holder.mDiscount.setText(note.getDiscount());
        holder.mDesc.setText(note.getDesc());
        holder.mCompanyName.setText(note.getCompanyName());
        holder.mPrice.setText(note.getPriceMessage()); //TODO FIX THE CURRENCY FORMAT

        String promoUrl = note.getImageUrl();


            Picasso.get()
                    .load(promoUrl)
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.image_placeholder)
                    .into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return promotionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView mName;
        @BindView(R.id.tvDiscount)
        TextView mDiscount;

        @BindView(R.id.tvCompanyName)
        TextView mCompanyName;
        @BindView(R.id.tvDesc)
        TextView mDesc;
        @BindView(R.id.tvPrice)
        TextView mPrice;
        @BindView(R.id.ivPromoImage)
        ImageView mImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
