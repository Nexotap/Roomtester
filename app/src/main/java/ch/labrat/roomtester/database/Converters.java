package ch.labrat.roomtester.database;

import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import androidx.room.TypeConverter;

public class Converters {
    private static final String TAG="Converters";

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromStringSet(Set<String> strings) {
        if (strings==null) {
            return(null);
        }

        StringWriter result=new StringWriter();
        JsonWriter json=new JsonWriter(result);

        try {
            json.beginArray();

            for (String s : strings) {
                json.value(s);
            }

            json.endArray();
            json.close();
        }
        catch (IOException e) {
            Log.e(TAG, "Exception creating JSON", e);
        }

        return(result.toString());
    }

    @TypeConverter
    public static Set<String> toStringSet(String strings) {
        if (strings==null) {
            return(null);
        }

        StringReader reader=new StringReader(strings);
        JsonReader json=new JsonReader(reader);
        HashSet<String> result=new HashSet<>();

        try {
            json.beginArray();

            while (json.hasNext()) {
                result.add(json.nextString());
            }

            json.endArray();
        }
        catch (IOException e) {
            Log.e(TAG, "Exception parsing JSON", e);
        }

        return(result);
    }
}
