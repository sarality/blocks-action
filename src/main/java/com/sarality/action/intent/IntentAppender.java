package com.sarality.action.intent;

import android.content.Intent;

import com.sarality.action.ActionContext;

/**
 * Populates an Intent with additional information.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface IntentAppender {

  void append(Intent intent, ActionContext actionContext);
}
