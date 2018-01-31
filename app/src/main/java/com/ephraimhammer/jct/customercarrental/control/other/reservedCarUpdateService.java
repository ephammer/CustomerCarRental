package com.ephraimhammer.jct.customercarrental.control.other;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.ephraimhammer.jct.customercarrental.control.other.Task;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class reservedCarUpdateService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    static int count = 1;
    int id = 0, startId = -1;
    boolean isRun = false;

    final String TAG = "reservedCarUpdateServic";

    public reservedCarUpdateService() {
        super("reservedCarUpdateService");
        id = count++;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (isRun) {
            try {
                Thread.sleep(10000);
                new Task.ReservedCarUpdateTask(this).execute();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, serviceInfo() + " print ...");
        }
    }

    String serviceInfo() {
        return "service [" + id + "] startId = " + startId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        id++;
        Log.d(TAG, serviceInfo() + " onCreate ...");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, serviceInfo() + " onDestroy ...");
        isRun = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        isRun = true;
        Log.d(TAG, serviceInfo() + " onStartCommand start ...");
        return super.onStartCommand(intent, flags, startId);
    }
}
