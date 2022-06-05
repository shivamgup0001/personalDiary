package com.example.personaldiary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.personaldiary.db.AppDataBase;
import com.example.personaldiary.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton img;
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.imageButton);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);
        loadUserList();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,AddNewNote.class);
                startActivityForResult(i,100);
            }
        });
    }

    private void loadUserList(){
        AppDataBase db=AppDataBase.getDbInstance(this.getApplicationContext());
        List<User> userList=db.userDao().getAllUsers();
        recyclerViewAdapter.setUserList(userList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
            loadUserList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}