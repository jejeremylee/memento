package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel{
    private MutableLiveData<Profile> selected = new MutableLiveData<>();

    public void setSelected(Profile profile) {
        selected.setValue(profile);
    }

    public MutableLiveData<Profile> getSelected() {
        return selected;
    }
}
