package com.sarality.action;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Action to show a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class HideViewAction implements ViewAction {

  private final Activity activity;
  private final View view;
  private Animation animation;

  public HideViewAction(Activity activity, int viewId) {
    this(activity, activity.findViewById(viewId));
  }

  public HideViewAction(Activity activity, View view) {
    this.activity = activity;
    this.view = view;
  }

  public HideViewAction withAnimation(int animationId) {
    animation = AnimationUtils.loadAnimation(activity.getApplicationContext(), animationId);
    return this;
  }

  @Override
  public void perform() {
    if (animation != null) {
      view.setAnimation(animation);
    }
    view.setVisibility(View.GONE);
  }
}
