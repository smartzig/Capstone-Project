package com.smartz.conexaodescontos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PromotionDetailActivity extends AppCompatActivity {

    private final String EXTRA_PROMOTION_KEY ="promotion";
    private Promotion mPromotion;


    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.photo_container)
    View mPhotoContainerView;
    @BindView(R.id.iv_promotion)
    ImageView mPhotoView;

    @BindView(R.id.share_fab)
    FloatingActionButton mShareFab;
    @BindView(R.id.meta_bar)
    LinearLayout metaBar;
    @BindView(R.id.article_title)
    TextView titleView;
    @BindView(R.id.article_byline)
    TextView bylineView;
    @BindView(R.id.detail_toolbar)
    Toolbar detailToolbar;
    @BindView(R.id.article_body)
    TextView bodyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_detail);
        ButterKnife.bind(this);


        mPromotion= getIntent().getParcelableExtra(EXTRA_PROMOTION_KEY);


        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_iccon);

        String promoUrl = mPromotion.getImageUrl();


        Picasso.get()
                .load(promoUrl)
                .fit()
                .centerInside()

                .into(mPhotoView);

        titleView.setText(mPromotion.getName()); // npeomotion name
        SimpleDateFormat dtFormatter = new SimpleDateFormat("dd/mm/yyyy");

        String validUntil = "Valid until: " +  dtFormatter.format(mPromotion.getValidUntilDt().getTime());
        bylineView.setText(validUntil); //set valid until
        mPhotoView.setContentDescription("Where it´s it:?"); //dunno what is it
        bodyView.setText(mPromotion.getDesc()); //description or all promotion information in html

        mShareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(PromotionDetailActivity.this)
                        .setType("text/plain")
                        .setText(mPromotion.getName() +
                                "\\/n" + mPromotion.getValidUntilDt() +
                                "\\/n" + mPromotion.getDesc() +
                                "\\/n" + mPromotion.getPriceMessage())
                        .getIntent(), getString(R.string.action_share)));
            }
        });





    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                   finishAfterTransition();
                   return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
