package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import id.ac.ui.cs.mobileprogramming.jeremy.memento.databinding.FragmentProfilesBinding;


import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ProfilesFragment extends Fragment {

    private FragmentProfilesBinding binding;
    private AppDatabase db;

    private ProfilesDetail detailsFragment = new ProfilesDetail();

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

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "profiledb").build();

        Profiles nasya = new Profiles();
        nasya.setNamaProfile("nasya oris");
        nasya.setNickNameProfile("nasya");
        nasya.setPhoneProfile("0855555555");
        nasya.setBirthdayProfile("12 july 9999");
        nasya.setAddressProfile("asjfbqigiqw");
        nasya.setImg(R.drawable.template);

        db.profileDao().insertProfile(nasya);

        List<Profiles> profiles = db.profileDao().selectAllProfiles();



        ProfilesAdapter adapter = new ProfilesAdapter(profiles);


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