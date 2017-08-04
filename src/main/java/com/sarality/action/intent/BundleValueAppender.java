package com.sarality.action.intent;

import android.content.Intent;
import android.os.Bundle;

import com.sarality.action.ActionContext;

/**
 * Appends a Bundle to the intent.
 *
 * @author satya@ (Satya Puniani)
 */
public class BundleValueAppender implements IntentAppender {
  private final String extraName;
  private final Bundle extraBundle;

  public BundleValueAppender(String extraName, Bundle extraBundle) {
    this.extraName = extraName;
    this.extraBundle = extraBundle;
  }

  @Override
  public void append(Intent intent, ActionContext actionContext) {
    intent.putExtra(extraName, extraBundle);
  }
}
