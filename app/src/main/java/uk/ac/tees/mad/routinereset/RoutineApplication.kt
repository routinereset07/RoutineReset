package uk.ac.tees.mad.routinereset

import android.app.Application
import uk.ac.tees.mad.routinereset.di.AppModule

class RoutineApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule.init(this)
    }
}