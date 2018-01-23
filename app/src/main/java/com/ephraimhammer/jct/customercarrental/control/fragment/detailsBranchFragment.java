package com.ephraimhammer.jct.customercarrental.control.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ephraimhammer.jct.customercarrental.R;

/**
 * Created by binyamin on 22/01/2018.
 */

public class detailsBranchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_branch_fragment, container , false);

    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }
}
