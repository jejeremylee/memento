package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Profiles.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProfileDao profileDao();
}
