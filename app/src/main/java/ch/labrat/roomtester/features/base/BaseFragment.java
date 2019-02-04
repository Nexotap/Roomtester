package ch.labrat.roomtester.features.base;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected String TAG;

    public BaseFragment() {
        super();
        TAG = getClass().getSimpleName();
    }
}
