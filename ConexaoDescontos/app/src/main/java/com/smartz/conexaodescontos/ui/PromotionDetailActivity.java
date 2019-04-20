package com.smartz.conexaodescontos.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.WriterException;
import com.smartz.conexaodescontos.PromotionAppWidget;
import com.smartz.conexaodescontos.R;
import com.smartz.conexaodescontos.db.DbHelper;
import com.smartz.conexaodescontos.model.Promotion;
import com.smartz.conexaodescontos.model.QrCode;
import com.smartz.conexaodescontos.utils.QRCodeGenerator;
import com.smartz.conexaodescontos.utils.Utils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PromotionDetailActivity extends AppCompatActivity {

    private  DbHelper dbHelper;

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

    @BindView(R.id.iv_crcode)
    ImageView mQCcodeView;
    @BindView(R.id.bt_qrcode)
    Button mQrCodeButton;

    private QrCode mQrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_detail);
        ButterKnife.bind(this);

        dbHelper = new DbHelper(this);




        mPromotion= getIntent().getParcelableExtra(Utils.EXTRA_PROMOTION_KEY);


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

        titleView.setText(mPromotion.getPromotionName());


        String validUntil = getResources().getString(R.string.valid_until) +  Utils.dtFormatter.format(mPromotion.getValidUntilDt().getTime());
        bylineView.setText(validUntil);
        mPhotoView.setContentDescription(mPromotion.getDesc());
        bodyView.setText(mPromotion.getDetailDesc());

        mShareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(PromotionDetailActivity.this)
                        .setType("text/plain")
                        .setText(mPromotion.getPromotionName() +
                                "\\/n" + mPromotion.getValidUntilDt() +
                                "\\/n" + mPromotion.getDesc() +
                                "\\/n" + mPromotion.getPriceMessage())
                        .getIntent(), getString(R.string.action_share)));
            }
        });


        mQCcodeView.setEnabled(false);

        mQrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid() ;
                String hash = "treta"+userID;
                mQrCode = new QrCode();
                mQrCode.setQrCodeId(mPromotion.getId());
                mQrCode.setPromotion(mPromotion);

                mQrCode.setUsedHash(hash);
                mQrCode.setUserID(userID);

                mQCcodeView.setEnabled(false);

                try {
                    QRCodeGenerator generator = new QRCodeGenerator(getApplicationContext());
                    final Bitmap bitmap = generator.generateQRCode(mQrCode);

                    mQrCode.setGeneratedBitMap(bitmap);

                    mQCcodeView.setImageBitmap(bitmap);
                    mQCcodeView.setEnabled(true);


                    updateWidget();


                } catch (WriterException e) {
                    e.printStackTrace();

                }
            }
        });



    }

    private void updateWidget() {

        dbHelper.insertToWidget(mQrCode);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, PromotionAppWidget.class));
        //Now update all widgets
        PromotionAppWidget.updateWidgets(this, appWidgetManager, appWidgetIds);

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

