package com.example.unitume;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;;

public class LoginActivity extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPhone = (EditText) findViewById(R.id.number);
        edtPassword = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        final  FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Loading...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //checking if user does not exist in database
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //get user's information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                               Common.currentUser = user;
                               startActivity(intent);
                               finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                         {
                             mDialog.dismiss();
                             Toast.makeText(LoginActivity.this, "User does not exist!", Toast.LENGTH_LONG).show();
                         }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });
    }
}