
package com.khafonline.phoenix4.component;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khafonline.phoenix4.R;


/**
 * Created by Hossein on 12/23/2016.
 */

public class DialogYesNo {


    YesCommandCallBack yesCommand;
    NoCommandCallBack noCommand;
    AlertDialog alertDialog;

    public DialogYesNo(Context context, String title, String body) {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.dialog_yes_no, null);
        setNoCommand(new NoCommandCallBack() {
            @Override
            public void noCommand() {

            }
        });

        setYesCommand(new YesCommandCallBack() {
            @Override
            public void yesCommand() {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        ( viewGroup.findViewById(R.id.dialog_no_Button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noCommand.noCommand();
                dismiss();
            }
        });


        (viewGroup.findViewById(R.id.dialog_yes_Button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesCommand.yesCommand();
                dismiss();
            }
        });

        ((MyTextView)viewGroup.findViewById(R.id.dialog_yes_no_body)).setText(body);
        ((MyTextView)viewGroup.findViewById(R.id.dialog_yes_no_title)).setText(title);

        builder.setView(viewGroup);
        alertDialog = builder.create();

    }


    public DialogYesNo setNoCommand(NoCommandCallBack noCommand) {
        this.noCommand = noCommand;
        return this;
    }

    public DialogYesNo setYesCommand(YesCommandCallBack yesCommand) {
        this.yesCommand = yesCommand;
        return this;
    }

    public interface NoCommandCallBack {

        void noCommand();
    }


    public interface YesCommandCallBack {
        void yesCommand();
    }

    public DialogYesNo show() {
        alertDialog.show();
        return this;
    }

    public DialogYesNo finish() {
        alertDialog.dismiss();
        return this;
    }
    public DialogYesNo dismiss() {
        alertDialog.dismiss();
        return this;
    }
}