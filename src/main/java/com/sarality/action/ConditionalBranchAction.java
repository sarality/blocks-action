package com.sarality.action;

/**
 * Allows branching of actions based on whether condition is true
 *
 * @author satya@ (Satya Puniani)
 */
public class ConditionalBranchAction implements ViewAction {
  private final ViewAction actionIfTrue;
  private final ViewAction actionIfFalse;
  private final ActionCondition condition;

  public ConditionalBranchAction(ActionCondition condition, ViewAction actionIfTrue, ViewAction actionIfFalse) {
    this.actionIfFalse = actionIfFalse;
    this.actionIfTrue = actionIfTrue;
    this.condition = condition;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    if (condition != null) {
      if (condition.isTrue(actionContext.getView())) {
        return actionIfTrue.perform(actionContext);
      } else {
        return actionIfFalse.perform(actionContext);
      }
    }
    return true;
  }
}
