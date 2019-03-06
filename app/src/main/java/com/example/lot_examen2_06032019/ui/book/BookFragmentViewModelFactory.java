package com.example.lot_examen2_06032019.ui.book;


import com.example.lot_examen2_06032019.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class BookFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public BookFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookFragmentViewModel.class)) {
            return (T) new BookFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
