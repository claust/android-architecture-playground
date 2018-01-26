package dk.delectosoft.githubmvvm

import android.app.Application
import timber.log.Timber


class GitHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}