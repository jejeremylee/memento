package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import id.ac.ui.cs.mobileprogramming.jeremy.memento.databinding.FragmentProfilesDetailBinding;

public class ProfilesDetail extends Fragment {

    private FragmentProfilesDetailBinding binding;
    public ProfilesDetail() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profiles_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getSelected().observe(getViewLifecycleOwner(), item -> {
            binding.name.setText(item.getNameProfile());
            binding.nickname.setText(item.getNickNameProfile());
            binding.phone.setText(item.getPhoneProfile());
            binding.birthday.setText(item.getBirthdayProfile());
            binding.address.setText(item.getAddressProfile());
            binding.socmed.setText(item.getSocialMediaProfile());
            binding.image.setImageBitmap(BitmapFactory.decodeFile(item.getImg()));
        });

    }
}