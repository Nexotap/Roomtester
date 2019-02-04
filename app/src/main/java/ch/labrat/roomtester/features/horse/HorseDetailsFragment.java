package ch.labrat.roomtester.features.horse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.labrat.roomtester.R;
import ch.labrat.roomtester.databinding.HorseDetailsFragmentBinding;
import ch.labrat.roomtester.features.base.BaseFragment;

public class HorseDetailsFragment extends BaseFragment {

    static final String HORSE_ID ="horseId";
    private HorseDetailsFragmentBinding binding;
    private Horse mHorse;

    static HorseDetailsFragment newInstance(int horseId) {
        HorseDetailsFragment f = new HorseDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(HORSE_ID, horseId);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = HorseDetailsFragmentBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.physique_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        binding.physiqueSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterFL = ArrayAdapter.createFromResource(inflater.getContext(), R.array.fetter_position_array, android.R.layout.simple_spinner_item);
        adapterFL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fetterFLSpinner.setAdapter(adapterFL);

        ArrayAdapter<CharSequence> adapterFR = ArrayAdapter.createFromResource(inflater.getContext(), R.array.fetter_position_array, android.R.layout.simple_spinner_item);
        adapterFR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fetterFRSpinner.setAdapter(adapterFR);

        ArrayAdapter<CharSequence> adapterHL = ArrayAdapter.createFromResource(inflater.getContext(), R.array.fetter_position_array, android.R.layout.simple_spinner_item);
        adapterHL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fetterHLSpinner.setAdapter(adapterHL);

        ArrayAdapter<CharSequence> adapterHR = ArrayAdapter.createFromResource(inflater.getContext(), R.array.fetter_position_array, android.R.layout.simple_spinner_item);
        adapterHR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fetterHRSpinner.setAdapter(adapterHR);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments()!= null && getArguments().containsKey(HORSE_ID)) {
            HorseRepository r = new HorseRepository(requireActivity().getApplication());
            r.getHorse(getArguments().getInt(HORSE_ID)).observe( this, horse -> displayDetails(horse) );
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.horses_view_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);

    }

    public void displayDetails(Horse horse){
        mHorse = horse;

        if (horse != null) {
            binding.name.setText(horse.getName());
            binding.age.setText(String.valueOf(horse.getAge()));
            binding.breed.setText(horse.getBreed());
            binding.owner.setText(horse.getOwner());
            binding.color.setText(horse.getColor());
            binding.gender.setText(horse.getGender());
        }
    }
}
