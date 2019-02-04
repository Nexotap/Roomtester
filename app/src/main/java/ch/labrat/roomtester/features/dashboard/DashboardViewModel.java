package ch.labrat.roomtester.features.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ch.labrat.roomtester.features.breed.BreedRepository;
import ch.labrat.roomtester.features.horse.HorseRepository;

public class DashboardViewModel extends AndroidViewModel {
    private HorseRepository mRepository;
    private BreedRepository breedRep;


    public DashboardViewModel(Application application) {
        super(application);
        mRepository = new HorseRepository(application);
        breedRep = new BreedRepository(application);
    }

    LiveData<Integer> getHorseCount() {
        return mRepository.getHorseCount();
    }
    LiveData<Integer> getBreedCount() {
        return breedRep.getBreedCount();
    }

}
