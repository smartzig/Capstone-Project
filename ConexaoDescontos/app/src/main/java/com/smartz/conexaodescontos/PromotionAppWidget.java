package com.smartz.conexaodescontos;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.WriterException;
import com.smartz.conexaodescontos.db.DbHelper;
import com.smartz.conexaodescontos.model.QrCode;
import com.smartz.conexaodescontos.utils.QRCodeGenerator;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

/**
 * Implementation of App Widget functionality.
 */
public class PromotionAppWidget extends AppWidgetProvider {



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId ) {


        QrCode qrCodeToWidget = null;
        try {
            qrCodeToWidget = new DbHelper(context).getQrCodeToWidget();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (qrCodeToWidget == null || FirebaseAuth.getInstance().getCurrentUser()==null) {

            CharSequence widgetText = context.getString(R.string.appwidget_empty);
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.empty_app_widget);
            views.setTextViewText(R.id.tv_appwidget_empty, widgetText);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }else{

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.promotion_app_widget);
            views.setTextViewText(R.id.tvWidgetName, qrCodeToWidget.getPromotion().getPromotionName());
            views.setTextViewText(R.id.tvWidgetCompanyName, qrCodeToWidget.getPromotion().getCompanyRefName());
            views.setTextViewText(R.id.tvWidgetDiscount, qrCodeToWidget.getPromotion().getDiscount());
            views.setTextViewText(R.id.tvWidgetDesc, qrCodeToWidget.getPromotion().getDesc());
            views.setTextViewText(R.id.tvWidgetPrice, qrCodeToWidget.getPromotion().getPriceMessage());


                QRCodeGenerator generator = new QRCodeGenerator(context);

            try
            {
              views.setImageViewBitmap(R.id.ivWidgetcrcode, generator.generateQRCode(qrCodeToWidget));
            } catch (WriterException e) {
                e.printStackTrace();
            }




            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

