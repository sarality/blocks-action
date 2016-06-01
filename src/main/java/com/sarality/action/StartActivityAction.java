package com.sarality.action;

import android.app.Activity;
import android.content.Intent;

/**
 * Action to show a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class StartActivityAction implements ViewAction {

  private final Activity activity;
  private final Class<? extends Activity> activityClass;
  private boolean showAnimation;

  public StartActivityAction(Activity activity, Class<? extends Activity> activityClass, boolean showAnimation) {
    this.activity = activity;
    this.activityClass = activityClass;
    this.showAnimation = showAnimation;
  }

  public StartActivityAction(Activity activity, Class<? extends Activity> activityClass) {
    this(activity, activityClass, false);
  }

  @Override
  public void perform() {
    Intent intent = new Intent(activity, activityClass);
    if (!showAnimation) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }
    activity.startActivity(intent);
  }
}
