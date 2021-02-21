package com.example.timer.Database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timer.Models.ItemModel;

import java.util.List;

@androidx.room.Dao
public interface MyDao {

    @Query("SELECT * FROM ItemModel")
    List<ItemModel> GetAll();

    @Insert
    void Insert(ItemModel timer);

    @Update
    void Update(ItemModel timer);

    @Delete
    void Delete(ItemModel timer);

    @Query("DELETE FROM ItemModel")
    void Clear();
}
