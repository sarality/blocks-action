package com.sarality.action.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

import hirondelle.date4j.DateTime;

/**
 * Utility to extract data from an {@link Intent}.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class IntentValues {

  private final Bundle extras;

  public IntentValues(Activity activity) {
    this(activity.getIntent());
  }

  public IntentValues(Intent intent) {
    if (intent != null) {
      this.extras = intent.getExtras();
    } else {
      this.extras = null;
    }
  }

  public Long getLong(String parameterName) {
    if (extras != null && extras.containsKey(parameterName)) {
      return extras.getLong(parameterName);
    }
    return null;
  }

  public <E extends Enum<E>> E getEnum(String parameterName, Class<E> enumClass) {
    String value = getString(parameterName);
    if (value == null) {
      return null;
    }
    return Enum.valueOf(enumClass, value);
  }

  public DateTime getDate(String parameterName) {
    String value = getString(parameterName);
    if (value == null) {
      return null;
    }
    return new DateTime(value);
  }

  public Double getDouble(String parameterName) {
    if (extras != null && extras.containsKey(parameterName)) {
      if (extras.get(parameterName) != null) {
        return extras.getDouble(parameterName);
      }
    }
    return null;
  }

  public String getString(String parameterName) {
    if (extras != null && extras.containsKey(parameterName)) {
      return extras.getString(parameterName);
    }
    return null;

  }

  @SuppressWarnings("unchecked")
  public <T extends Serializable> ArrayList<T> getSerializableList(String parameterName) {
    if (extras != null && extras.containsKey(parameterName)) {
      return (ArrayList<T>) extras.getSerializable(parameterName);
    }
    return null;

  }
}
