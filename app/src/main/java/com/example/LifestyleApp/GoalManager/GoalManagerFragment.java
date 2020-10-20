package com.example.LifestyleApp.GoalManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.Home;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.UserData;

import Dialogs.ActivityLevelPicker;
import Dialogs.GoalWeightPicker;
import Dialogs.HeightPickerDialog;
import Dialogs.WeightPickerDialog;

public class GoalManagerFragment extends Fragment implements View.OnClickListener {

    private SeekBar seekBar;
    private TextView goalTV;
    private TextView mWeight;
    private TextView mHeight;
    private TextView mGoalWeight;
    private TextView mActivity;

    private Button mLose;
    private Button mMaintain;
    private Button mGain;
    private Button mSave;

    private String goal = "";
    private int prog = 0;

    private GoalManagerViewModel goalManagerViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goal_manager, container, false);

        Bundle bundle = getArguments();

        mWeight = view.findViewById(R.id.weightTextView);
        seekBar = view.findViewById(R.id.seekBar);
        goalTV = view.findViewById(R.id.goalTextView);
        mHeight = view.findViewById(R.id.heightTextView);
        mGoalWeight = view.findViewById(R.id.goalWeightTextView);
        mActivity = view.findViewById(R.id.activityLevelTextView);
        mLose = view.findViewById(R.id.loseBtn);
        mMaintain = view.findViewById(R.id.maintainBtn);
        mGain = view.findViewById(R.id.gainBtn);
        mGoalWeight = view.findViewById(R.id.goalWeightTextView);
        mActivity = view.findViewById(R.id.activityLevelTextView);
        mSave = view.findViewById(R.id.saveGoalButton);

        mWeight.setOnClickListener(this);
        mHeight.setOnClickListener(this);
        mGoalWeight.setOnClickListener(this);
        mActivity.setOnClickListener(this);
        mLose.setOnClickListener(this);
        mMaintain.setOnClickListener(this);
        mGain.setOnClickListener(this);
        mSave.setOnClickListener(this);
        seekBar.setMax(5);

        goalManagerViewModel = ViewModelProviders.of(this).get(GoalManagerViewModel.class);
        goalManagerViewModel.getUserData().observe(this, goalManagerObserver);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(goal != "maintain"){
                    prog = progress;
                    goalTV.setText(goal + " " + prog + "lb per week");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    final Observer<UserData> goalManagerObserver = new Observer<UserData>() {
        @Override
        public void onChanged(@Nullable final UserData userData) {
            mWeight.setText(userData.getUserData1().getWeight() + " lbs >");
            int ft = userData.getUserData1().getHeight()/12;
            int in = userData.getUserData1().getHeight()%12;
            mHeight.setText(ft + " ft " + in + " in" + " >");

            if(userData.getUserGoals().isGoalWeightSet()){
                mGoalWeight.setText(userData.getUserGoals().getGoalWeight() + " >");
            }
            if(userData.getUserActivity().isActivitySet()){
                mActivity.setText(userData.getUserActivity().getActivity() + " >");
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loseBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mGain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                goal = "lose";
                goalTV.setText(goal + " " + prog + "lb per week");
                //user.setGoal("lose");
                break;
            }
            case R.id.maintainBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                mGain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                goal = "maintain";
                goalTV.setText(goal + " weight");
                //user.setGoal("maintain");
                break;
            }
            case R.id.gainBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mGain.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                goal = "gain";
                goalTV.setText(goal + " " + prog + "lb per week");
                //user.setGoal("gain");
                break;
            }
            case R.id.weightTextView: {
                DialogFragment dialogFragment = new WeightPickerDialog();
                assert getFragmentManager() != null;
                dialogFragment.show(getFragmentManager(), "WeightPicker");
                break;
            }
            case R.id.heightTextView: {
                DialogFragment dialogFragment = new HeightPickerDialog();
                assert getFragmentManager() != null;
                dialogFragment.show(getFragmentManager(), "HeightPicker");
                break;
            }
            case R.id.goalWeightTextView: {
                DialogFragment dialogFragment = new GoalWeightPicker();
                assert getFragmentManager() != null;
                dialogFragment.show(getFragmentManager(), "GoalWeightPicker");
                //goalweight dialog
                break;
            }
            case R.id.activityLevelTextView: {
                DialogFragment dialogFragment = new ActivityLevelPicker();
                dialogFragment.show(getFragmentManager(), "ActivityLevelPicker");
                //activity level dialog box
                break;
            }
            case R.id.saveGoalButton: {

                float fWeight = Float.parseFloat(mWeight.getText().toString().split(" ")[0]);
                String height = (String) mHeight.getText();

                int ft = Integer.parseInt(height.split(" ")[0]);
                int in = Integer.parseInt(height.split(" ")[2]);
                int heightInInches = (ft * 12) + in;

                float fGWeight = Float.parseFloat(mGoalWeight.getText().toString().split(" ")[0]);
                String activityLevel = mActivity.getText().toString();

                //men 66.47 + (6.24 * weight) + (12.7 * height) - (6.755 * age in years)
                //women 655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age in years)

                // sedentary = bmr * 1.2
                // active = bmr * 1.5
//                Period period = Period.between(user.getDOB().toInstant()
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDate(), LocalDate.now());
                float BMR;
//                if (user.getGender().equals("Male")) {
//                    BMR = (float) (66.47 + (6.24 * fWeight) + (12.7 * heightInInches) - (6.755 * period.getYears()));
//                } else {
//                    BMR = (float) (655.1 + (4.35 * fWeight) + (4.7 * heightInInches) - (4.7 * period.getYears()));
//                }
//                BMR = (activityLevel == "Active") ? (BMR *= 1.5) : (BMR *= 1.2);
                BMR = 0;
                //calculate calories
                int diff = prog * 500;
                float calories = BMR;

                if (goal.equals("gain")) {
                    calories += diff;
                } else if (goal.equals("lose")) {
                    calories -= diff;
                }

                goalManagerViewModel.insert(prog, fGWeight, heightInInches,
                        fGWeight, true, activityLevel, true, goal,
                        true, prog, BMR, true, calories, true);

                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
                break;
            }
        }
    }


    public void sendGoalWeight(String goalWeight){
        mGoalWeight.setText(goalWeight);
    }
    public void sendActivity(String activityLevel){
        mActivity.setText(activityLevel);
    }

}