package com.example.weddingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editnama, edituang, editberas, editopak, editpisang, editranginang, editwajit, editkueali, editlain,editalamat;
    Spinner editstatus;
    Button buttonsimpan, buttonlihat, buttonUpdate;
    ImageView ivback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB=new DatabaseHelper(this);
        editnama=findViewById(R.id.edtnama);
        edituang=findViewById(R.id.edtuang);
        editberas=findViewById(R.id.edtberas);
        editopak=findViewById(R.id.edtOpak);
        editpisang=findViewById(R.id.edtPisang);
        editranginang=findViewById(R.id.edtranginang);
        editwajit=findViewById(R.id.edtwajit);
        editkueali=findViewById(R.id.edtledram);
        editstatus=findViewById(R.id.edtstatus);
        editlain=findViewById(R.id.edtlain);
        editalamat=findViewById(R.id.edtalamat);
        buttonsimpan=findViewById(R.id.btnsimpan);
        buttonlihat=findViewById(R.id.btnlihat);
        buttonUpdate=findViewById(R.id.btnupdate);
        ivback=findViewById(R.id.back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UpdateActivity.class);
                startActivity(intent);
            }
        });
        AddData();
        viewAll();
    }

    private void AddData() {
        buttonsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama =editnama.getText().toString();
                String uang=edituang.getText().toString();
                String beras=editberas.getText().toString();
                String opak =editopak.getText().toString() ;
                String pisang =editpisang.getText().toString();
                String ranginang =editranginang.getText().toString();
                String wajit =editwajit.getText().toString();
                String kueali =editkueali.getText().toString();
                String status=String.valueOf(editstatus.getSelectedItem());
                String lain = editlain.getText().toString();
                String alamat=editalamat.getText().toString();
                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(MainActivity.this, "Silahkan isi \n setidaknya untuk nama, uang, status dan alamat tamu",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(uang)){
                    Toast.makeText(MainActivity.this, "Jumlah uang harus diisi",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(alamat)){
                    Toast.makeText(MainActivity.this, "Alamat tamu harus diisi",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isInserted=myDB.insertData(nama,uang,beras,opak,pisang,ranginang,wajit,kueali,status,lain,alamat);
                if (isInserted==true){
                    Toast.makeText(MainActivity.this, "data berhasil disimpan", Toast.LENGTH_SHORT).show();
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
                }else{
                    Toast.makeText(MainActivity.this, "data gagal disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
            }
        });
        builder.setMessage(message);
        builder.show();
    }
    private void viewAll() {
        buttonlihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= myDB.getAllData();
                if (res.getCount()==0){
                    showMessage("Pesan","Data masih kosong");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append(res.getString(0)+". Nama         \t: "+res.getString(1)+"\n");
                    buffer.append("    Uang           \t: Rp"+res.getString(2)+"\n");
                    buffer.append("    Beras          \t: "+res.getString(3)+" Liter \n");
                    buffer.append("    Opak           \t: "+res.getString(4)+"\n");
                    buffer.append("    Pisang        \t: "+res.getString(5)+" Sikat \n");
                    buffer.append("    Ranginang\t: "+res.getString(6)+"\n");
                    buffer.append("    Wajit           \t: "+res.getString(7)+"\n");
                    buffer.append("    Kue ali        \t: "+res.getString(8)+"\n");
                    buffer.append("    Status         \t: "+res.getString(9)+"\n");
                    buffer.append("    Lainnya       \t: "+res.getString(10)+"\n");
                    buffer.append("    Alamat        \t: "+res.getString(11)+"\n");
                    buffer.append("  _________________________________\t"+"\n");
                }
                showMessage("DAFTAR TAMU UNDANGAN"+"\n"+"___________________________________",buffer.toString());
            }
        });
    }

}