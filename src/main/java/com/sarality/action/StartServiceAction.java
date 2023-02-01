package com.sarality.action;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Build;

import com.sarality.action.intent.IntentAppender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Action to start a new IntentService with a given set of parameters
 *
 * @author satya@ (Satya Puniani)
 */
public class StartServiceAction implements ViewAction {

  private final Activity activity;
  private final Class<? extends IntentService> serviceClass;
  private final List<IntentAppender> intentAppenderList = new ArrayList<>();

  public StartServiceAction(Activity activity, Class<? extends IntentService> serviceClass,
      List<IntentAppender> intentAppenderList) {
    this.activity = activity;
    this.serviceClass = serviceClass;
    if (intentAppenderList != null) {
      this.intentAppenderList.addAll(intentAppenderList);
    }
  }

  public StartServiceAction(Activity activity, Class<? extends IntentService> serviceClass,
      IntentAppender... intentAppenders) {
    this(activity, serviceClass, intentAppenders == null ? null : Arrays.asList(intentAppenders));
  }

  public StartServiceAction withIntent(IntentAppender intentAppender) {
    this.intentAppenderList.add(intentAppender);
    return this;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    Intent intent = new Intent(activity, serviceClass);

    for (IntentAppender intentAppender : intentAppenderList) {
      intentAppender.append(intent, actionContext);
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      activity.startForegroundService(intent);
    } else {
      activity.startService(intent);
    }

    return true;
  }
}
