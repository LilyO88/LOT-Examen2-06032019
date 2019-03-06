package com.example.lot_examen2_06032019.ui.list;

import com.example.lot_examen2_06032019.data.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public ListFragmentViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListFragmentViewModel.class)) {
            return (T) new ListFragmentViewModel(repository);
        } else {
            throw new IllegalArgumentException("Incorrect viewmodelClass");
        }
    }
}
