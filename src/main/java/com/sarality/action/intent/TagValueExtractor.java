package com.sarality.action.intent;

import android.app.Activity;
import android.view.View;

import com.sarality.util.log.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extracts a value from a Tag set on a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TagValueExtractor<T> implements ViewValueExtractor<T> {

  private static final Logger logger = LoggerFactory.getLogger(TagValueExtractor.class.getCanonicalName());

  private final Activity activity;
  private final ActionViewProvider viewProvider;
  private final int tagResourceId;

  public TagValueExtractor(Activity activity, ActionViewProvider viewProvider, int tagResourceId) {
    this.activity = activity;
    this.viewProvider = viewProvider;
    this.tagResourceId = tagResourceId;
  }

  public TagValueExtractor(Activity activity, int viewId, int tagResourceId) {
    this(activity, new DefaultActionViewProvider(activity, viewId), tagResourceId);
  }

  public TagValueExtractor(Activity activity, int tagResourceId) {
    this(activity, new DefaultActionViewProvider(activity), tagResourceId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public T extract(View view) {
    View taggedView = viewProvider.getView(view);
    if (taggedView.getId() > 0) {
      logger.debug("Looking up Tag with Id {} on View with Id {} ", Resources.name(activity, tagResourceId),
          Resources.name(activity, taggedView.getId()));
    }
    return (T) taggedView.getTag(tagResourceId);
  }
}
