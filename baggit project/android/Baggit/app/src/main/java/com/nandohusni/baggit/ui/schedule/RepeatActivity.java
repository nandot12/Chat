package com.nandohusni.baggit.ui.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nandohusni.baggit.R;
import com.nandohusni.baggit.db.AlarmClockBuilder;
import com.nandohusni.baggit.db.AlarmClockLab;

/**
 * Created by Denys on 27.01.2017.
 */
public class RepeatActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.repeat_once)
    TextView tvOnce;
    @BindView(R.id.repeat_weekday)
    TextView tvWeekDay;
    @BindView(R.id.repeat_everyday)
    TextView tvEveryDay;
    @BindView(R.id.repeat_weekend)
    TextView tvWeekend;
    @BindView(R.id.repeat_choice)
    TextView tvChoice;

    private AlarmClockLab alarmClockLab;

    public static Intent newIntent(Context context) {
        return new Intent(context, RepeatActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);
        ButterKnife.bind(this);

        alarmClockLab = new AlarmClockBuilder().builderLab(0);

        tvOnce.setOnClickListener(this);
        tvWeekDay.setOnClickListener(this);
        tvEveryDay.setOnClickListener(this);
        tvWeekend.setOnClickListener(this);
        tvChoice.setOnClickListener(this);

        String repeat = alarmClockLab.repeat;
        if (repeat.equals(tvOnce.getText().toString())) {
            tvOnce.setTextColor(ContextCompat.getColor(this, R.color.colorRed_500));
        } else if (repeat.equals(tvWeekDay.getText().toString())) {
            tvWeekDay.setTextColor(ContextCompat.getColor(this, R.color.colorRed_500));
        } else if (repeat.equals(tvEveryDay.getText().toString())) {
            tvEveryDay.setTextColor(ContextCompat.getColor(this, R.color.colorRed_500));
        } else if (repeat.equals(tvWeekend.getText().toString())) {
            tvWeekend.setTextColor(ContextCompat.getColor(this, R.color.colorRed_500));
        } else if (repeat.equals(tvChoice.getText().toString())) {
            tvChoice.setTextColor(ContextCompat.getColor(this, R.color.colorRed_500));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repeat_once:
                alarmClockLab.setRepeat(tvOnce.getText().toString());
                alarmClockLab.setSunday(false);
                alarmClockLab.setMonday(false);
                alarmClockLab.setTuesday(false);
                alarmClockLab.setWednesday(false);
                alarmClockLab.setThursday(false);
                alarmClockLab.setFriday(false);
                alarmClockLab.setSaturday(false);
                break;

            case R.id.repeat_weekday:
                alarmClockLab.setRepeat(tvWeekDay.getText().toString());
                alarmClockLab.setSunday(false);
                alarmClockLab.setMonday(true);
                alarmClockLab.setTuesday(true);
                alarmClockLab.setWednesday(true);
                alarmClockLab.setThursday(true);
                alarmClockLab.setFriday(true);
                alarmClockLab.setSaturday(false);
                break;

            case R.id.repeat_everyday:
                alarmClockLab.setRepeat(tvEveryDay.getText().toString());
                alarmClockLab.setSunday(true);
                alarmClockLab.setMonday(true);
                alarmClockLab.setTuesday(true);
                alarmClockLab.setWednesday(true);
                alarmClockLab.setThursday(true);
                alarmClockLab.setFriday(true);
                alarmClockLab.setSaturday(true);
                break;

            case R.id.repeat_weekend:
                alarmClockLab.setRepeat(tvWeekend.getText().toString());
                alarmClockLab.setSunday(true);
                alarmClockLab.setMonday(false);
                alarmClockLab.setTuesday(false);
                alarmClockLab.setWednesday(false);
                alarmClockLab.setThursday(false);
                alarmClockLab.setFriday(false);
                alarmClockLab.setSaturday(true);
                break;

            case R.id.repeat_choice:
                alarmClockLab.setRepeat(tvChoice.getText().toString());
                startActivity(RepeatChoiceActivity.newIntent(this));
                break;

            default:
                break;
        }
        finish();
    }
}
