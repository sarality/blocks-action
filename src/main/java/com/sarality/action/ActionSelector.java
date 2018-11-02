package com.sarality.action;

import java.util.HashMap;
import java.util.Map;

/**
 * A collection of Actions that are called based on selected value.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ActionSelector implements ViewAction {

  private final ViewAction defaultAction;
  private final Map<String, ViewAction> registry = new HashMap<>();
  private String selectValue = null;

  public ActionSelector(ViewAction defaultAction) {
    this.defaultAction = defaultAction;
  }

  public ActionSelector mapAction(String value, ViewAction action) {
    registry.put(value, action);
    return this;
  }

  void setSelectedValue(String value) {
    this.selectValue = value;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    ViewAction action = registry.get(selectValue);
    if (action != null) {
      action.perform(actionContext);
    } else if (selectValue != null && defaultAction != null) {
      defaultAction.perform(actionContext);
    }
    return false;
  }
}
