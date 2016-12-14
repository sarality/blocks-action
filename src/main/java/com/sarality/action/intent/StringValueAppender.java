package com.sarality.action.intent;

import android.content.Intent;

import com.sarality.action.ActionContext;

/**
 * Appends a String value to the intent.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class StringValueAppender implements IntentAppender {
  private final String extraName;
  private final ViewValueExtractor<String> extractor;

  public StringValueAppender(String extraName, ViewValueExtractor<String> valueExtractor) {
    this.extraName = extraName;
    this.extractor = valueExtractor;
  }

  @Override
  public void append(Intent intent, ActionContext actionContext) {
    intent.putExtra(extraName, extractor.extract(actionContext.getView()));
  }
}
