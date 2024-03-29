package com.example.todolite.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Pasin Suriyentrakorn <pasin@couchbase.com> on 4/13/14.
 */
public class RoundedImageView extends ImageView {
    private static final float RADIUS = 90;

    public RoundedImageView(Context context) {
        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            RectF rectF = new RectF(drawable.getBounds());
            int restoreCount = canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG);
            getImageMatrix().mapRect(rectF);

            Paint paint = ((BitmapDrawable) drawable).getPaint();
            paint.setAntiAlias(true);
            paint.setColor(0xff000000);

            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawRoundRect(rectF, RADIUS, RADIUS, paint);

            Xfermode restoreMode = paint.getXfermode();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            super.onDraw(canvas);

            // Restore paint and canvas
            paint.setXfermode(restoreMode);
            canvas.restoreToCount(restoreCount);
        } else {
            super.onDraw(canvas);
        }
    }
}
