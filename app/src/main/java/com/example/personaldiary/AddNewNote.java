package com.example.personaldiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.personaldiary.db.AppDataBase;
import com.example.personaldiary.db.User;
import com.google.android.material.textfield.TextInputEditText;

public class AddNewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        EditText date=findViewById(R.id.editTextDate);
        TextInputEditText data=findViewById(R.id.editData);
        Button sumbit=findViewById(R.id.button);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveNewNote(date.getText().toString(),data.getText().toString());
            }
        });
    }

    private void saveNewNote(String date,String data){
        AppDataBase db=AppDataBase.getDbInstance(this.getApplicationContext());

        User user=new User();
        user.DATE=date;
        user.DATA=data;
        db.userDao().insertUser(user);

        finish();
    }
}