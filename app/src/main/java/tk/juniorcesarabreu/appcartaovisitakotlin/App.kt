package tk.juniorcesarabreu.appcartaovisitakotlin

import android.app.Application
import tk.juniorcesarabreu.appcartaovisitakotlin.data.AppDatabase
import tk.juniorcesarabreu.appcartaovisitakotlin.data.BusinessCardRepository

class App : Application() {

    val database by lazy {
        AppDatabase.getDatabase(this)
    }

    val repository by lazy {
        BusinessCardRepository(database.businessDao())
    }
}