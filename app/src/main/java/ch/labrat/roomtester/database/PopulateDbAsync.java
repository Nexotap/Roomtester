package ch.labrat.roomtester.database;

import android.os.AsyncTask;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import ch.labrat.roomtester.features.breed.Breed;
import ch.labrat.roomtester.features.breed.BreedDao;
import ch.labrat.roomtester.features.horse.HorseDao;
import ch.labrat.roomtester.features.horse.Horse;

class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final HorseDao mHorseDao;
    private final BreedDao breedDao;

    PopulateDbAsync(AnimaRoomDatabase db) {
        mHorseDao = db.horseDao();
        breedDao = db.breedDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {

        populateBreed();

        mHorseDao.deleteAll();
        Horse horse = new Horse("Navaro");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Bahira");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Escado");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Sweet Destiney");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Laskarino");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Butterfly");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Aragon");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Bilbao");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Don Fuego");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Allegra");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Domingo");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Hurrican");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Cassiopeia");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Cascliad Chayenne");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Hidalgo");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Rainbow");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Happy");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Vulkan");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        horse = new Horse("Diva Ladina");
        horse.setAllDetails(getRandomAge(), getRandomGender(),getRandomBreed(), "Color", getRandomOwner());
        mHorseDao.insert(horse);
        return null;
    }

    private void populateBreed() {
        Breed breed = new Breed("FirstBreed");
        breedDao.insert(breed);
        breed = new Breed("Second Breed");
        breedDao.insert(breed);
    }

    private Date getRandomAge() {
        long currentYear = LocalDate.now().toDateTimeAtStartOfDay().getMillis() - (long)31557600 * 1000;
        // 20 years earlier
        long  oldestYear = currentYear - (long)31557600 * 20 * 1000;
        Date date = new  Date(ThreadLocalRandom.current().nextLong(oldestYear, currentYear));
        return date;

    }

    private String getRandomBreed() {
        String[] breedList = {"Arabian", "Quarter Horse", "Thoroughbred", "Tennessee Walker", "Morgan", "Paint", "Appaloosa", "Miniature Horse", "Warmblood", "Andalusian"};
        int rnd = ThreadLocalRandom.current().nextInt(0, breedList.length);
        return breedList[rnd];
    }

    private String getRandomGender() {
        String[] genderList = {"Mare", "Stallion", "Gelding"};
        int rnd = ThreadLocalRandom.current().nextInt(0, genderList.length);
        return genderList[rnd];
    }

    private String getRandomOwner() {
        String[] list= {
        "Maison Tait",
        "Juniper Almond",
        "Bentley Roman",
        "Mercy Beil",
        "Hywel Cantrell",
        "Kaylee Slater",
        "Rhianne Meyer",
        "Ceri Drew",
        "Tahir Kaufman",
        "Sue Weaver" };
        int rnd = ThreadLocalRandom.current().nextInt(0, list.length);
        return list[rnd];
    }

}
