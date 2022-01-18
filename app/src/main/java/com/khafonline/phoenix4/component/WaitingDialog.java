
package com.khafonline.phoenix4.component;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.khafonline.phoenix4.R;

/**
 * Created by Hossein on 1/6/2017.
 */

public class WaitingDialog {

    AlertDialog alertDialog;

    public WaitingDialog(Context context, String title, String body) {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.dialog_waiting, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(viewGroup);
        alertDialog = builder.create();

    }
    public void dismiss()
    {
        alertDialog.dismiss();
    }

    public void finish()
    {
        alertDialog.dismiss();
    }



}
