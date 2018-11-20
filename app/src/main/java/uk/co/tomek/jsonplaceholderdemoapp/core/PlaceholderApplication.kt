package uk.co.tomek.jsonplaceholderdemoapp.core

import android.app.Application
import android.os.StrictMode
import com.squareup.leakcanary.AndroidExcludedRefs
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.BuildConfig
import uk.co.tomek.jsonplaceholderdemoapp.core.di.applicationModule
import uk.co.tomek.jsonplaceholderdemoapp.core.di.networkModule

/**
 * Main Application class.
 */
class PlaceholderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            setupLeakCanary()
            enableStrictMode()
        }

        startKoin(this, listOf(
            applicationModule,
            networkModule
        ))
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyFlashScreen()
            .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
            .detectLeakedSqlLiteObjects()
            .detectLeakedClosableObjects()
            .penaltyLog()
            .penaltyDeath()
            .build())
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.refWatcher(this)
            .excludedRefs(AndroidExcludedRefs.createAppDefaults().build())
            .buildAndInstall()
    }
}