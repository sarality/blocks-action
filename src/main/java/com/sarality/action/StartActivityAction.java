package com.sarality.action;

import android.app.Activity;
import android.content.Intent;

import com.sarality.action.intent.IntentAppender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Action to start a new Activity with a given set of parameters
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class StartActivityAction implements ViewAction {

  private final Activity activity;
  private final Class<? extends Activity> activityClass;
  private final List<IntentAppender> intentAppenderList = new ArrayList<>();

  private boolean showAnimation;

  public StartActivityAction(Activity activity, Class<? extends Activity> activityClass,
      List<IntentAppender> intentAppenderList, boolean showAnimation) {
    this.activity = activity;
    this.activityClass = activityClass;
    if (intentAppenderList != null) {
      this.intentAppenderList.addAll(intentAppenderList);
    }
    this.showAnimation = showAnimation;
  }

  public StartActivityAction(Activity activity, Class<? extends Activity> activityClass,
      IntentAppender... intentAppenders) {
    this(activity, activityClass, intentAppenders == null? null : Arrays.asList(intentAppenders), false);
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    Intent intent = new Intent(activity, activityClass);
    if (!showAnimation) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }
    for (IntentAppender intentAppender : intentAppenderList) {
      intentAppender.append(intent, actionContext);
    }
    activity.startActivity(intent);
    return true;
  }
}
