package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel{
    private MutableLiveData<Profiles> selected = new MutableLiveData<>();

    public void setSelected(Profiles profile) {
        selected.setValue(profile);
    }

    public MutableLiveData<Profiles> getSelected() {
        return selected;
    }
}
