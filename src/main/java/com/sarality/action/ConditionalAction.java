package com.sarality.action;

/**
 * Action that is performed only when the condition is true.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ConditionalAction implements ViewAction {
  private final ViewAction action;
  private final ActionCondition condition;

  public ConditionalAction(ViewAction action, ActionCondition condition) {
    this.action = action;
    this.condition = condition;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    if (condition != null && condition.isTrue(actionContext.getView())) {
      return action.perform(actionContext);
    }
    return true;
  }
}
