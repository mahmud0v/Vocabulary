package uz.gita.vocabulary.app

import android.app.Application
import uz.gita.vocabulary.db.AppDatabase

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.initDB(this)
    }


}