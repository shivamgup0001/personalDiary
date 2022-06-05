package com.example.personaldiary.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="date")
    public String DATE;

    @ColumnInfo(name="data")
    public String DATA;
}
