package com.nandohusni.baggit.ui.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nandohusni.baggit.R;
import com.nandohusni.baggit.db.AlarmDBUtils;
import com.nandohusni.baggit.db.AlarmManagerHelper;

/**
 * Created by Denys on 28.01.2017.
 */
public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ALARM_ID = "id";
    @BindView(R.id.delete_cancel)
    TextView tvCancel;
    @BindView(R.id.delete_ok)
    TextView tvOk;

    private int id;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, DeleteActivity.class);
        intent.putExtra(ALARM_ID, id);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra(ALARM_ID, 0);
        tvCancel.setOnClickListener(this);
        tvOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.delete_ok) {
            AlarmManagerHelper.cancelAlarmClock(this, id);
            AlarmDBUtils.deleteAlarmClock(this, id);
        }
        finish();
    }
}
