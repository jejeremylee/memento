package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;


import id.ac.ui.cs.mobileprogramming.jeremy.memento.databinding.FragmentProfilesBinding;


import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class ProfilesFragment extends Fragment {

    private FragmentProfilesBinding binding;
    List<Profiles> profiles;
    private ProfilesDetail detailsFragment = new ProfilesDetail();
    //private SharedViewModel viewModel;

    public ProfilesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profiles, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


         //viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(SharedViewModel.class);
        //viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel.class);

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        try {
            profiles = viewModel.getAllProfiles();
        }
        catch(ExecutionException | InterruptedException e){
            profiles = null;
        }
        ProfilesAdapter adapter = new ProfilesAdapter(profiles);

        Log.d("profile", adapter.getProfileAt(0).nameProfile);
        binding.recyclerView.setAdapter(adapter);
        adapter.setListener((v, position) -> {
            viewModel.setSelected(adapter.getProfileAt(position));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

}