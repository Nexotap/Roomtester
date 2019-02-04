package ch.labrat.roomtester.features.base;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG;

    protected BaseActivity() {
        super();
        TAG = getClass().getSimpleName();
    }
}
