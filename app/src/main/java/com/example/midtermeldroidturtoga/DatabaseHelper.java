package com.example.midtermeldroidturtoga;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;


public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "BookData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        sqlDB.execSQL("create Table bookDetails(bookId INTEGER primary key, bookTitle TEXT, bookGenre TEXT, bookPrice INTEGER, bookstack INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int u, int u1) {
        sqlDB.execSQL("drop Table if exists bookDetails");
    }


    public boolean create(String bookId, String bookTitle, String bookGenre, String bookPrice, String bookStack) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bookId", bookId);
        values.put("bookTitle", bookTitle);
        values.put("bookGenre", bookGenre);
        values.put("bookPrice", bookPrice);
        values.put("bookStack", bookStack);

        long result = sqlDb.insert("bookDetails", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean update(String bookId, String bookTitle, String bookGenre, String bookPrice, String bookStack) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("bookTitle", bookTitle);
        values.put("bookGenre", bookGenre);
        values.put("bookPrice", bookPrice);
        values.put("bookStack", bookStack);

        Cursor cursor = sqlDb.rawQuery("Select * from bookDetails where bookId = ?", new String[]{bookId});
        if (cursor.getCount() > 0) {
            long result = sqlDb.update("bookDetails", values, "bookId=?", new String[]{bookId});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Cursor getdata() {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from bookDetails", null);
        return cursor;
    }


    public Cursor retrievedata(EditText bookId) {
        String id = bookId.getText().toString();
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from BookDetails where bookId ='" + id + "'", null);
        return cursor;
    }

    public boolean delete(String bookId) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from bookDetails where bookId = ?", new String[]{bookId});
        if (cursor.getCount() > 0) {
            long result = sqlDb.delete("bookDetails", "bookId=?", new String[]{bookId});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}


