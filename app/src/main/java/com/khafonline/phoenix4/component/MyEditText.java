
package com.khafonline.phoenix4.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.core.Constants;
/**
 * Created by Hossein on 1/6/2017.
 */


public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {

    //region Constructor

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(attrs);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(attrs);
        }

    }

    public MyEditText(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(null);
        }
    }

    //endregion



    private void init(AttributeSet attrs) {

        setTypeface(Typeface.createFromAsset(getContext().getAssets(), Constants.DEFAULT_FONT_FOLDER + Constants.fonts[Constants.DEFAULT_FONT]));

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyEditText);

            if (a.getString(R.styleable.MyEditText_edit_font) != null)
            {
                String fontName = Constants.fonts[Integer.valueOf(a.getString(R.styleable.MyEditText_edit_font))];
                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Constants.DEFAULT_FONT_FOLDER + fontName);
                    setTypeface(myTypeface);
                }
                a.recycle();
            }

        }

    }

}