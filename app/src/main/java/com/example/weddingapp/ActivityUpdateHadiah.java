package com.example.weddingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActivityUpdateHadiah extends AppCompatActivity {

    DatabaseHelper2 myDB2;
    EditText editid, editnamahadiah, editalamathadiah;
    Button buttonUpdate, buttondelete;
    ImageButton buttoncari;
    private String ID;
    private String NAMA;
    private String ALAMAT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_hadiah);

        myDB2=new DatabaseHelper2(this);
        editid=findViewById(R.id.edtidkado);
        editnamahadiah=findViewById(R.id.edtupdatenamakado);
        editalamathadiah=findViewById(R.id.edtupdatealamatkado);
        buttonUpdate=findViewById(R.id.btnupdatehadiah);
        buttondelete=findViewById(R.id.btndeletehadiah);
        buttoncari=findViewById(R.id.btncarihadiah);
        UpdateDataHadiah();
        DeleteDataHadiah();
        SearchDataHadiah();
    }

    private void SearchDataHadiah() {
        buttoncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAMA=editnamahadiah.getText().toString();
//                ID=editid.getText().toString();
                if (NAMA.isEmpty()){
                    Toast.makeText(ActivityUpdateHadiah.this, "silahkan masukan id terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Cursor cursor=myDB2.searchData(NAMA);
                        if (cursor.moveToFirst()){
                            editid.setText(cursor.getString(0));
                            editalamathadiah.setText(cursor.getString(2));
                            Toast.makeText(ActivityUpdateHadiah.this, "pencarian data berhasil", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ActivityUpdateHadiah.this, "nama tidak tersedia atau penulisan salah", Toast.LENGTH_SHORT).show();
                            editid.setText("");
                            editalamathadiah.setText("");
                        }
                        cursor.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void UpdateDataHadiah() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editid.getText().toString();
                String namaudp =editnamahadiah.getText().toString();
                String alamatudp=editalamathadiah.getText().toString();
                if (TextUtils.isEmpty(id)){
                    Toast.makeText(ActivityUpdateHadiah.this, "SILAHKAN MASUKAN ID YANG DI TUJU", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(namaudp)){
                    Toast.makeText(ActivityUpdateHadiah.this, "Silahkan isi bidang nama",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(alamatudp)){
                    Toast.makeText(ActivityUpdateHadiah.this, "Silahkan isi bidang alamat",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isUpdate=myDB2.updateData(editid.getText().toString(),editnamahadiah.getText().toString(), editalamathadiah.getText().toString());
                if (isUpdate==true){
                    Toast.makeText(ActivityUpdateHadiah.this, "DATA BERHASIL DI UPDATE", Toast.LENGTH_SHORT).show();
                    editnamahadiah.setText("");
                    editalamathadiah.setText("");
                    editid.setText("");
                }else {
                    Toast.makeText(ActivityUpdateHadiah.this, "DATA TIDAK DAPAT DI UPDATE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void DeleteDataHadiah(){
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editid.getText().toString();
                if (TextUtils.isEmpty(id)){
                    Toast.makeText(ActivityUpdateHadiah.this, "SILAHKAN MASUKAN ID YANG DI TUJU", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer deleteRows=myDB2.deleteData(editid.getText().toString());
                if (deleteRows>0){
                    Toast.makeText(ActivityUpdateHadiah.this,"data telah dihapus", Toast.LENGTH_SHORT).show();
                    editid.setText("");
                }else{
                    Toast.makeText(ActivityUpdateHadiah.this,"data gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}