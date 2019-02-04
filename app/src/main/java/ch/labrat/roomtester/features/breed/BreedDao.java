package ch.labrat.roomtester.features.breed;

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
public interface BreedDao {

    @Insert(onConflict = IGNORE)
    void insert(Breed breed);

    @Update(onConflict = REPLACE)
    void update(Breed breed);

    @Delete
    void delete(Breed breed);

    @Query("DELETE FROM breed")
    void deleteAll();

    @Query("SELECT * from breed ORDER BY name ASC")
    LiveData<List<Breed>> getAlphabetizedBreeds();

    @Query("SELECT COUNT(id) FROM breed")
    LiveData<Integer> getDataCount();

    @Query("SELECT * from breed where id = :id LIMIT 1")
    LiveData<Breed> getBreed(int id);

//    @Query("SELECT * FROM pet WHERE ownerId IS :ownerId")
//    List<Pet> getPetsForOwner(String ownerId);
}

