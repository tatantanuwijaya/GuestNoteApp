package com.example.weddingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editid,editnama, edituang, editberas, editopak, editpisang, editranginang, editwajit, editkueali, editlain,editalamat;
    Spinner editstatus;
    ImageButton buttoncari, buttoncarinama;
    Button buttonUpdate, buttondelete;
    String id,nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDB=new DatabaseHelper(this);
        editid=findViewById(R.id.edtidupd);
        editnama=findViewById(R.id.edtnamaupd);
        edituang=findViewById(R.id.edtuangupd);
        editberas=findViewById(R.id.edtberasudp);
        editopak=findViewById(R.id.edtOpakudp);
        editpisang=findViewById(R.id.edtPisangudp);
        editranginang=findViewById(R.id.edtranginangudp);
        editwajit=findViewById(R.id.edtwajitudp);
        editkueali=findViewById(R.id.edtledramudp);
        editstatus=findViewById(R.id.edtstatusudp);
        editlain=findViewById(R.id.edtlainudp);
        editalamat=findViewById(R.id.edtalamatudp);
        buttonUpdate=findViewById(R.id.btnupdate);
        buttondelete=findViewById(R.id.btdelete);
        buttoncari=findViewById(R.id.btncari);
        buttoncarinama=findViewById(R.id.btncarinama);

        UpdateData();
        DeleteData();
        searchData();
        searchDataNama();
    }

    private void searchData() {
        buttoncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=editid.getText().toString();
                if (id.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Silahkan masukkan id terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        Cursor cursor=myDB.searchData(id);
                        if (cursor.moveToFirst()){
                            editnama.setText(cursor.getString(1));
                            edituang.setText(cursor.getString(2));
                            editberas.setText(cursor.getString(3));
                            editopak.setText(cursor.getString(4));
                            editpisang.setText(cursor.getString(5));
                            editranginang.setText(cursor.getString(6));
                            editwajit.setText(cursor.getString(7));
                            editkueali.setText(cursor.getString(8));
                            editstatus.setSelected(Boolean.parseBoolean((cursor.getString(9))));
                            editlain.setText(cursor.getString(10));
                            editalamat.setText(cursor.getString(11));
                            Toast.makeText(UpdateActivity.this, "Pencarian berhasil", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UpdateActivity.this,"pencarian gagal \n data tidak ditemukan",Toast.LENGTH_SHORT).show();
                            editid.setText("");
                            editnama.setText("");
                            edituang.setText("");
                            editberas.setText("");
                            editopak.setText("");
                            editpisang.setText("");
                            editranginang.setText("");
                            editwajit.setText("");
                            editkueali.setText("");
                            editstatus.setSelected(Boolean.parseBoolean("Status"));
                            editlain.setText("");
                            editalamat.setText("");
                        }
                        cursor.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void searchDataNama() {
        buttoncarinama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama=editnama.getText().toString();
                if (nama.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Silahkan masukkan nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        Cursor cursor=myDB.searchDataNama(nama);
                        if (cursor.moveToFirst()){
                            editid.setText(cursor.getString(0));
                            edituang.setText(cursor.getString(2));
                            editberas.setText(cursor.getString(3));
                            editopak.setText(cursor.getString(4));
                            editpisang.setText(cursor.getString(5));
                            editranginang.setText(cursor.getString(6));
                            editwajit.setText(cursor.getString(7));
                            editkueali.setText(cursor.getString(8));
                            editstatus.setSelected(Boolean.parseBoolean((cursor.getString(9))));
                            editlain.setText(cursor.getString(10));
                            editalamat.setText(cursor.getString(11));
                            Toast.makeText(UpdateActivity.this, "Pencarian berhasil", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UpdateActivity.this,"pencarian gagal \n nama tidak ditemukan atau tidak ditemukan",Toast.LENGTH_SHORT).show();
                            editid.setText("");
                            editnama.setText("");
                            edituang.setText("");
                            editberas.setText("");
                            editopak.setText("");
                            editpisang.setText("");
                            editranginang.setText("");
                            editwajit.setText("");
                            editkueali.setText("");
                            editstatus.setSelected(Boolean.parseBoolean("Status"));
                            editlain.setText("");
                            editalamat.setText("");
                        }
                        cursor.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void UpdateData() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editid.getText().toString();
                String namaudp =editnama.getText().toString();
                String uangudp=edituang.getText().toString();
                String alamatudp=editalamat.getText().toString();
                if (TextUtils.isEmpty(id)){
                    Toast.makeText(UpdateActivity.this, "SILAHKAN MASUKAN ID YANG DI TUJU", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(namaudp)){
                    Toast.makeText(UpdateActivity.this, "Silahkan isi bidang nama",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uangudp)){
                    Toast.makeText(UpdateActivity.this, "Silahkan isi bidang uang",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(alamatudp)){
                    Toast.makeText(UpdateActivity.this, "Silahkan isi bidang alamat",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isUpdate=myDB.updateData(editid.getText().toString(),editnama.getText().toString(),edituang.getText().toString(), editberas.getText().toString(), editopak.getText().toString(),editpisang.getText().toString(),
                        editranginang.getText().toString(),editwajit.getText().toString(),editkueali.getText().toString(), String.valueOf(editstatus.getSelectedItem()),
                        editlain.getText().toString(), editalamat.getText().toString());
                if (isUpdate==true){
                    Toast.makeText(UpdateActivity.this, "DATA BERHASIL DI UPDATE", Toast.LENGTH_SHORT).show();
                    editnama.setText("");
                    edituang.setText("");
                    editberas.setText("");
                    editopak.setText("");
                    editpisang.setText("");
                    editranginang.setText("");
                    editwajit.setText("");
                    editkueali.setText("");
                    editstatus.setSelected(Boolean.parseBoolean("Status"));
                    editlain.setText("");
                    editalamat.setText("");
                    editid.setText("");
                }else {
                    Toast.makeText(UpdateActivity.this, "DATA TIDAK DAPAT DI UPDATE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void DeleteData(){
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editid.getText().toString();
                if (TextUtils.isEmpty(id)){
                    Toast.makeText(UpdateActivity.this, "SILAHKAN MASUKAN ID YANG DI TUJU", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer deleteRows=myDB.deleteData(editid.getText().toString());
                if (deleteRows>0){
                    Toast.makeText(UpdateActivity.this,"data telah dihapus", Toast.LENGTH_SHORT).show();
                    editid.setText("");
                }else{
                    Toast.makeText(UpdateActivity.this,"data gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}