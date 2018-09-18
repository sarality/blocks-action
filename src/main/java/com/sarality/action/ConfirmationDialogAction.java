package com.sarality.action;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Displays A Conformation Dialog and performs the actions if user selects yes.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ConfirmationDialogAction extends ActionChain {

  private final int titleResId;
  private final int messageResId;
  private final int iconDrawableId;
  private final int yesButtonLabelResId;
  private final int noButtonLabelResId;

  public ConfirmationDialogAction(int titleResId, int messageResId, int iconDrawableId,
      int yesButtonLabelResId, int noButtonLabelResId,
      ViewAction... actions) {
    super(actions);
    this.titleResId = titleResId;
    this.messageResId = messageResId;
    this.iconDrawableId = iconDrawableId;
    this.yesButtonLabelResId = yesButtonLabelResId;
    this.noButtonLabelResId = noButtonLabelResId;
  }

  @Override
  public boolean perform(final ActionContext actionContext) {
    final Context context = actionContext.getView().getContext();
    new AlertDialog.Builder(context)
        .setTitle(titleResId)
        .setMessage(messageResId)
        .setIcon(iconDrawableId)
        .setPositiveButton(yesButtonLabelResId, new PositiveActionPerformer(actionContext))
        .setNegativeButton(noButtonLabelResId, null)
        .show();
    return true;
  }

  private void performAction(final ActionContext actionContext) {
    super.perform(actionContext);
  }

  private class PositiveActionPerformer implements DialogInterface.OnClickListener {
    private ActionContext actionContext;

    private PositiveActionPerformer(ActionContext actionContext) {
      this.actionContext = actionContext;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      performAction(actionContext);
    }
  }
}
