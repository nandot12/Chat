package com.nandohusni.baggit.ui.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.nandohusni.baggit.R;
import com.nandohusni.baggit.db.AlarmClockBuilder;
import com.nandohusni.baggit.db.AlarmDBUtils;
import com.nandohusni.baggit.service.AlarmClockService;
import com.nandohusni.baggit.ui.schedule.adapter.AlarmAdapter;
import com.nandohusni.baggit.ui.schedule.model.AlarmModel;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements AlarmAdapter.AlarmAdapterInterface {


    @BindView(R.id.add_alarmlist)
    RecyclerView addAlarmlist;
    @BindView(R.id.floating_action_btn)
    FloatingActionButton floatingActionBtn;
    @BindView(R.id.clayout)
    CoordinatorLayout clayout;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<AlarmModel> alarmList;
    private AlarmAdapter alarmAdapter;
    private AlarmTask mTask;

    public static final String WEEK_DAY = "Week day";
    private static final String BOOT = "boot";
    private static final String FLAG = "flag";

    private Thread mTread = new Thread(new Runnable() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setData(alarmList);
                }
            });
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(getString(R.string.schedule_toolbar));


        mTread.start();

        alarmAdapter = new AlarmAdapter(this, alarmList);
        addAlarmlist.setLayoutManager(new LinearLayoutManager(this));
        addAlarmlist.setAdapter(alarmAdapter);
        startService(new Intent(this, AlarmClockService.class));
    }

    private void setData(List<AlarmModel> list) {
        alarmAdapter.setData(list);
    }

    @OnClick(R.id.floating_action_btn)
    public void OnFABClick() {
        startActivity(AddAlarmActivity.newIntent(ScheduleActivity.this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tambah_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuTambah:
                Intent intent = new Intent(this, AddAlarmActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        alarmList = AlarmDBUtils.queryAlarmClock(this);
        alarmAdapter.setData(alarmList);
    }


    private void initDB() {
        AlarmClockBuilder clockBuilder = new AlarmClockBuilder();
        AlarmModel alarmM = clockBuilder.enable(true)
                .hour(7)
                .minute(0)
                .repeat(WEEK_DAY)
                .sunday(false)
                .monday(true)
                .tuesday(true)
                .wednesday(true)
                .thursday(true)
                .friday(true)
                .saturday(false)
                .ringPosition(0)
                .ring(firstRing(this))
                .volume(10)
                .vibrate(true)
                .remind(3)
                .weather(true)
                .builder(0);

        AlarmDBUtils.insertAlarmClock(this, alarmM);
    }

    private String firstRing(Context context) {
        RingtoneManager ringtoneManager = new RingtoneManager(context);
        ringtoneManager.setType(RingtoneManager.TYPE_ALARM);
        Cursor cursor = ringtoneManager.getCursor();
        String ringName = null;

        while (cursor.moveToNext()) {
            ringName = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
            if (ringName != null) {
                break;
            }
        }
        return ringName;
    }


    @Override
    public void onItemClick(AlarmModel model) {
        startActivity(new Intent(EditAlarmActivity.newIntent(this, model)));
    }

    @Override
    public void onLongClick(int id) {
        startActivity(new Intent(DeleteActivity.newIntent(ScheduleActivity.this, id)));
    }

    public class AlarmTask extends AsyncTask<Void, Void, AlarmModel> {

        @Override
        protected AlarmModel doInBackground(Void... params) {
            SharedPreferences sharedPreferences = getSharedPreferences(BOOT, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean flag = sharedPreferences.getBoolean(FLAG, false);

            if (!flag) {
                initDB();
                editor.putBoolean(FLAG, true);
                editor.apply();
            }

            alarmList = AlarmDBUtils.queryAlarmClock(ScheduleActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(AlarmModel alarmModel) {
            super.onPostExecute(alarmModel);
            setData(alarmList);
        }
    }
}
