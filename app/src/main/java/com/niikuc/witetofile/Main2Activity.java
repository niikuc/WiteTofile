package com.niikuc.witetofile;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2Activity extends AppCompatActivity {

    EditText et_word;
    TextView tv_opis;
    Button b_show,b_show_dialog;
    String newString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        b_show=(Button) findViewById(R.id.b_show);

    b_show.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            et_word=(EditText) findViewById(R.id.et_word);
            String word=et_word.getText().toString();
            String defn=readDefinition(word);
            tv_opis=(TextView) findViewById(R.id.tv_opis);
            tv_opis.setText(defn);

        }
    });

    b_show_dialog=(Button) findViewById(R.id.b_show_dialog);

    b_show_dialog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager fm = getSupportFragmentManager();
            BlankFragment fragment= new BlankFragment();
            fragment.show(fm,"fragment_blank");

            Log.i("TAG","Test");

        }
    });

    }



    private String readDefinition(String word) {
        String a = "";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),newString);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] pieces = line.split("\t");
                if (pieces[0].equalsIgnoreCase(word.trim())) {
                    a = pieces[1];
                }

            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }
}
