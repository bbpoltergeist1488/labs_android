package com.example.timer.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ItemModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String ItemName;
    public int PrepareTime;
    public int WorkTime;
    public int RestTime;
    public int Sets;
    public int Cycle;
    public int Color;
}
