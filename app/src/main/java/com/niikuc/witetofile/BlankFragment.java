package com.niikuc.witetofile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BlankFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Create view to show
        final View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_blank, null);


        //Create a button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText=(EditText) v.findViewById(R.id.et_word_dialog);
                EditText editText2=(EditText) v.findViewById(R.id.et_descpription_dialog);
                String x=editText.getText().toString()+"\t"+editText2.getText().toString();
                saveTextAsFile(x);

            }
        };

        DialogInterface.OnClickListener listener1 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();

            }
        };


        //Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Save to file")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel,listener1)
                .create();


}

    private void saveTextAsFile(String content) {


        //create file
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"recnik.txt");

        //write to file

        try {
            FileOutputStream fos = new FileOutputStream((file), true);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(content.toString() + "\n");
            osw.flush();
            osw.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();


        }

    }

    }

