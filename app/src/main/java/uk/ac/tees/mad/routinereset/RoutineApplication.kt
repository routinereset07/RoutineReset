package uk.ac.tees.mad.routinereset

import android.app.Application
import uk.ac.tees.mad.routinereset.di.AppModule
import uk.ac.tees.mad.routinereset.preference.AppPreference

class RoutineApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule.init(this) //db initialise
        AppPreference.init(this) //preference initialise
    }
}