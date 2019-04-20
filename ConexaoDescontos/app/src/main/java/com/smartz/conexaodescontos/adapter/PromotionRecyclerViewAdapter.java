package com.smartz.conexaodescontos.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.smartz.conexaodescontos.R;
import com.smartz.conexaodescontos.model.Promotion;
import com.smartz.conexaodescontos.ui.PromotionDetailActivity;
import com.smartz.conexaodescontos.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PromotionRecyclerViewAdapter extends RecyclerView.Adapter<PromotionRecyclerViewAdapter.ViewHolder> {

    private static final String LOG_TAG = PromotionRecyclerViewAdapter.class.toString();

    private List<Promotion> promotionList ;
    private Context context;

    private FirebaseFirestore firestoreDB;
    private int mPromotionPosition;

    public PromotionRecyclerViewAdapter(List<Promotion> promotionList, Context context, FirebaseFirestore firestoreDB) {
        this.promotionList = promotionList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @NonNull
    @Override
    public PromotionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion, parent, false);

        final ViewHolder vh = new PromotionRecyclerViewAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPromotionPosition = vh.getAdapterPosition();

               final Promotion promotion =  promotionList.get(mPromotionPosition);

                Intent intent = new Intent(context, PromotionDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Utils.EXTRA_PROMOTION_KEY, promotion);



                context.startActivity(intent);

            }
        });
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull PromotionRecyclerViewAdapter.ViewHolder holder, int position) {

        final Promotion promotion = promotionList.get(position);

        holder.mName.setText(promotion.getPromotionName());
        holder.mDiscount.setText(promotion.getDiscount());
        holder.mDesc.setText(promotion.getDesc());
        holder.mCompanyName.setText(promotion.getCompanyRefName());
        holder.mPrice.setText(promotion.getPriceMessage());

        String promoUrl = promotion.getImageUrl();


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
