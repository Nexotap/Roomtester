package ch.labrat.roomtester.features.breed;

import android.os.Bundle;

import ch.labrat.roomtester.R;
import ch.labrat.roomtester.features.base.BaseActivity;

public class BreedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breed_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
