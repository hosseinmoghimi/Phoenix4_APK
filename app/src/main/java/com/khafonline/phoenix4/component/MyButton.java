package com.khafonline.phoenix4.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.core.Constants;


/**
 * Created by Hossein on 16/03/2019.
 */

public class MyButton extends androidx.appcompat.widget.AppCompatButton
{
    //region constructor
    public MyButton(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(null);
        }
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs); }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs); }
    //endregion


    private void init(AttributeSet attrs) {

        setTypeface(Typeface.createFromAsset(getContext().getAssets(), Constants.DEFAULT_FONT_FOLDER + Constants.fonts[Constants.DEFAULT_FONT]));

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyButton);

            if (a.getString(R.styleable.MyButton_button_font) != null)
            {
                String fontName = Constants.fonts[Integer.valueOf(a.getString(R.styleable.MyButton_button_font))];
                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Constants.DEFAULT_FONT_FOLDER + fontName);
                    setTypeface(myTypeface);
                }
                a.recycle();
            }

        }

    }
}
