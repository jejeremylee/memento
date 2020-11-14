package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertProfile(Profiles profile);

    @Query("SELECT * FROM profiles")
    List<Profiles> selectAllProfiles();
}