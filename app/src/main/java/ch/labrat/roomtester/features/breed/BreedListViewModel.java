package ch.labrat.roomtester.features.breed;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BreedListViewModel extends AndroidViewModel {
    private BreedRepository rep;

    private LiveData<List<Breed>> mAllBreeds;

    public BreedListViewModel(Application application) {
        super(application);
        rep = new BreedRepository(application);
        rep.initList();
        mAllBreeds = rep.getAllBreeds();
    }

    LiveData<List<Breed>> getAllBreeds() {
        return mAllBreeds;
    }

    void insert(Breed breed) {
        rep.insert(breed);
    }
    void update(Breed breed) {
        rep.update(breed);
    }
    void delete(Breed breed) {
        rep.delete(breed);
    }

}
