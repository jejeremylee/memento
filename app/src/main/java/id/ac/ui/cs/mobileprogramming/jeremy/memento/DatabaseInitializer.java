package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.os.AsyncTask;



public class DatabaseInitializer {

    public static void populateAsync(final AppDatabase database) {
        new PopulateDbAsync(database).execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase database;

        PopulateDbAsync(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // If the Database is empty, add the initial data.
            if (database.profileDao().rowCount() == 0) {
                Profiles nasya = new Profiles();
                nasya.setNameProfile("nasya oristania");
                nasya.setNickNameProfile("nasya");
                nasya.setPhoneProfile("0855555555");
                nasya.setBirthdayProfile("12 july 9999");
                nasya.setAddressProfile("Jl jalan ke sukabumi cakepp");
                nasya.setImg(R.drawable.template);

                database.profileDao().insertProfile(nasya);
            }

            return null;
        }
    }
}

