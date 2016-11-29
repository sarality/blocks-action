package com.sarality.action.intent;

import android.app.Activity;
import android.view.View;

/**
 * Default implementation of the Tag View provider
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DefaultActionViewProvider implements ActionViewProvider {

  private final Activity activity;
  private final int viewId;

  private static final int DEFAULT_VIEW_ID = -1;

  public DefaultActionViewProvider(Activity activity) {
    this(activity, DEFAULT_VIEW_ID);
  }

  public DefaultActionViewProvider(Activity activity, int viewId) {
    this.activity = activity;
    this.viewId = viewId;
  }


  @Override
  public View getView(View triggerView) {
    if (viewId == DEFAULT_VIEW_ID) {
      return triggerView;
    }
    View taggedView = activity.findViewById(viewId);
    if (taggedView == null) {
      throw new IllegalStateException("Could not find View with id "
          + activity.getResources().getResourceName(viewId));
    }
    return taggedView;
  }
}
