package com.example.lot_examen2_06032019.data.local;

import android.content.Context;

import com.example.lot_examen2_06032019.data.BookDao;
import com.example.lot_examen2_06032019.data.local.model.Book;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "practices";

    public abstract BookDao bookDao();

    private static AppDatabase instance;
//    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
