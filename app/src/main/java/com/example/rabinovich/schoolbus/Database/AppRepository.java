package com.example.rabinovich.schoolbus.Database;

import android.app.Application;

/**
 * Created by Rabinovich on 5/30/2018.
 */

public class AppRepository {

    private AppDao mAppDao;

    AppRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
    }
}
