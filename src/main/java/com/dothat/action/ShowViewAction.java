package com.dothat.action;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Action to show a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ShowViewAction implements ViewAction {

  private final Activity activity;
  private final View view;
  private Animation startAnimation;

  public ShowViewAction(Activity activity, int viewId) {
    this(activity, activity.findViewById(viewId));
  }

  public ShowViewAction(Activity activity, View view) {
    this.activity = activity;
    this.view = view;
  }

  public ShowViewAction withStartAnimation(int animationId) {
    startAnimation = AnimationUtils.loadAnimation(activity.getApplicationContext(), animationId);
    return this;
  }

  @Override
  public void perform() {
    if (startAnimation != null) {
      view.startAnimation(startAnimation);
    }
    view.setVisibility(View.VISIBLE);
  }
}
