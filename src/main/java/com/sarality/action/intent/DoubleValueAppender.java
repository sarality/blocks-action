package com.sarality.action.intent;

import android.content.Intent;

import com.sarality.action.ActionContext;

/**
 * Appends a Double value to the intent
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DoubleValueAppender implements IntentAppender {

  private final String extraName;
  private final ViewValueExtractor<Double> extractor;

  public DoubleValueAppender(String extraName, ViewValueExtractor<Double> extractor) {
    this.extraName = extraName;
    this.extractor = extractor;
  }

  @Override
  public void append(Intent intent, ActionContext actionContext) {
    if (actionContext == null) {
      intent.putExtra(extraName, extractor.extract(null));
    } else {
      intent.putExtra(extraName, extractor.extract(actionContext.getView()));
    }
  }
}
