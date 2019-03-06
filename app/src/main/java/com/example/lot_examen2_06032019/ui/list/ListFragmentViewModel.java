package com.example.lot_examen2_06032019.ui.list;

import com.example.lot_examen2_06032019.data.Repository;
import com.example.lot_examen2_06032019.data.local.model.Book;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ListFragmentViewModel extends ViewModel {

    private final Repository repository;
    private Book deletedBook;

    private final LiveData<List<Book>> books;

    public ListFragmentViewModel(Repository repository) {
        this.repository = repository;
        books = repository.queryBooks();
        deletedBook = new Book();
    }

    public LiveData<List<Book>> getCompanies() {
        return books;
    }

    public void deleteBook(Book book) {
        repository.deleteBook(book);
    }

    public Book getDeletedBook() {
        return deletedBook;
    }

    public void setDeletedBook(Book deletedBook) {
        this.deletedBook = deletedBook;
    }

    public void insertBook(Book book) {
        repository.insertBook(book);
    }
}
