package com.sarality.action.intent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.sarality.action.ActionContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    intent.putExtra(extraName, extractor.extract(actionContext.getView()));
  }

}
