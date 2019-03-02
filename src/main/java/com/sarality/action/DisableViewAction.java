package com.sarality.action;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Action to disable a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DisableViewAction implements ViewAction {

  private final Activity activity;
  private final View view;
  private Animation animation;

  public DisableViewAction(Activity activity, int viewId) {
    this(activity, activity.findViewById(viewId));
  }

  public DisableViewAction(Activity activity, View view) {
    this.activity = activity;
    this.view = view;
  }

  public DisableViewAction withAnimation(int animationId) {
    animation = AnimationUtils.loadAnimation(activity.getApplicationContext(), animationId);
    return this;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    if (animation != null) {
      view.setAnimation(animation);
    }
    view.setEnabled(false);
    return true;
  }
}
