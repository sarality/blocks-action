package com.sarality.action.intent;

import android.content.Intent;

import com.sarality.action.ActionContext;

/**
 * Appends a Long value to the intent
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class LongValueAppender implements IntentAppender {

  private final String extraName;
  private final ViewValueExtractor<Long> extractor;

  public LongValueAppender(String extraName, ViewValueExtractor<Long> extractor) {
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
