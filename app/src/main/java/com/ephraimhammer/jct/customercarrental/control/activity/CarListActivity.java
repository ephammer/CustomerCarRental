package com.ephraimhammer.jct.customercarrental.control.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ephraimhammer.jct.customercarrental.R;
import com.ephraimhammer.jct.customercarrental.control.other.SEARCH_CAR_TYPE;
import com.ephraimhammer.jct.customercarrental.control.other.Task;
import com.ephraimhammer.jct.customercarrental.control.other.reservedCarUpdateService;
import com.ephraimhammer.jct.customercarrental.model.entities.Car;

import java.util.List;

public class CarListActivity extends AppCompatActivity {

    List<Car> carList;
    SEARCH_CAR_TYPE search_car_type;
    long branchId = 650208;
    int RangeKm = 47;
    int sector = 15;
    final String TAG = "CarListActivity";
    boolean isRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);


        startService(new Intent(this, reservedCarUpdateService.class));

        search_car_type = (SEARCH_CAR_TYPE) getIntent().getExtras().get("type");
        SEARCH_CAR_TYPE searchCarType = (search_car_type);
        switch (search_car_type) {
            case FREE_CARS:
//                new Task.FreeCarListTask(this , false).execute();
                break;
            case FREE_CARS_BY_BRANCH:
                new Task.FreeCarByBranchTask(this).execute(branchId);
                break;
            case FREE_CARS_BY_RANGE_KM:
                new Task.FreeCarByRangeTask(this).execute(RangeKm, sector);
            default:
                break;

        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        isRun = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRun = false;
    }
}
