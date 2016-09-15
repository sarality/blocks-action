package com.sarality.action.intent;

import android.view.View;

/**
 * Extracts a value from a Tag set on a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TagValueExtractor<T> implements ViewValueExtractor<T> {

  private final int tagResourceId;

  public TagValueExtractor(int tagResourceId) {
    this.tagResourceId = tagResourceId;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T extract(View view) {
    return (T) view.getTag(tagResourceId);
  }
}
