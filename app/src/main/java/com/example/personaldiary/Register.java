package com.example.personaldiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText name,age,email,password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        name=findViewById(R.id.register_name);
        age=findViewById(R.id.register_age);
        email=findViewById(R.id.register_email);
        password=findViewById(R.id.register_password);
        register=findViewById(R.id.register_button);
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email1=email.getText().toString().trim();
        String password1=password.getText().toString().trim();
        String name1=name.getText().toString().trim();
        String age1= age.getText().toString().trim();

        if(name1.isEmpty()){
            name.setError("Full Name is required");
            name.requestFocus();
            return;
        }

        if(age1.isEmpty()){
            age.setError("Age is Required!");
            age.requestFocus();
            return;
        }

        if(email1.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        if(password1.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if(password1.length()<6){
            password.setError("Min password length should be 6 characters!");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email1,password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            UserLogin userLogin=new UserLogin(name1,age1,email1);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userLogin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this,"User has been registered successfully",Toast.LENGTH_LONG).show();

                                    }else {
                                        Toast.makeText(Register.this,"Failed to register! Try Again!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Register.this,"Failed to register! Try Again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}