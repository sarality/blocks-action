package com.sarality.action;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import com.sarality.action.intent.IntentAppender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Action to start a new IntentService with a given set of parameters
 *
 * @author satya@ (Satya Puniani)
 */
public class StartIntentServiceAction implements ViewAction {

  private final Activity activity;
  private final Class<? extends IntentService> serviceClass;
  private final List<IntentAppender> intentAppenderList = new ArrayList<>();

  public StartIntentServiceAction(Activity activity, Class<? extends IntentService> serviceClass,
      List<IntentAppender> intentAppenderList) {
    this.activity = activity;
    this.serviceClass = serviceClass;
    if (intentAppenderList != null) {
      this.intentAppenderList.addAll(intentAppenderList);
    }
  }

  public StartIntentServiceAction(Activity activity, Class<? extends IntentService> serviceClass,
      IntentAppender... intentAppenders) {
    this(activity, serviceClass, intentAppenders == null ? null : Arrays.asList(intentAppenders));
  }

  public StartIntentServiceAction withIntent(IntentAppender intentAppender) {
    this.intentAppenderList.add(intentAppender);
    return this;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    Intent intent = new Intent(activity, serviceClass);

    for (IntentAppender intentAppender : intentAppenderList) {
      intentAppender.append(intent, actionContext);
    }

    activity.startService(intent);

    return true;
  }
}
