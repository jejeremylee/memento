package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.MutableLiveData;


import java.util.List;
import java.util.concurrent.ExecutionException;


public class SharedViewModel extends AndroidViewModel {

    private ProfilesRepository profilesRepository;

    private MutableLiveData<Profiles> selected = new MutableLiveData<>();
    private List<Profiles> profiles;

    public SharedViewModel (Application application) {
        super(application);
        profilesRepository = new ProfilesRepository(application);
    }


    List<Profiles> getAllProfiles() throws ExecutionException, InterruptedException {
        return profilesRepository.getAll();}

    public void insertProfile(Profiles profile) {
        profilesRepository.insertProfile(profile); }


    public void setSelected(Profiles profile) {
        selected.setValue(profile);
    }

    public MutableLiveData<Profiles> getSelected() {
        return selected;
    }
}
