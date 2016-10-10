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

  public static final int DEFAULT_VIEW_ID = -1;

  private final Activity activity;
  private final int viewId;
  private final int tagResourceId;

  public TagValueExtractor(Activity activity, int viewId, int tagResourceId) {
    this.activity = activity;
    this.viewId = viewId;
    this.tagResourceId = tagResourceId;
  }

  public TagValueExtractor(Activity activity, int tagResourceId) {
    this(activity, DEFAULT_VIEW_ID, tagResourceId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public T extract(View view) {
    View taggedView = view;
    if (viewId != DEFAULT_VIEW_ID) {
      logger.debug("Looking up Tag with Id {} on View with Id {} ", Resources.name(activity, tagResourceId),
          Resources.name(activity, viewId));
      taggedView = activity.findViewById(viewId);
      if (taggedView == null) {
        throw new IllegalStateException("Could not find View with id "
            + activity.getResources().getResourceName(viewId));
      }
    }
    return (T) taggedView.getTag(tagResourceId);
  }
}
