package com.example.lab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LabDetailsActivity extends AppCompatActivity {
    private String[][] labtechnician_details1 =
            {
                    {"Labtechnician Name : Arvind Sharma","Lab Address : Airoli sector-3","Exp : 4yrs","Mobile No:9898989898","600"},
                    {"Labtechnician Name : Deepak Deshpande","Lab Address : Thane(Kolshet)","Exp : 15yrs","Mobile No:7357297209","900"},
                    {"Labtechnician Name : Shreyash Nikam","Lab Address : Borivali","Exp : 12yrs","Mobile No:7756830223","700"},
                    {"Labtechnician Name : Ashok Bugade","Lab Address : Santacruz","Exp : 8yrs","Mobile No:9372897628","500"},
                    {"Labtechnician Name : Pravin Pandey","Lab Address : Panvel","Exp : 10yrs","Mobile No:9683335509","1000"}
            };

    private String[][] labtechnician_details2 =

            {
                    {"Labtechnician Name : Monish Jain","Lab Address : Bhandhup","Exp : 4yrs","Mobile No:9898989898","600"},
                    {"Labtechnician Name : Deepali Patil","Lab Address : VileParle","Exp : 15yrs","Mobile No:7357297209","900"},
                    {"Labtechnician Name : Mayuri Parab","Lab Address : Mahim","Exp : 12yrs","Mobile No:7756830223","700"},
                    {"Labtechnician Name : Amol Gadhave","Lab Address : Kalyan","Exp : 8yrs","Mobile No:9372897628","500"},
                    {"Labtechnician Name : Gagana Reddy ","Lab Address : Mulund","Exp : 10yrs","Mobile No:9683335509","1000"}

            };

    private String[][] labtechnician_details3 =
            {
                    {"Labtechnician Name : Vishal Deshmukh","Lab Address : Dombivali","Exp : 4yrs","Mobile No:9898989898","600"},
                    {"Labtechnician Name : Deepak Kalal","Lab Address : Kalwa","Exp : 15yrs","Mobile No:7357297209","900"},
                    {"Labtechnician Name : Govind Thorat","Lab Address : NarimanPoint","Exp : 12yrs","Mobile No:7756830223","700"},
                    {"Labtechnician Name : Prasad Pawar","Lab Address : Kopar","Exp : 8yrs","Mobile No:9372897628","500"},
                    {"Labtechnician Name : Ranveer Pal ","Lab Address : Bandra","Exp : 10yrs","Mobile No:9683335509","1000"}

            };
    private String[][] labtechnician_details4 =

            {
                    {"Labtechnician Name : Varun Ghorpade","Lab Address : Vashi","Exp : 4yrs","Mobile No:9898989898","600"},
                    {"Labtechnician Name : Manoj Shinde","Lab Address : Kalamboli","Exp : 15yrs","Mobile No:7357297209","900"},
                    {"Labtechnician Name : Samarth Lokande","Lab Address : Mumbra","Exp : 12yrs","Mobile No:7756830223","700"},
                    {"Labtechnician Name : Revan Sharma","Lab Address : Parel","Exp : 8yrs","Mobile No:9372897628","500"},
                    {"Labtechnician Name : Utkarsh kale","Lab Address : Vikhroli","Exp : 10yrs","Mobile No:9683335509","1000"}

            };

    private String[][] labtechnician_details5 =

            {
                    {"Labtechnician Name : Bhargav Rao","Lab Address : Ambernath","Exp : 4yrs","Mobile No:9898989898","600"},
                    {"Labtechnician Name : Sunil Lohar","Lab Address : Goregaon","Exp : 15yrs","Mobile No:7357297209","900"},
                    {"Labtechnician Name : Arbaaz Shaikh","Lab Address : Khoparkhairane","Exp : 12yrs","Mobile No:7756830223","700"},
                    {"Labtechnician Name : Harsh Singh","Lab Address : Ghatkopar","Exp : 8yrs","Mobile No:9372897628","500"},
                    {"Labtechnician Name : Rajesh Patel","Lab Address : Vidyavihar","Exp : 10yrs","Mobile No:9683335509","1000"}

            };

    TextView tv;
    Button btn;
    String[][] labtechnician_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_details);

        tv = findViewById(R.id.textViewCartTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("ThyrocareTechnologies")==0)
            labtechnician_details = labtechnician_details1;
        else
        if(title.compareTo("ApolloDiagnostics")==0)
            labtechnician_details = labtechnician_details2;
        else
        if(title.compareTo("MetropolisHealthcare")==0)
            labtechnician_details = labtechnician_details3;
        else
        if(title.compareTo("DrLalPathLabs")==0)
            labtechnician_details = labtechnician_details4;
        else
        if(title.compareTo("SRLDiagnostics")==0)
            labtechnician_details = labtechnician_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabDetailsActivity.this, AppointmentActivity.class));

            }
        });

        list = new ArrayList();
        for(int i=0;i<labtechnician_details.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", labtechnician_details[i][0]);
            item.put("line2", labtechnician_details[i][1]);
            item.put("line3", labtechnician_details[i][2]);
            item.put("line4", labtechnician_details[i][3]);
            item.put("line5", "Cons Fees:" + labtechnician_details[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",labtechnician_details[i][0]);
                it.putExtra("text3",labtechnician_details[i][1]);
                it.putExtra("text4",labtechnician_details[i][3]);
                it.putExtra("text5",labtechnician_details[i][4]);
                startActivity(it);

            }
        });









    }


}
