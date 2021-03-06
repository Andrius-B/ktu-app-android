package lt.hacker_house.ktu_ais.views.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.widget.ListView
import com.mcxiaoke.koi.ext.toast
import lt.hacker_house.ktu_ais.BuildConfig
import lt.hacker_house.ktu_ais.R
import lt.hacker_house.ktu_ais.db.User
import lt.hacker_house.ktu_ais.services.GetGradesIntentService
import lt.hacker_house.ktu_ais.utils.Prefs
import lt.hacker_house.ktu_ais.utils.Prefs.ABOUT
import lt.hacker_house.ktu_ais.utils.Prefs.LOGOUT
import lt.hacker_house.ktu_ais.utils.Prefs.SELECTED_SEMESTER
import lt.hacker_house.ktu_ais.utils.Prefs.SHOW_NOTIFICATION
import lt.hacker_house.ktu_ais.utils.Prefs.UPDATE_INTERVAL
import lt.hacker_house.ktu_ais.utils.startActivityNoBack
import lt.hacker_house.ktu_ais.views.activities.SplashActivity


/**
 * Created by simonas on 5/20/17.
 */

class SettingsFragment: PreferenceFragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var selectedSemester: ListPreference
    lateinit var updateInterval: ListPreference
    lateinit var showNotification: CheckBoxPreference
    lateinit var logout: Preference
    lateinit var about: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)

        selectedSemester = findPreference(SELECTED_SEMESTER) as ListPreference
        updateInterval = findPreference(UPDATE_INTERVAL) as ListPreference
        showNotification = findPreference(SHOW_NOTIFICATION) as CheckBoxPreference
        logout = findPreference(LOGOUT) as Preference
        about = findPreference(ABOUT) as Preference

        User.getSemesters { userModel, entries, values ->
            selectedSemester.entries = entries
            selectedSemester.entryValues = values
            selectedSemester.value = Prefs.getCurrentSemester(userModel).toDataString()
        }

        updateInterval.entries = Prefs.UPDATE_INTERVAL_ENTRIES_NAMES
        updateInterval.entryValues = Prefs.UPDATE_INTERVAL_ENTRIES_VALUES
        updateInterval.value = Prefs.UPDATE_INTERVAL_DEFAULT

        showNotification.isChecked = Prefs.SHOW_NOTIFICATION_DEFAULT

        logout.setOnPreferenceClickListener { _ ->
            if (User.logout()) {
                activity.startActivityNoBack(SplashActivity::class.java)
            }
            false
        }

        val appVersionName = BuildConfig.VERSION_NAME
        about.summary = getString(R.string.version) + " " + appVersionName
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val list = view.findViewById(android.R.id.list) as ListView
        list.divider = null
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        when(key) {
            SELECTED_SEMESTER -> {
                User.update { _, _ -> }
                activity.toast(getString(R.string.loading_new_grades))
            }
            SHOW_NOTIFICATION -> {
                GetGradesIntentService.startBackgroundService(activity)
            }
            UPDATE_INTERVAL -> {
                GetGradesIntentService.startBackgroundService(activity)
            }
        }
    }
}
