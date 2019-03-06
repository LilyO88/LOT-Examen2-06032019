package com.example.lot_examen2_06032019.data;

import com.example.lot_examen2_06032019.data.local.model.Book;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface Repository {

    //    Book
    void insertBook(Book book);
    void deleteBook(Book book);

    LiveData<List<Book>> queryBooks();
}
