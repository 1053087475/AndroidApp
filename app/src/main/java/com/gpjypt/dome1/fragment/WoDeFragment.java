package com.gpjypt.dome1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gpjypt.dome1.R;
import com.gpjypt.dome1.activity.LoginActivity;
import com.gpjypt.dome1.base.BaseFragment;
import com.gpjypt.dome1.utils.SPUtils;
import com.gpjypt.dome1.utils.StaticConfig;

public class WoDeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wode,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLoginOut=view.findViewById(R.id.btnLoginOut);
        btnLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.putBoolean(StaticConfig.IS_LOGIN_KEY,false);
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        TextView tvUserInfo=view.findViewById(R.id.tvUserInfo);
        tvUserInfo.setText("欢迎，："+SPUtils.getString(StaticConfig.USER_NAME)+"!");
    }
}
