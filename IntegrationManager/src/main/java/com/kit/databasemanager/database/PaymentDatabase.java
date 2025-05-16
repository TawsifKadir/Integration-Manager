package com.kit.databasemanager.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.kit.databasemanager.dao.PayrollBiometricDao;
import com.kit.databasemanager.dao.PayrollCleanupDao;
import com.kit.databasemanager.dao.PayrollDao;
import com.kit.databasemanager.dao.PayrollDataDao;
import com.kit.databasemanager.dao.PayrollReconcileStatusDao;
import com.kit.databasemanager.dao.PayrollTransactionDao;

import com.kit.databasemanager.model.PayrollBiometricEO;
import com.kit.databasemanager.model.PayrollDataEO;
import com.kit.databasemanager.model.PayrollEO;
import com.kit.databasemanager.model.PayrollReconcileStatus;


@Database(entities = {PayrollEO.class, PayrollDataEO.class,PayrollBiometricEO.class, PayrollReconcileStatus.class},
        version = 1, exportSchema = true)
public abstract class PaymentDatabase extends RoomDatabase {
    private static final String LOG_TAG = PaymentDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "PaymentDatabase.db";
    private static PaymentDatabase sInstance;

    public static PaymentDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                try {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                                    PaymentDatabase.class, PaymentDatabase.DATABASE_NAME)
                            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                            .build();
                }catch(Exception exc){
                    Log.d(LOG_TAG, "Error while creating new database instance");
                    exc.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract PayrollDao payrollDao();
    public abstract PayrollDataDao payrollDataDao();
    public abstract PayrollBiometricDao payrollBiometricDao();
    public abstract PayrollTransactionDao payrollTransactionDao();
    public abstract PayrollCleanupDao payrollCleanupDao();
    public abstract PayrollReconcileStatusDao payrollReconcileStatusDao();
    
}
