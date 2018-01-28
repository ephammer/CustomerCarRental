package com.ephraimhammer.jct.customercarrental.control.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.Task;

public class CommandListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_list);

        new Task.CommandListTask(this).execute();
    }
}
