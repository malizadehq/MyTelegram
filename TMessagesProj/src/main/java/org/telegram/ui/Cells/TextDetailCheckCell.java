package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.GroundOverlayOptions;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.StickersActivity.TouchHelperCallback;

public class TextDetailCheckCell extends FrameLayout {
    private static Paint paint;
    private Switch checkBox;
    private boolean multiline;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    public TextDetailCheckCell(Context context) {
        super(context);
        int i = 3;
        if (paint == null) {
            paint = new Paint();
            paint.setColor(0xffd9d9d9);
            paint.setStrokeWidth(TouchHelperCallback.ALPHA_FULL);
        }
        this.textView = new TextView(context);
        textView.setTextColor(0xff212121);
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setGravity((LocaleController.isRTL ? 5 : 3) | 16);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        addView(this.textView, LayoutHelper.createFrame(-1, GroundOverlayOptions.NO_DIMENSION, (LocaleController.isRTL ? 5 : 3) | 48, 17.0f, 5.0f, 17.0f, 0.0f));
        this.valueTextView = new TextView(context);
        valueTextView.setTextColor(0xff8a8a8a);
        this.valueTextView.setTextSize(1, 13.0f);
        this.valueTextView.setGravity(LocaleController.isRTL ? 5 : 3);
        this.valueTextView.setPadding(0, 0, 0, 0);
        this.valueTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        setMultilineDetail(true);
        addView(this.valueTextView, LayoutHelper.createFrame(-2, -2.0f, (LocaleController.isRTL ? 5 : 3) | 48, 17.0f, 40.0f, 17.0f, 0.0f));
        this.checkBox = new Switch(context);
        this.checkBox.setDuplicateParentStateEnabled(false);
        this.checkBox.setFocusable(false);
        this.checkBox.setFocusableInTouchMode(false);
        this.checkBox.setClickable(false);
        View view = this.checkBox;
        if (!LocaleController.isRTL) {
            i = 5;
        }
        addView(view, LayoutHelper.createFrame(-2, -2.0f, i | 48, 14.0f, 5.0f, 14.0f, 0.0f));
    }

    protected void onDraw(Canvas canvas) {
        if (this.needDivider) {
            canvas.drawLine((float) getPaddingLeft(), (float) (getHeight() - 1), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - 1), paint);
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        if (this.multiline) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY));
            return;
        }
        int dp = AndroidUtilities.dp(48.0f);
        if (this.needDivider) {
            i3 = 1;
        }
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(i3 + dp, MeasureSpec.EXACTLY));
    }

    public void setChecked(boolean z) {
        this.checkBox.setChecked(z);
    }

    public void setMultilineDetail(boolean z) {
        this.multiline = z;
        if (z) {
            this.valueTextView.setLines(0);
            this.valueTextView.setMaxLines(0);
            this.valueTextView.setSingleLine(false);
            this.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(12.0f));
            return;
        }
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
    }

    public void setTextAndCheck(String str, String str2, boolean z, boolean z2) {
        this.textView.setText(str);
        this.valueTextView.setText(str2);
        if (VERSION.SDK_INT < 11) {
            this.checkBox.resetLayout();
            this.checkBox.requestLayout();
        }
        this.checkBox.setChecked(z);
        this.needDivider = z2;
        setWillNotDraw(!z2);
    }
}
