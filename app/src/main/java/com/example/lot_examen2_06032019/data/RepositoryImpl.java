package com.example.lot_examen2_06032019.data;

import android.os.AsyncTask;

import com.example.lot_examen2_06032019.data.local.model.Book;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RepositoryImpl implements Repository {

    private final BookDao bookDao;

    public RepositoryImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void insertBook(final Book book) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> bookDao.insert(book));
    }

    @Override
    public void deleteBook(Book book) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> bookDao.delete(book));
    }

    @Override
    public LiveData<List<Book>> queryBooks() {
        return bookDao.queryBooks();
    }
}
