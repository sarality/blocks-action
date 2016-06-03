package com.sarality.action;

import android.app.Activity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers Click Actions on a View or children of a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ClickActions implements ActionInitializer {

  private Activity activity;
  private Map<Integer, ViewAction> registry = new HashMap<>();

  public ClickActions(Activity activity) {
    this.activity = activity;
  }

  /**
   * Register the Action to be triggered on the View with the given Id.
   *
   * @param viewId If of the View that will trigger the action.
   * @param action Action to be performed
   * @return Click action with the
   */
  public ClickActions register(int viewId, ViewAction action) {
    if (registry.containsKey(viewId)) {
      throw new IllegalStateException("Cannot register multiple actions for the same View with Id  " +
          activity.getResources().getResourceName(viewId));
    }
    registry.put(viewId, action);
    return this;
  }

  @Override
  public void init() {
    for (Integer viewId : registry.keySet()) {
      View view = activity.findViewById(viewId);
      ViewAction action = registry.get(viewId);
      view.setOnClickListener(new Performer(action));
    }
  }

  @Override
  public void init(View parentView) {
    for (Integer viewId : registry.keySet()) {
      View view = parentView.findViewById(viewId);
      ViewAction action = registry.get(viewId);
      view.setOnClickListener(new Performer(action));
    }
  }

  /**
   * Listens to the Click events and triggers the action to be performed.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  private class Performer implements View.OnClickListener {

    private final ViewAction action;

    public Performer(ViewAction action) {
      this.action = action;
    }

    @Override
    public void onClick(View view) {
      action.perform();
    }
  }
}