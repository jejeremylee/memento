package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

public class AddProfileActivity extends AppCompatActivity {

    private EditText name;
    private EditText nickname;
    private EditText phone;
    private EditText birthday;
    private EditText address;
    private String picturePath;
    private static int RESULT_LOAD_IMAGE = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile_activity);
        name = findViewById(R.id.name);
        nickname = findViewById(R.id.nickname);
        phone = findViewById(R.id.phone);
        birthday = findViewById(R.id.birthday);
        address = findViewById(R.id.address);

        ImageView getImage = findViewById(R.id.addImage);
        getImage.setOnClickListener(arg0 -> {
            try {
                if(ActivityCompat.checkSelfPermission(AddProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
                else {
                    storagePermissionReq();
                 }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(view -> {
            Profiles newProfile = new Profiles();
            newProfile.setNameProfile(name.getText().toString());
            newProfile.setNickNameProfile(nickname.getText().toString());
            newProfile.setBirthdayProfile(birthday.getText().toString());
            newProfile.setPhoneProfile(phone.getText().toString());
            newProfile.setAddressProfile(address.getText().toString());
            newProfile.setImg(picturePath);

            SharedViewModel viewModel = new ViewModelProvider(AddProfileActivity.this).get(SharedViewModel.class);
            viewModel.insertProfile(newProfile);

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(this, "New profile added",
                    Toast.LENGTH_LONG).show();
        });
    }

    private void storagePermissionReq() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            startActivity(new Intent(getApplicationContext(), PermissionActivity.class));
        }
        else{
            ActivityCompat.requestPermissions(AddProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_LOAD_IMAGE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RESULT_LOAD_IMAGE )  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied ", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.addImage);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Toast.makeText(this, "You have pick an image",
                    Toast.LENGTH_LONG).show();

        }
    }

}
