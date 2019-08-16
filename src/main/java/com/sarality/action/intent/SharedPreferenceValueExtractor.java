package com.sarality.action.intent;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Extracts a value from a Shared Preference.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SharedPreferenceValueExtractor implements ViewValueExtractor<String> {

  private final Activity activity;
  private final String propertyName;

  public SharedPreferenceValueExtractor(Activity activity, String propertyName) {
    this.activity = activity;
    this.propertyName = propertyName;
  }

  @Override
  public String extract(View view) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    if (preferences.contains(propertyName)) {
      return preferences.getString(propertyName, "");
    }
    return null;
  }
}
