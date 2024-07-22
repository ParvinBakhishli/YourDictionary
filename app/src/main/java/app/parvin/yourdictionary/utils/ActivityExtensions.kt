package app.parvin.yourdictionary.utils

import androidx.appcompat.app.AppCompatActivity
import app.parvin.yourdictionary.presentation.dialogs.NoInternetDialog


fun AppCompatActivity.showNoInternet() {
    if (supportFragmentManager.findFragmentByTag(NoInternetDialog.TAG) != null) {
        return
    }

    NoInternetDialog().show(supportFragmentManager, NoInternetDialog.TAG)
}