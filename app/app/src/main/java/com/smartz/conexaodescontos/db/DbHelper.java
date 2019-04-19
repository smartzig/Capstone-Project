package com.smartz.conexaodescontos.db;


import android.content.Context;
import android.os.AsyncTask;

import com.smartz.conexaodescontos.model.QrCode;

import java.util.concurrent.ExecutionException;


public class DbHelper {
    private QrCode qrCodeToWidget;
    private AppDatabase appDatabase;

    public DbHelper(Context context) {

        appDatabase = AppDatabase.getInstance(context);

    }

    public QrCode getQrCodeToWidget() throws ExecutionException, InterruptedException {

        GetQrCodeAsyncTask task = new GetQrCodeAsyncTask(appDatabase, qrCodeToWidget);
        qrCodeToWidget = task.execute().get();

        return qrCodeToWidget;
    }



    public void insertToWidget(QrCode qrCode) {
        new InsertAsyncTask(appDatabase).execute(qrCode);
    }

    private static class InsertAsyncTask extends AsyncTask<QrCode, Void, Void> {

        private AppDatabase db;

        InsertAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final QrCode... params) {
            db.qrCodeDao().deleteAll();
            db.qrCodeDao().insertToWidget(params[0]);
            return null;
        }

    }


        public static class GetQrCodeAsyncTask extends AsyncTask<Void, Integer, QrCode>{
            private AppDatabase db;
            private QrCode qrCode;

            private GetQrCodeAsyncTask(AppDatabase db, QrCode qrCode){
                this.db = db;
                this.qrCode = qrCode;
            }


            @Override
            protected QrCode doInBackground(Void... voids) {
                return db.qrCodeDao().getForWidget();
            }

            protected void onPostExecute(QrCode data) {
                super.onPostExecute(data);
               this.qrCode = data;

            }

        }

}
