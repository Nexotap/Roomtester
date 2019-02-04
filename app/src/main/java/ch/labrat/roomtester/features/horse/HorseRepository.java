package ch.labrat.roomtester.features.horse;

import android.app.Application;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import ch.labrat.roomtester.database.AnimaRoomDatabase;

public class HorseRepository {

    private HorseDao mHorseDao;
    private LiveData<List<Horse>> mAllHorses;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public HorseRepository(Application application) {
        AnimaRoomDatabase db = AnimaRoomDatabase.getDatabase(application);
        mHorseDao = db.horseDao();
    }

    public void initList() {
        mAllHorses = mHorseDao.getAlphabetizedHorses();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Horse>> getAllHorses()
    {
        return mAllHorses;
    }

    LiveData<Horse> getHorse(int horseId) {
        return mHorseDao.getHorse(horseId);
    }

    private static class insertAsyncTask extends AsyncTask<Horse, Void, Void> {

        private HorseDao mAsyncTaskDao;

        insertAsyncTask(HorseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Horse... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Horse, Void, Void> {

        private HorseDao mAsyncTaskDao;

        updateAsyncTask(HorseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Horse... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Horse, Void, Void> {

        private HorseDao mAsyncTaskDao;

        deleteAsyncTask(HorseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Horse... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(Horse horse) {
        horse.setCreatedAt(new Date());
        new insertAsyncTask(mHorseDao).execute(horse);
    }

    void update(Horse horse) {
        horse.setEditedAt(new Date());
        new updateAsyncTask(mHorseDao).execute(horse);
    }

    void delete(Horse horse) {
        new deleteAsyncTask(mHorseDao).execute(horse);
    }

    public LiveData<Integer> getHorseCount() {
        return mHorseDao.getDataCount();
    }

}
