package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.app.Application;
import android.os.AsyncTask;


import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProfilesRepository {

        private ProfileDao profileDao;

        public ProfilesRepository(Application application){
            AppDatabase db = AppDatabase.getInstance(application);
            profileDao = db.profileDao();
        }
        public List<Profiles> getAll() throws ExecutionException, InterruptedException {
            return new getAllAsyncTask(profileDao).execute().get();
        }

        private static class getAllAsyncTask extends android.os.AsyncTask<Void, Void, List<Profiles>> {

            private ProfileDao profileDao;
            List<Profiles> a;

            getAllAsyncTask(ProfileDao dao) {
                profileDao = dao;
            }

            @Override
            protected List<Profiles> doInBackground(Void... voids) {
                return profileDao.getAllProfiles();
            }
        }


        public void insertProfile (Profiles profile) {
            new insertAsyncTask(profileDao).execute(profile);
        }


        private static class insertAsyncTask extends AsyncTask<Profiles, Void, Void> {

            private ProfileDao profileAsyncTaskDao;

            insertAsyncTask(ProfileDao dao) {
                profileAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final Profiles... params) {
                profileAsyncTaskDao.insertProfile(params[0]);
                return null;
            }
        }

}
