package com.sarality.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A chain of actions where one is triggered only if the first one executes successfully.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ActionChain implements ViewAction {

  private final List<ViewAction> actionList = new ArrayList<>();
  private final ActionChain failureActionChain;

  public ActionChain(ViewAction... actions) {
    this((actions == null ? new ArrayList<ViewAction>() : Arrays.asList(actions)));
  }

  //TODO(Satya) use builder pattern for failure actions
  public ActionChain(List<ViewAction> actions, ViewAction... failureActions) {
    if (actions == null || actions.size() < 1) {
      throw new IllegalArgumentException("Must specify at least the initial action for an action chain");
    }
    this.actionList.addAll(actions);

    if (failureActions != null && failureActions.length > 0) {
      this.failureActionChain = new ActionChain(failureActions);
    } else {
      this.failureActionChain = null;
    }

  }

  @Override
  public boolean perform(ActionContext actionContext) {
    boolean success = true;
    for (ViewAction action : actionList) {
      success = action.perform(actionContext);
      if (!success) {
        if (failureActionChain != null) {
          return failureActionChain.perform(actionContext);
        }
        break;
      }
    }

    return success;

  }
}
