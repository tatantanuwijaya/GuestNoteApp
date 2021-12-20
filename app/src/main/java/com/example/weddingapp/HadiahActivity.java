package com.example.weddingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class HadiahActivity extends AppCompatActivity {
    DatabaseHelper2 databaseHelper2;
    EditText editnamahadiah, editalamathadiah;
    Button buttonsimpanhadiah, buttonlihathadiah, buttonUpdatehadiah, buttonback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadiah);

        databaseHelper2=new DatabaseHelper2(this);
        editnamahadiah=findViewById(R.id.edtnamahadiah);
        editalamathadiah=findViewById(R.id.edtalamathadiah);
        buttonsimpanhadiah=findViewById(R.id.btnsimpanhadiah);
        buttonlihathadiah=findViewById(R.id.btnlihathadiah);
        buttonUpdatehadiah=findViewById(R.id.btnupdatedatahadiah);
        buttonback=findViewById(R.id.btnBack);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buttonUpdatehadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ActivityUpdateHadiah.class);
                startActivity(intent);
            }
        });
        AddData();
        viewAll();
    }

    private void viewAll() {
        buttonlihathadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=databaseHelper2.getAllData();
                if (cursor.getCount()==0){
                    showMessage("Pesan", "Data hadiah masih kosong");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append(cursor.getString(0)+". Nama   :\t"+cursor.getString(1)+"\n");
                    buffer.append("    Alamat :\t"+cursor.getString(2)+"\n");
                    buffer.append("  _____________________________________\t"+"\n");
                }
                showMessage("DATA HADIAH TAMU"+"\n"+"________________________________", buffer.toString());
            }
        });
    }

    private void AddData() {
        buttonsimpanhadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama=editnamahadiah.getText().toString();
                String alamat=editalamathadiah.getText().toString();
                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(HadiahActivity.this, "silahkan masukan nama pemberi hadiah", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(alamat)){
                    Toast.makeText(HadiahActivity.this, "silahkan masukan alamat pemberi hadiah", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isInserted=databaseHelper2.insertData(nama, alamat);
                if (isInserted==true){
                    Toast.makeText(HadiahActivity.this, "data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    editnamahadiah.setText("");
                    editalamathadiah.setText("");
                }
                else
                    Toast.makeText(HadiahActivity.this, "data gagal disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
            }
        });
        builder.setMessage(Message);
        builder.show();
    }
}