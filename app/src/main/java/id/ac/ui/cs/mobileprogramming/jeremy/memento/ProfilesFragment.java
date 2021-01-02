package id.ac.ui.cs.mobileprogramming.jeremy.memento;



import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;


import id.ac.ui.cs.mobileprogramming.jeremy.memento.databinding.FragmentProfilesBinding;


import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class ProfilesFragment extends Fragment {

    private FragmentProfilesBinding binding;
    List<Profiles> profiles;
    private ProfilesDetail detailsFragment = new ProfilesDetail();


    public ProfilesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profiles, container, false);

        Button creditButton = binding.getRoot().findViewById(R.id.credit_button);

        creditButton.bringToFront();

        creditButton.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "FLASH AND DISCO WARNING!!!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity().getApplicationContext(), CreditActivity.class));
        });

        FloatingActionButton addButton = binding.getRoot().findViewById(R.id.addProfile);
        addButton.bringToFront();
        addButton.setOnClickListener(view -> {
            startActivity(new Intent(getActivity().getApplicationContext(), AddProfileActivity.class));
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        try {
            profiles = viewModel.getAllProfiles();
        }
        catch(ExecutionException | InterruptedException e){
            profiles = null;
        }
        ProfilesAdapter adapter = new ProfilesAdapter(profiles);



        binding.recyclerView.setAdapter(adapter);
        adapter.setListener((v, position) -> {
            if (isNetworkAvailable()) {
                viewModel.setSelected(adapter.getProfileAt(position));
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.main, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
            else{
                Toast.makeText(getActivity(), "No internet is available, please connect to internet", Toast.LENGTH_LONG).show();
            }
        });
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}