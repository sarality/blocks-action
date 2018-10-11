package com.sarality.action;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Action to enable a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EnableViewAction implements ViewAction {

  private final Activity activity;
  private final View view;
  private Animation startAnimation;

  public EnableViewAction(Activity activity, int viewId) {
    this(activity, activity.findViewById(viewId));
  }

  public EnableViewAction(Activity activity, View view) {
    this.activity = activity;
    this.view = view;
  }

  public EnableViewAction withStartAnimation(int animationId) {
    startAnimation = AnimationUtils.loadAnimation(activity.getApplicationContext(), animationId);
    return this;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    if (startAnimation != null) {
      view.startAnimation(startAnimation);
    }
    view.setEnabled(true);
    return true;
  }
}
