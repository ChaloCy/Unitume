package com.example.unitume;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class RegistrationActivity extends AppCompatActivity {

    EditText edtName, edtPassword,edtEmail,edtNumber;
    Button register;
    public String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtEmail = (EditText)findViewById(R.id.email);
        edtNumber = (EditText)findViewById(R.id.number);
        edtName = (EditText)findViewById(R.id.name);
        edtPassword = (EditText)findViewById(R.id.password);

        register  = (Button)findViewById(R.id.register);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
         final DatabaseReference table_user = database.getReference("User");

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                         token = task.getResult().getToken();
                    }
                });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(RegistrationActivity.this);
                mDialog.setMessage("Loading...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapsot){
                        //checking if user already exists
                        if(dataSnapsot.child(edtNumber.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Already Registered!", Toast.LENGTH_LONG).show();
                        }
                        else
                            {
                            mDialog.dismiss();
                            User user = new User(edtName.getText().toString(),edtPassword.getText().toString(),edtEmail.getText().toString(),token);
                            table_user.child(edtNumber.getText().toString()).setValue(user);
                                Intent intent = new Intent(RegistrationActivity.this, DashboardActivity.class);
                                Toast.makeText(RegistrationActivity.this, "Registered successful!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                    @Override
                            public void onCancelled(DatabaseError databaseError){
                    }
                });

            }
        });
    }
}
