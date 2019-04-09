package com.sarality.action;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Action to show a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ShowViewAction implements ViewAction {

  private final Activity activity;
  private final List<View> viewList = new ArrayList<>();
  private Animation startAnimation;

  public ShowViewAction(Activity activity, int viewId, Integer... additionalViewIds) {
    this(activity, activity.findViewById(viewId));
    if (additionalViewIds != null) {
      for (Integer additionalViewId : additionalViewIds) {
        viewList.add(activity.findViewById(additionalViewId));
      }
    }
  }

  public ShowViewAction(Activity activity, View view) {
    this.activity = activity;
    this.viewList.add(view);
  }

  public ShowViewAction withStartAnimation(int animationId) {
    startAnimation = AnimationUtils.loadAnimation(activity.getApplicationContext(), animationId);
    return this;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    for (View view : viewList) {
      if (startAnimation != null) {
        view.startAnimation(startAnimation);
      }
      view.setVisibility(View.VISIBLE);
    }
    return true;
  }
}
