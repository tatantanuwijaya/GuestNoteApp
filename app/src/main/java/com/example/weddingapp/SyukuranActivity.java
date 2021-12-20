package com.example.weddingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SyukuranActivity extends AppCompatActivity {
    EditText editnama, edituang, editberas,editlain,editalamat;
    DatabaseHelper3 databaseHelper3;
    Button simpan, lihat, update, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syukuran);

        databaseHelper3=new DatabaseHelper3(this);
        editnama=findViewById(R.id.edtnamasyukuran);
        editberas=findViewById(R.id.edtberassyukuran);
        editlain=findViewById(R.id.edtlainsyukuran);
        editalamat=findViewById(R.id.edtalamatsyukuran);
        simpan=findViewById(R.id.btnsimpansyukuran);
        AddData();
    }

    private void AddData() {
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namasukuran = editnama.getText().toString();
                String berassukuran = editberas.getText().toString();
                String lainsukuran = editlain.getText().toString();
                String alamatsukuran = editalamat.getText().toString();
                if (TextUtils.isEmpty(namasukuran)){
                    Toast.makeText(SyukuranActivity.this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isInserted = databaseHelper3.insertData(namasukuran,berassukuran,lainsukuran,alamatsukuran);
                if (isInserted==true){
                    Toast.makeText(SyukuranActivity.this, "BERHASIL", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SyukuranActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}