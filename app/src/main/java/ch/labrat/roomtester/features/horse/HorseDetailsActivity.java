package ch.labrat.roomtester.features.horse;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import ch.labrat.roomtester.features.base.BaseActivity;

public class HorseDetailsActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG,"onCreate");
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            HorseDetailsFragment details = HorseDetailsFragment.newInstance(getIntent().getExtras().getInt(HorseDetailsFragment.HORSE_ID));
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
