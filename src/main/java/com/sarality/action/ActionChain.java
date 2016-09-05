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

  public ActionChain(ViewAction... actions) {
    if (actions == null || actions.length < 1) {
      throw new IllegalArgumentException("Must specify at least the initial action for an action chain");
    }
    this.actionList.addAll(Arrays.asList(actions));
  }
  
  @Override
  public boolean perform() {
    boolean success = true;
    for (ViewAction action : actionList) {
      // TODO(abhideep): Add support for onFailure based chaining.
      success = action.perform();
      if (!success) {
        break;
      }
    }

    return success;
  }
}
