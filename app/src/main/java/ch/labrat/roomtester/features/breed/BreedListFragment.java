package ch.labrat.roomtester.features.breed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.labrat.roomtester.R;
import ch.labrat.roomtester.features.base.BaseFragment;

public class BreedListFragment extends BaseFragment {

    private static final String SELECTED_BREED_POSITION = "selectedBreedPosition";

    private BreedListViewModel mBreedViewModel;
    private boolean twoPanelMode;
    private int selectedBreedPosition;
    private RecyclerView recyclerView;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            selectedBreedPosition = savedInstanceState.getInt(SELECTED_BREED_POSITION);
        }
        else {
            selectedBreedPosition = 0;
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
            pos = selectedBreedPosition;
        }
        else {
            pos = -1;
        }

        final BreedListAdapter adapter = new BreedListAdapter(getContext(), new BreedListAdapter.OnBreedAdapterItemClickListener() {
            @Override
            public void onItemClicked(int position) {

                selectBreed(position);
            }
        }, pos);

        recyclerView.setAdapter(adapter);

        mBreedViewModel = ViewModelProviders.of(requireActivity()).get(BreedListViewModel.class);

        mBreedViewModel.getAllBreeds().observe(this, breeds -> {
           adapter.setBreeds(breeds);

            if (twoPanelMode) {
                selectBreed(selectedBreedPosition);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.breed_list_fragment, container, false);
        return view;
    }

    void selectBreed(int position) {
        BreedListAdapter adapter = (BreedListAdapter) recyclerView.getAdapter();
        if(adapter != null && adapter.getItemCount() > 0) {
            selectedBreedPosition = position;
            showDetails(adapter.getItem(position));
        }
    }

    void showDetails(Breed breed){
    }

}
