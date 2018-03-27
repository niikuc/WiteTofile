package com.niikuc.witetofile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et_name,et_content,et_description;
    Button b_save,b_read;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }

        et_name=(EditText) findViewById(R.id.et_name);
        et_content=(EditText) findViewById(R.id.et_content);
        et_description=(EditText) findViewById(R.id.et_descpription);
        b_save=(Button) findViewById(R.id.b_save);
        b_read=(Button) findViewById(R.id.b_read);


        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileName=et_name.getText().toString().concat(".txt");

                String content=et_content.getText().toString();
                String description=et_description.getText().toString();

                String word_des=content +"\t"+description;
                if (!fileName.equals("") && !word_des.equals("")) {
                    saveTextAsFile(word_des);
                }
            }
        });}



    public void otvoriAktiviti(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("STRING_I_NEED", fileName);
        startActivity(intent);
    }


    private void saveTextAsFile(String content){


        //create file
        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName);

        //write to file

        try {
            FileOutputStream fos= new FileOutputStream((file),true);
           OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(content.toString()+"\n");
            osw.flush();
            osw.close();
            fos.close();
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"File not Found",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Not Saved",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1000:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permision granted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"Permision not granted",Toast.LENGTH_SHORT).show();
                finish();
                }
        }
    }
}
