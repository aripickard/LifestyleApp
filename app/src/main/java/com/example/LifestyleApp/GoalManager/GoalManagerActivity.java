package com.example.LifestyleApp.GoalManager;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.User;

public class GoalManagerActivity extends AppCompatActivity {
    private GoalManagerFragment goalManagerFragment;
//    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        goalManagerFragment = new GoalManagerFragment();
        goalManagerFragment.setArguments(getIntent().getExtras());
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fl_frag_itemdetail_container_phone, goalManagerFragment, "frag_itemdetail_gm");
        fTrans.commit();
    }
}