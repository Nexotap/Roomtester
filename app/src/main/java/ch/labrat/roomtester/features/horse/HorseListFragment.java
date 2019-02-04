package ch.labrat.roomtester.features.horse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.labrat.roomtester.R;
import ch.labrat.roomtester.features.base.BaseFragment;

public class HorseListFragment extends BaseFragment {

    private static final String SELECTED_HORSE_POSITION = "selectedHorsePosition";

    private HorseViewModel mHorseViewModel;
    private boolean twoPanelMode;
    private int selectedHorsePosition;
    private RecyclerView recyclerView;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            selectedHorsePosition = savedInstanceState.getInt(SELECTED_HORSE_POSITION);
        }
        else {
            selectedHorsePosition = 0;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View detailsFrame = getActivity().findViewById(R.id.detailsFragment);
        twoPanelMode = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        recyclerViewSetup();
    }

    private void recyclerViewSetup() {
        recyclerView = view.findViewById(R.id.recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int pos;
        if (twoPanelMode) {
            pos = selectedHorsePosition;
        }
        else {
            pos = -1;
        }
        // Specify an adapter
        final HorseListAdapter adapter = new HorseListAdapter(getContext(), new HorseListAdapter.OnHorseAdapterItemClickListener() {
            @Override
            public void onItemClicked(int position) {

                selectHorse(position);
            }
        }, pos);

        recyclerView.setAdapter(adapter);

        // Get a new or existing ViewModel from the ViewModelProvider.
        mHorseViewModel = ViewModelProviders.of(getActivity()).get(HorseViewModel.class);

        // Add an observer on the LiveData returned by getAllHorses.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mHorseViewModel.getAllHorses().observe(this, horses -> {
            // Update the cached copy of the words in the adapter.
            adapter.setHorses(horses);

            if (twoPanelMode) {
                selectHorse(selectedHorsePosition);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.horse_list_fragment, container, false);
        return view;
    }

    void selectHorse(int position) {
        HorseListAdapter adapter = (HorseListAdapter) recyclerView.getAdapter();

        if(adapter != null && adapter.getItemCount() > 0) {
            selectedHorsePosition = position;
            showDetails(adapter.getItem(position));
        }
    }

    void showDetails(Horse horse){
        if (twoPanelMode) {
            if (getFragmentManager()!= null) {
                HorseDetailsFragment df = (HorseDetailsFragment) getFragmentManager().findFragmentById(R.id.detailsFragment);

                if (df != null) {
                    df.displayDetails(horse);
                } else {
                    df = HorseDetailsFragment.newInstance(horse.getId());
                    FragmentTransaction t = getFragmentManager().beginTransaction();
                    t.replace(R.id.detailsFragment, df, "details").commit();
                }
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(requireActivity(), HorseDetailsActivity.class);
            intent.putExtra(HorseDetailsFragment.HORSE_ID, horse.getId());
            startActivity(intent);
        }
    }

}
