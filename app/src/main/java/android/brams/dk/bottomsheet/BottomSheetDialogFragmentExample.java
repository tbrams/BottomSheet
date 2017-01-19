package android.brams.dk.bottomsheet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
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

     }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
                final FrameLayout bottomSheet = (FrameLayout) ((BottomSheetDialog) dialog).findViewById(android.support.design.R.id.design_bottom_sheet);
                final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                bottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetBehavior.setPeekHeight(550);
            }
        });


        return dialog;
    }
}