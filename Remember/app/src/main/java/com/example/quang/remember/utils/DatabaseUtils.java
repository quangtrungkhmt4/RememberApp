package com.example.quang.remember.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.quang.remember.model.ItemNote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseUtils {
    public static final String PATH = Environment.getDataDirectory().getPath()
            + "/data/com.example.quang.remember/databases/";

    public static final String DB_NAME = "remember_note.sqlite";
    public static final String TABLE_NAME = "TBLNote";
    public static final String ID = "ID";
    public static final String DATE = "DATE";
    public static final String TEXT = "TEXT";
    public static final String SOUND = "SOUND";
    public static final String IMAGE = "IMAGE";
    public static final String PHOTO = "PHOTO";
    public static final String TITLE = "TITLE";

    private Context context;
    private SQLiteDatabase database;

    public DatabaseUtils(Context context) {
        this.context = context;
        copyFileToDevice();
    }

    private void copyFileToDevice() {
        File file = new File(PATH + DB_NAME);
        if (!file.exists()) {
            File parent = file.getParentFile();
            parent.mkdirs();
            try {
                InputStream inputStream = context.getAssets().open(DB_NAME);
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int count = inputStream.read(b);
                while (count != -1) {
                    outputStream.write(b, 0, count);
                    count = inputStream.read(b);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openDatabase() {
        database = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    private void closeDatabase() {
        database.close();
    }

    public ArrayList<ItemNote> getData() {
        ArrayList<ItemNote> arr = new ArrayList<>();
        openDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        int indexId = cursor.getColumnIndex(ID);
        int indexDATE = cursor.getColumnIndex(DATE);
        int indexTEXT = cursor.getColumnIndex(TEXT);
        int indexSOUND = cursor.getColumnIndex(SOUND);
        int indexIMAGE = cursor.getColumnIndex(IMAGE);
        int indexPHOTO = cursor.getColumnIndex(PHOTO);
        int indexTITLE = cursor.getColumnIndex(TITLE);

        while (cursor.isAfterLast() == false) {
            int id = cursor.getInt(indexId);
            String date = cursor.getString(indexDATE);
            String text = cursor.getString(indexTEXT);
            String sound = cursor.getString(indexSOUND);
            String image = cursor.getString(indexIMAGE);
            String photo = cursor.getString(indexPHOTO);
            String title = cursor.getString(indexTITLE);
            ItemNote note = new ItemNote(id,date,text,sound,image,photo,title);
            arr.add(note);
            cursor.moveToNext();
        }
        closeDatabase();
        return arr;
    }

    public long insert(ItemNote item) {
        ContentValues values = new ContentValues();
        values.put(DATE, item.getDate());
        values.put(TEXT, item.getText());
        values.put(SOUND, item.getSound());
        values.put(IMAGE, item.getImage());
        values.put(PHOTO, item.getPhoto());
        values.put(TITLE, item.getTitle());
        openDatabase();
        long id = database.insert(TABLE_NAME, null, values);
        closeDatabase();
        return id;
    }

    public int update(ItemNote item) {
        ContentValues values = new ContentValues();
        values.put(DATE, item.getDate());
        values.put(TEXT, item.getText());
        values.put(SOUND, item.getSound());
        values.put(IMAGE, item.getImage());
        values.put(PHOTO, item.getPhoto());
        values.put(TITLE, item.getTitle());
        openDatabase();
        String where = ID + " = ?";
        String[] whereAgrs = {item.getId() + ""};
        int rows = database.update(TABLE_NAME, values, where, whereAgrs);
        closeDatabase();
        return rows;
    }

    public int delete(int id){
        openDatabase();
        String where = ID + " = ?";
        String[] whereAgrs = {id + ""};
        int rows = database.delete(TABLE_NAME,where,whereAgrs);
        closeDatabase();
        return rows;
    }
}
