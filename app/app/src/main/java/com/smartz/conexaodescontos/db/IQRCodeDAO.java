package com.smartz.conexaodescontos.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.smartz.conexaodescontos.model.QrCode;

@Dao
public interface IQRCodeDAO {

    @Query("SELECT * FROM QRCODE")
    QrCode getForWidget();

    @Insert
    void insertToWidget(QrCode qrCode);

    @Query("DELETE  FROM QRCODE")
    void deleteAll();
}
