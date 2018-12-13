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


public class RepeatChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.repeat_sun)
    TextView tvSun;
    @BindView(R.id.repeat_mon)
    TextView tvMon;
    @BindView(R.id.repeat_tue)
    TextView tvTue;
    @BindView(R.id.repeat_wed)
    TextView tvWed;
    @BindView(R.id.repeat_thu)
    TextView tvThu;
    @BindView(R.id.repeat_fri)
    TextView tvFri;
    @BindView(R.id.repeat_sat)
    TextView tvSat;
    @BindView(R.id.repeat_cancel)
    TextView tvCancel;
    @BindView(R.id.repeat_ok)
    TextView tvOk;

    AlarmClockLab alarmClockLab;


    public static Intent newIntent(Context context) {
        return new Intent(context, RepeatChoiceActivity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_choice);
        ButterKnife.bind(this);
        alarmClockLab = new AlarmClockBuilder().builderLab(0);

        tvSun.setTextColor(getColor(alarmClockLab.sunday));
        tvMon.setTextColor(getColor(alarmClockLab.monday));
        tvTue.setTextColor(getColor(alarmClockLab.tuesday));
        tvWed.setTextColor(getColor(alarmClockLab.wednesday));
        tvThu.setTextColor(getColor(alarmClockLab.thursday));
        tvFri.setTextColor(getColor(alarmClockLab.friday));
        tvSat.setTextColor(getColor(alarmClockLab.saturday));

        tvSun.setOnClickListener(this);
        tvMon.setOnClickListener(this);
        tvTue.setOnClickListener(this);
        tvWed.setOnClickListener(this);
        tvThu.setOnClickListener(this);
        tvFri.setOnClickListener(this);
        tvSat.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvOk.setOnClickListener(this);
    }

    private int getColor(boolean b) {
        if (b) {
            return ContextCompat.getColor(this, R.color.colorRed_500);
        } else {
            return ContextCompat.getColor(this, R.color.colorWhite);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repeat_sun:
                boolean sunday = !alarmClockLab.sunday;
                alarmClockLab.setSunday(sunday);
                tvSun.setTextColor(getColor(sunday));
                break;
            case R.id.repeat_mon:
                boolean monday = !alarmClockLab.monday;
                alarmClockLab.setMonday(monday);
                tvMon.setTextColor(getColor(monday));
                break;
            case R.id.repeat_tue:
                boolean tuesday = !alarmClockLab.tuesday;
                alarmClockLab.setTuesday(tuesday);
                tvTue.setTextColor(getColor(tuesday));
                break;
            case R.id.repeat_wed:
                boolean wednesday = !alarmClockLab.wednesday;
                alarmClockLab.setWednesday(wednesday);
                tvWed.setTextColor(getColor(wednesday));
                break;
            case R.id.repeat_thu:
                boolean thursday = !alarmClockLab.thursday;
                alarmClockLab.setThursday(thursday);
                tvThu.setTextColor(getColor(thursday));
                break;
            case R.id.repeat_fri:
                boolean friday = !alarmClockLab.friday;
                alarmClockLab.setFriday(friday);
                tvFri.setTextColor(getColor(friday));
                break;
            case R.id.repeat_sat:
                boolean saturday = !alarmClockLab.saturday;
                alarmClockLab.setSaturday(saturday);
                tvSat.setTextColor(getColor(saturday));
                break;
            case R.id.repeat_cancel:
                finish();
                break;
            case R.id.repeat_ok:
                finish();
                break;
            default:
                break;
        }
    }
}
