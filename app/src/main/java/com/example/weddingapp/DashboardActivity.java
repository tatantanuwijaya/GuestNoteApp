package com.example.weddingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.material.badge.BadgeUtils;

public class DashboardActivity extends AppCompatActivity {
    LinearLayout llkondangan,llsourcecode,llhadiah, llsyukuran;
    TextView marqueetext;
    ImageView imgaboutme, imgaboutapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        llkondangan=findViewById(R.id.tamukondangan);
        marqueetext=findViewById(R.id.marquee);
        marqueetext.setSelected(true);
        imgaboutme=findViewById(R.id.imageaboutme);
        imgaboutapp=findViewById(R.id.imageaboutapp);
        llsourcecode=findViewById(R.id.sourcecode);
        llhadiah=findViewById(R.id.datahadiah);
//        llsyukuran=findViewById(R.id.llsyukuran);

//        llsyukuran.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(), SyukuranActivity.class);
//                startActivity(intent);
//            }
//        });
        llkondangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        llsourcecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
        });
        llhadiah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HadiahActivity.class);
                startActivity(intent);
            }
        });
        imgaboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertme();
            }
        });
        imgaboutapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertapp();
            }
        });
    }

    private void alertme() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Tentang saya");
        builder.setMessage("Assalamualaikum, perkenalkan nama saya Tatan Tanuwijaya. " +
                "Saya berasal dari jurusan Teknik Informatika dengan NIM 19552011132 dari kelas TIF RP 18A. " +
                "Saat ini saya sedang menempuh semester 5 di Sekolah Tinggi Teknologi Bandung. ");
        builder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
            }
        });
        builder.setIcon(getDrawable(R.drawable.gna));
        builder.create().show();
    }

    private void alertapp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Tentang Aplikasi");
        builder.setMessage("Aplikasi ini dibuat untuk kebutuhan pesta hajatan yang sering diadakan di kabupaten subang. " +
                "Dalam pembuatannya aplikasi ini dapat digunakan sebagai aplikasi pencatatan data tamu undangan yang sering diadakan di desa. " +
                "Pendataan tamu undangan dalam aplikasi ini meliputi acara hajatan, khitanan, tasyakuran untuk 4 bulanan maupun 7 bulanan. " +
                "Selain itu, aplikasi ini dapat digunakan sebagai aplikasi acara pesta ulang tahun juga.");
        builder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeContextMenu();
            }
        });
        builder.setIcon(getDrawable(R.drawable.gna));
        builder.create().show();
    }

}