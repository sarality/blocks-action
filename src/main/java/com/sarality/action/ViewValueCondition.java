package com.sarality.action;

import android.view.View;

/**
 * Add description here
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class ViewValueCondition<V extends View> implements ActionCondition {
  private final V view;
  private final String expectedValue;

  public ViewValueCondition(V view, String expectedValue) {
    this.view = view;
    this.expectedValue = expectedValue;
  }

  protected abstract String getViewValue(V view);

  @Override
  public boolean isTrue(View contextView) {
    String value = getViewValue(view);
    return (value == null && expectedValue == null) || (value != null && value.equals(expectedValue));
  }
}
