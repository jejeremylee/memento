package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddProfileActivity extends AppCompatActivity {

    private EditText name;
    private EditText nickname;
    private EditText phone;
    private EditText birthday;
    private EditText address;
    private ProfilesFragment profilesFragment = new ProfilesFragment();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile_activity);
        name = findViewById(R.id.name);
        nickname = findViewById(R.id.nickname);
        phone = findViewById(R.id.phone);
        birthday = findViewById(R.id.birthday);
        address = findViewById(R.id.address);
        AppDatabase appDatabase = AppDatabase.getInstance(AddProfileActivity.this);
        Button saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(view -> {
            Profiles newProfile = new Profiles();
            newProfile.setNameProfile(name.getText().toString());
            newProfile.setNickNameProfile(nickname.getText().toString());
            newProfile.setBirthdayProfile(birthday.getText().toString());
            newProfile.setPhoneProfile(phone.getText().toString());
            newProfile.setAddressProfile(address.getText().toString());
            newProfile.setImg(R.drawable.template);

            SharedViewModel viewModel = new ViewModelProvider(AddProfileActivity.this).get(SharedViewModel.class);
            viewModel.insertProfile(newProfile);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}
