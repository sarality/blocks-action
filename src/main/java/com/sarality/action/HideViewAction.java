package com.sarality.action;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Action to show a View or a set of Views
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class HideViewAction implements ViewAction {

  private final Activity activity;
  private final List<View> viewList = new ArrayList<>();
  private Animation animation;

  public HideViewAction(Activity activity, int viewId, Integer... additionalViewIds) {
    this(activity, activity.findViewById(viewId));
    if (additionalViewIds != null) {
      for (Integer additionalViewId : additionalViewIds) {
        viewList.add(activity.findViewById(additionalViewId));
      }
    }
  }

  public HideViewAction(Activity activity, View view) {
    this.activity = activity;
    this.viewList.add(view);
  }

  public HideViewAction withAnimation(int animationId) {
    animation = AnimationUtils.loadAnimation(activity.getApplicationContext(), animationId);
    return this;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    for (View view : viewList) {
      if (animation != null) {
        view.setAnimation(animation);
      }
      view.setVisibility(View.GONE);
    }
    return true;
  }
}
