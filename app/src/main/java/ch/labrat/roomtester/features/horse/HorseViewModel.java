package ch.labrat.roomtester.features.horse;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class HorseViewModel extends AndroidViewModel {

    private HorseRepository mRepository;

    private LiveData<List<Horse>> mAllHorses;

    public HorseViewModel(Application application) {
        super(application);
        mRepository = new HorseRepository(application);
        mRepository.initList();
        mAllHorses = mRepository.getAllHorses();
    }

    LiveData<List<Horse>> getAllHorses() {
        return mAllHorses;
    }

    void insert(Horse horse) {
        mRepository.insert(horse);
    }
    void update(Horse horse) {
        mRepository.update(horse);
    }
    void delete(Horse horse) {
        mRepository.delete(horse);
    }

}
