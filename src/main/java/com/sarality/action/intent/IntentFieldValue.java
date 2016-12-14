package com.sarality.action.intent;

import android.view.View;

/**
 * A value of a Field that is passed to an Intent
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class IntentFieldValue<T> implements ViewValueExtractor<T> {
  private T value;

  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public T extract(View view) {
    return value;
  }
}
