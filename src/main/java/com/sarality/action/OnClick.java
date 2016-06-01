package com.sarality.action;

import android.app.Activity;
import android.view.View;

/**
 * Action Performer to execute an action when a View is clicked.
 *
 */
public class OnClick implements TriggerEventDefinition, View.OnClickListener {

  private Activity activity;
  private View view;
  private ViewAction action;

  public OnClick(Activity activity, int viewId) {
    this(activity, activity.findViewById(viewId));
  }

  public OnClick(Activity activity, View view) {
    this.activity = activity;
    this.view = view;
  }

  // Implements TriggerEventDefinition
  @Override
  public Activity getActivity() {
    return activity;
  }

  // Implements TriggerEventDefinition
  @Override
  public View getView() {
    return view;
  }

  // Implements TriggerEventDefinition
  @Override
  public void setupAction(ViewAction action) {
    this.action = action;
    view.setOnClickListener(this);
  }

  // Implements View.OnClickListener
  @Override
  public void onClick(View view) {
    if (action != null) {
      action.perform();
    }
  }
}
