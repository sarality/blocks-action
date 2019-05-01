package com.sarality.action;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;

import com.sarality.util.log.Resources;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers Item Selection Actions on a View or children of a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckedActions implements ActionInitializer {

  private Activity activity;
  private Map<Integer, ViewAction> checkedRegistry = new HashMap<>();
  private Map<Integer, ViewAction> uncheckedRegistry = new HashMap<>();

  public CheckedActions(Activity activity) {
    this.activity = activity;
  }

  public CheckedActions register(int viewId, ViewAction checkedAction, ViewAction uncheckedAction) {
    if (checkedRegistry.containsKey(viewId)) {
      throw new IllegalStateException("Cannot register multiple actions for the same View with Id  " +
          Resources.name(activity, viewId));
    }
    checkedRegistry.put(viewId, checkedAction);
    uncheckedRegistry.put(viewId, uncheckedAction);
    return this;
  }

  @Override
  public void init() {
    for (Integer viewId : checkedRegistry.keySet()) {
      CompoundButton view = activity.findViewById(viewId);
      if (view == null) {
        throw new IllegalStateException("Cannot find View with id " + Resources.name(activity, viewId));
      }
      ViewAction checkedAction = checkedRegistry.get(viewId);
      ViewAction uncheckedAction = uncheckedRegistry.get(viewId);
      view.setOnCheckedChangeListener(new Performer(checkedAction, uncheckedAction));
    }
  }

  @Override
  public void init(View parentView) {
    for (Integer viewId : checkedRegistry.keySet()) {
      CompoundButton view = parentView.findViewById(viewId);
      if (view == null) {
        throw new IllegalStateException("Cannot find View with id " + Resources.name(activity, viewId));
      }
      ViewAction checkedAction = checkedRegistry.get(viewId);
      ViewAction uncheckedAction = uncheckedRegistry.get(viewId);
      view.setOnCheckedChangeListener(new Performer(checkedAction, uncheckedAction));
    }
  }

  /**
   * Listens to the Item selection events and triggers the action to be performed.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  private class Performer implements CompoundButton.OnCheckedChangeListener {

    private final ViewAction checkedAction;
    private final ViewAction uncheckedAction;

    private Performer(ViewAction checkedAction, ViewAction uncheckedAction) {
      this.checkedAction = checkedAction;
      this.uncheckedAction = uncheckedAction;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
      ViewAction action;
      if (isChecked) {
        action = checkedAction;
      } else {
        action = uncheckedAction;
      }
      if (action != null) {
        action.perform(new ActionContext(compoundButton));
      }
    }
  }
}
