package com.sarality.action;

import android.view.View;

/**
 * Interface for classes that provide the data for the action.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ActionContext {

  private final View view;

  public ActionContext(View view) {
    this.view = view;
  }

  public View getView() {
    return view;
  }
}
