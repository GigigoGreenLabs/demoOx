package demoox.gigigo.com.demoox;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;


/**
 * Created by josemoralejo on 4/2/16.
 */
public class PreferencesImp {

    protected SharedPreferences sharedPreferences;
    private static final String ox_key = "session_ox_key";
    private static final String ox_secret = "session_ox_secret";


    public PreferencesImp(Context ctx) {
        sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public String getKey() {
        return sharedPreferences.getString(ox_key, "key");
    }

    public String getSecret() {
        return sharedPreferences.getString(ox_secret, "secret");
    }

    public void setOx_key(String value) {
        this.sharedPreferences.edit().putString(ox_key, value).commit();
    }

    public void setOx_secret(String value) {
        this.sharedPreferences.edit().putString(ox_secret, value).commit();
    }
}
