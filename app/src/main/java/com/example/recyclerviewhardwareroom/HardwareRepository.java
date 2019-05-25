package com.example.recyclerviewhardwareroom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class HardwareRepository {
    private HardwareDao mHardwareDao;
    private LiveData<List<Hardware>> mAllHardware;

    HardwareRepository(Application application) {
        HardwareRoomDatabase db = HardwareRoomDatabase.getDatabase(application);
        mHardwareDao = db.hardwareDao();
        mAllHardware = mHardwareDao.getAllHardware();
    }

    LiveData<List<Hardware>> getmAllHardware() {
        return mAllHardware;
    }

    public void insert (Hardware hardware) {
        new insertAsyncTask(mHardwareDao).execute(hardware);
    }
    public void delete (Hardware hardware){ new deleteAsyncTask(mHardwareDao).execute(hardware); }

    //insert
    private static class insertAsyncTask extends AsyncTask<Hardware, Void, Void> {
        private HardwareDao mAsyncTaskDao;

        insertAsyncTask(HardwareDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Hardware... params) {
            mAsyncTaskDao.insertHardware(params[0]);
            return null;
        }
    }

    //delete
    private static class deleteAsyncTask extends AsyncTask<Hardware, Void, Void> {
        private HardwareDao mAsyncTaskDao;

        deleteAsyncTask(HardwareDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Hardware... params) {
            mAsyncTaskDao.deleteHardware(params[0]);
            return null;
        }
    }
}
