package ch.labrat.roomtester.features.horse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface HorseDao {

    @Insert(onConflict = IGNORE)
    void insert(Horse horse);

    @Update(onConflict = REPLACE)
    void update(Horse horse);

    @Delete
    void delete(Horse horse);

    @Query("DELETE FROM horse")
    void deleteAll();

    @Query("SELECT * from horse ORDER BY name ASC")
    LiveData<List<Horse>> getAlphabetizedHorses();

    @Query("SELECT COUNT(id) FROM horse")
    LiveData<Integer> getDataCount();

    @Query("SELECT * from horse where id = :id LIMIT 1")
    LiveData<Horse> getHorse(int id);

//    @Query("SELECT * FROM pet WHERE ownerId IS :ownerId")
//    List<Pet> getPetsForOwner(String ownerId);
}

