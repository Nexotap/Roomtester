package ch.labrat.roomtester.features.horse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "horse")
public class Horse {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private Date dob;
    private String gender = "";
    private String breed = "";
    private String color = "";
    private String physique = "";
    private String abnormalities = "";
    private String keeping = "";
    private String use = "";
    private String shoeing = "";
    private String owner = "";
    private Date createdAt;
    private Date editedAt;

    public Horse(String name) {
        this.name = name;
    }

    @Ignore
    public Horse() {
        // Do nothing
    }

    public void setAllDetails(Date dob, String gender, String breed, String color, String owner) {
        this.dob = dob;
        this.gender = gender;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
    }

    public int getAge() {
        if (dob != null) {
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            int d1 = Integer.parseInt(formatter.format(dob));
            int d2 = Integer.parseInt(formatter.format(new Date()));
            return  (d2 - d1) / 10000;
        }
        else {
            return 0;
        }
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhysique() {
        return physique;
    }

    public void setPhysique(String physique) {
        this.physique = physique;
    }

    public String getAbnormalities() {
        return abnormalities;
    }

    public void setAbnormalities(String abnormalities) {
        this.abnormalities = abnormalities;
    }

    public String getKeeping() {
        return keeping;
    }

    public void setKeeping(String keeping) {
        this.keeping = keeping;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getShoeing() {
        return shoeing;
    }

    public void setShoeing(String shoeing) {
        this.shoeing = shoeing;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }
}
