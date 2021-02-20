package com.example.ppolab2.SqlDatabase;

import android.content.Context;

public class DeleteHelper {
    private SQLiteHelper sqLiteHelper;

    public DeleteHelper(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    public void delete(int id) {
        sqLiteHelper.open();
        sqLiteHelper.deleteOneItem(id);
        sqLiteHelper.close();
    }

}
