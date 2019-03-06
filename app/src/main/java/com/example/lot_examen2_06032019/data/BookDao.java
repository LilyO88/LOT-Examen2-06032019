package com.example.lot_examen2_06032019.data;

import com.example.lot_examen2_06032019.base.BaseDao;
import com.example.lot_examen2_06032019.data.local.model.Book;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface BookDao extends BaseDao<Book> {

    @Query("SELECT * FROM book")
    LiveData<List<Book>> queryBooks();

}
