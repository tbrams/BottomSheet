package android.brams.dk.bottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior<View> mBottomSheetBehavior;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        textView = (TextView) findViewById(R.id.textView);
        View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);

        //For your bottom sheet to be displayable, you need to create a BottomSheetBehavior.
        //This is created by getting a reference to the container view and calling BottomSheetBehavior.from() on that container.
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        mBottomSheetBehavior.setPeekHeight(0);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                /**
                 * For persistent bottom sheet to work, your layout should contain a coordinator layout,
                 * and then in any child view of your coordinator layout, you can make it as a persistent bottom sheet
                 * by adding a custom property app:layout_behavior and use behavior_peekHeight to define how much
                 * of the Bottom Sheet you want visible.
                 */
                textView.setText(R.string.dynamic_persistent_txt);
                mBottomSheetBehavior.setPeekHeight(300);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.button2:
                /**
                 * You can also display a Dialog in place of a View in the bottom sheet.
                 * To do this, get the view from getLayoutInflater and pass it setContentView of the Dialog.
                 */
                View view = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
                TextView textView = (TextView) view.findViewById(R.id.textView);
                textView.setText(R.string.dialog_modal_txt);
                BottomSheetDialog dialog = new BottomSheetDialog(this);
                dialog.setContentView(view);
                dialog.show();
                break;
            case R.id.button3:
                /**
                 * You can also display a Fragment in place of a View in the bottom sheet.
                 * To do this, you class that extends BottomSheetDialogFragment.
                 */
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogFragmentExample();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                break;
        }
    }
}
