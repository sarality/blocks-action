package com.sarality.action.intent;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  @SuppressWarnings("unchecked")
  public String extract(View view) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
    return preferences.getString(propertyName,"");
  }
}
