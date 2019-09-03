package com.example.unitume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private LinearLayout shopping,bus,laundry,food,parcel,appointments,ecitizen,bill,gas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dl = (DrawerLayout)findViewById(R.id.activity_dashboard);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home:
                        Intent intent = new Intent( DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent);break;
                    case R.id.notification:
                        Intent intent1 = new Intent( DashboardActivity.this, Notification.class);
                        startActivity(intent1);break;
                    case R.id.FAQs:
                        Intent intent2 = new Intent( DashboardActivity.this, FAQs.class);
                        startActivity(intent2);break;
                    case R.id.invite:
                        Intent intent4 = new Intent(Intent.ACTION_SEND);
                        intent4.putExtra(Intent.EXTRA_TEXT, "Paste your link here.");
                        intent4.setType("text/plain");
                        startActivity(Intent.createChooser(intent4,"Choose App To Share Link or Any Text" ));
                        break;
                    case R.id.share:
                        Intent Email = new Intent(Intent.ACTION_SEND);
                        Email.setType("text/email");
                        Email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"chalocynthia77@gmail.com"});
                        Email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback");
                        Email.putExtra(Intent.EXTRA_TEXT,  "");
                        startActivity(Intent.createChooser(Email, "Send mail..."));
                        break;
                    case R.id.logout:
                        Intent intent5 = new Intent(getApplicationContext() , MainActivity.class);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent5);break;
                    default:
                        return true;
                }


                return true;

            }
        });

        shopping=findViewById(R.id.shopping);
        bus=findViewById(R.id.bus);
        laundry=findViewById(R.id.laundry);
        food=findViewById(R.id.food);
        parcel=findViewById(R.id.parcel);
        appointments=findViewById(R.id.appointments);
        ecitizen=findViewById(R.id.ecitizen);
        bill=findViewById(R.id.bill);
        gas=findViewById(R.id.gas);

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent( DashboardActivity.this, Shopping.class);
            startActivity(intent);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DashboardActivity.this, BusBooking.class);
                startActivity(intent);
            }
        });
        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DashboardActivity.this, Laundry.class);
                startActivity(intent);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DashboardActivity.this, FoodDelivery.class);
                startActivity(intent);
            }
        });

        parcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DashboardActivity.this, ParcelDelivery.class);
                startActivity(intent);
            }
        });
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DashboardActivity.this, Appointments.class);
                startActivity(intent);
            }
        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DashboardActivity.this, BillPayments.class);
                startActivity(intent);
            }
        });
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( DashboardActivity.this, GasDelivery.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}




