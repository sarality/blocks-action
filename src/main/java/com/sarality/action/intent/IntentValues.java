package com.sarality.action.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Utility to extract data from an {@link Intent}.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class IntentValues {

  private final Bundle extras;

  public IntentValues(Activity activity) {
    Intent intent = activity.getIntent();
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

  public Double getDouble(String parameterName) {
    if (extras != null && extras.containsKey(parameterName)) {
      return extras.getDouble(parameterName);
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
