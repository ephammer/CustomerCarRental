package com.ephraimhammer.jct.customercarrental.control.other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.ephraimhammer.jct.customercarrental.R;

public class FreeCarReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Log.d("FreeCarReceiver", "BroadCast received");
        new Task.FreeCarListTask(context, true).execute();
    }
}
