package android.brams.dk.bottomsheet;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BottomSheetDialogFragmentExample extends BottomSheetDialogFragment {
    public static final String TAG = "TBR:";

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                // Get rid of the fragment when the dialog is hidden
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    /**
     * Within the setupDialog() method, you can inflate a new layout file and retrieve the BottomSheetBehavior
     * of the container view in your Activity. Once you have the behavior, you can create and associate a
     * BottomSheetCallback with it to dismiss the Fragment when the sheet is hidden.
     */

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        final Dialog d=dialog;
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_layout_2, null);
        dialog.setContentView(contentView);

        Button btnUpd = (Button) contentView.findViewById(R.id.btnUPD);
        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                d.hide();
            }
        });

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }
}