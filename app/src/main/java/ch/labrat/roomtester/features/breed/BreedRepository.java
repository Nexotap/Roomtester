package ch.labrat.roomtester.features.breed;

import android.app.Application;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import ch.labrat.roomtester.database.AnimaRoomDatabase;

public class BreedRepository {

    private BreedDao dao;
    private LiveData<List<Breed>> allBreeds;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public BreedRepository(Application application) {
        AnimaRoomDatabase db = AnimaRoomDatabase.getDatabase(application);
        dao = db.breedDao();
    }

    public void initList() {
        allBreeds = dao.getAlphabetizedBreeds();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Breed>> getAllBreeds()
    {
        return allBreeds;
    }

    LiveData<Breed> getBreed(int breedId) {
        return dao.getBreed(breedId);
    }

    private static class insertAsyncTask extends AsyncTask<Breed, Void, Void> {

        private BreedDao mAsyncTaskDao;

        insertAsyncTask(BreedDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Breed... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Breed, Void, Void> {

        private BreedDao mAsyncTaskDao;

        updateAsyncTask(BreedDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Breed... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Breed, Void, Void> {

        private BreedDao mAsyncTaskDao;

        deleteAsyncTask(BreedDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Breed... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(Breed breed) {
        breed.setCreatedAt(new Date());
        new insertAsyncTask(dao).execute(breed);
    }

    void update(Breed breed) {
        breed.setEditedAt(new Date());
        new updateAsyncTask(dao).execute(breed);
    }

    void delete(Breed breed) {
        new deleteAsyncTask(dao).execute(breed);
    }

    public LiveData<Integer> getBreedCount() {
        return dao.getDataCount();
    }

}
