package com.example.lot_examen2_06032019.ui.book;


import com.example.lot_examen2_06032019.data.Repository;
import com.example.lot_examen2_06032019.data.local.model.Book;

import androidx.lifecycle.ViewModel;

public class BookFragmentViewModel extends ViewModel {

    private final Repository repository;

    public BookFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void insertBook(Book book) {
        repository.insertBook(book);
    }
}
