package ch.labrat.roomtester.features.horse;

import android.os.Bundle;

import ch.labrat.roomtester.R;
import ch.labrat.roomtester.features.base.BaseActivity;

public class HorseActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.horse_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
