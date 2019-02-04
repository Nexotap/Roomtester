package ch.labrat.roomtester.features.breed;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "breed_translation",
        foreignKeys = @ForeignKey(entity = Breed.class,
        parentColumns = "id",
        childColumns = "breed_id"))
public class BreedTranslation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "breed_id")
    private int breedId;
    @ColumnInfo(name = "language")
    private String language;
    @ColumnInfo(name = "name")
    private String name;


    public int getId() { return id; }

    // TODO: How to remove this function without getting error of missing setter?
    public void setId(int id) {}

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
