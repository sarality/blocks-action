package com.sarality.action;

import android.app.Activity;
import android.view.View;

import com.sarality.util.log.Resources;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers Long Click Actions on a View or children of a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class LongClickActions implements ActionInitializer {

  private Activity activity;
  private Map<Integer, ViewAction> registry = new HashMap<>();
  private ViewAction parentViewAction;

  public LongClickActions(Activity activity) {
    this.activity = activity;
  }

  /**
   * Register the Action to be triggered on the View with the given Id.
   *
   * @param viewId If of the View that will trigger the action.
   * @param action Action to be performed
   * @return Click action with the
   */
  public LongClickActions register(int viewId, ViewAction action) {
    if (registry.containsKey(viewId)) {
      throw new IllegalStateException("Cannot register multiple actions for the same View with Id  " +
          Resources.name(activity, viewId));
    }
    registry.put(viewId, action);
    return this;
  }

  public LongClickActions register(ViewAction action) {
    this.parentViewAction = action;
    return this;
  }


  @Override
  public void init() {
    for (Integer viewId : registry.keySet()) {
      View view = activity.findViewById(viewId);
      if (view == null) {
        throw new IllegalStateException("Cannot find View with id " + Resources.name(activity, viewId));
      }
      ViewAction action = registry.get(viewId);
      if (action != null) {
        view.setOnLongClickListener(new Performer(action));
      } else {
        view.setOnLongClickListener(null);
      }
    }
  }

  @Override
  public void init(View parentView) {
    for (Integer viewId : registry.keySet()) {
      View view = parentView.findViewById(viewId);
      if (view == null) {
        throw new IllegalStateException("Cannot find View with id " + Resources.name(activity, viewId));
      }
      ViewAction action = registry.get(viewId);
      if (action != null) {
        view.setOnLongClickListener(new Performer(action));
      } else {
        view.setOnLongClickListener(null);
      }
    }
    if (registry.isEmpty() && parentViewAction != null) {
      parentView.setOnLongClickListener(new Performer(parentViewAction));
    }
  }

  /**
   * Listens to the Click events and triggers the action to be performed.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  private class Performer implements View.OnLongClickListener {

    private final ViewAction action;

    private Performer(ViewAction action) {
      this.action = action;
    }

    @Override
    public boolean onLongClick(View view) {
      action.perform(new ActionContext(view));
      return true;
    }
  }
}
