package com.sarality.action.intent;

import android.content.Intent;

import com.sarality.action.ActionContext;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Appends a List of serializable values to the intent
 *
 * @author satya@ (Satya Puniani)
 */
public class SerializableListValueAppender<T extends Serializable> implements IntentAppender {

  private final String extraName;
  private final ViewValueExtractor<ArrayList<T>> extractor;

  public SerializableListValueAppender(String extraName, ViewValueExtractor<ArrayList<T>> extractor) {
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
