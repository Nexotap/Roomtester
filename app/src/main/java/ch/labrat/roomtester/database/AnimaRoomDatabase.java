package ch.labrat.roomtester.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import ch.labrat.roomtester.MyApplication;
import ch.labrat.roomtester.features.breed.Breed;
import ch.labrat.roomtester.features.breed.BreedDao;
import ch.labrat.roomtester.features.horse.HorseDao;
import ch.labrat.roomtester.features.horse.Horse;

@Database(entities = {Horse.class, Breed.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AnimaRoomDatabase extends RoomDatabase {

    public abstract HorseDao horseDao();
    public abstract BreedDao breedDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile AnimaRoomDatabase INSTANCE;

    public static AnimaRoomDatabase getDatabase(final Context context) {
        context.getApplicationContext().deleteDatabase(MyApplication.databaseName);
        if (INSTANCE == null) {
            synchronized (AnimaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AnimaRoomDatabase.class, MyApplication.databaseName)
                            .addCallback(sRoomDatabaseCallback)
                            .setJournalMode(JournalMode.TRUNCATE)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


}
