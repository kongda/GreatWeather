package com.example.android.greatweather.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class GreatWeatherSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static GreatWeatherSyncAdapter sGreatWeatherSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("GreatWeatherSyncService", "onCreate - GreatWeatherSyncService");
        synchronized (sSyncAdapterLock) {
            if (sGreatWeatherSyncAdapter == null) {
                sGreatWeatherSyncAdapter = new GreatWeatherSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sGreatWeatherSyncAdapter.getSyncAdapterBinder();
    }
}