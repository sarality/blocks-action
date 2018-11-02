package com.sarality.action;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import com.sarality.util.log.Resources;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers Item Selection Actions on a View or children of a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ItemSelectedActions implements ActionInitializer {

  private Activity activity;
  private Map<Integer, ActionSelector> registry = new HashMap<>();

  public ItemSelectedActions(Activity activity) {
    this.activity = activity;
  }

  public ItemSelectedActions register(int viewId, ActionSelector actionSelector) {
    if (registry.containsKey(viewId)) {
      throw new IllegalStateException("Cannot register multiple actions for the same View with Id  " +
          Resources.name(activity, viewId));
    }
    registry.put(viewId, actionSelector);
    return this;
  }

  @Override
  public void init() {
    for (Integer viewId : registry.keySet()) {
      AdapterView view = activity.findViewById(viewId);
      if (view == null) {
        throw new IllegalStateException("Cannot find View with id " + Resources.name(activity, viewId));
      }
      ActionSelector actionSelector = registry.get(viewId);
      if (actionSelector != null) {
        view.setOnItemSelectedListener(new Performer(actionSelector));
      } else {
        view.setOnItemSelectedListener(null);
      }
    }
  }

  @Override
  public void init(View parentView) {
    for (Integer viewId : registry.keySet()) {
      AdapterView view = parentView.findViewById(viewId);
      ActionSelector actionSelector = registry.get(viewId);
      if (actionSelector != null) {
        view.setOnItemSelectedListener(new Performer(actionSelector));
      } else {
        view.setOnItemSelectedListener(null);
      }
    }
  }

  /**
   * Listens to the Item selection events and triggers the action to be performed.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  private class Performer implements AdapterView.OnItemSelectedListener {

    private final ActionSelector actionSelector;

    private Performer(ActionSelector actionSelector) {
      this.actionSelector = actionSelector;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
      String value = adapterView.getItemAtPosition(pos).toString();
      actionSelector.setSelectedValue(value);
      actionSelector.perform(new ActionContext(adapterView));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
      actionSelector.setSelectedValue(null);
      actionSelector.perform(new ActionContext(adapterView));
    }
  }
}
