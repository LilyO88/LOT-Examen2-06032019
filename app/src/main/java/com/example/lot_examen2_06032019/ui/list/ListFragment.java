package com.example.lot_examen2_06032019.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lot_examen2_06032019.R;
import com.example.lot_examen2_06032019.data.RepositoryImpl;
import com.example.lot_examen2_06032019.data.local.AppDatabase;
import com.example.lot_examen2_06032019.data.local.model.Book;
import com.example.lot_examen2_06032019.databinding.FragmentListBinding;
import com.google.android.material.snackbar.Snackbar;

public class ListFragment extends Fragment {

    private FragmentListBinding b;
    private  NavController navController;
    private ListFragmentViewModel viewModel;
    private ListFragmentAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_list, container, false);
        b = FragmentListBinding.inflate(inflater, container, false);
        return b.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModel = ViewModelProviders.of(this, new ListFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).bookDao())))
                .get(ListFragmentViewModel.class);
        setupListeners();
        setupRecyclerView(requireView());
        observeBooks();
    }

    private void observeBooks() {
        viewModel.getCompanies().observe(getViewLifecycleOwner(), books -> {
            listAdapter.submitList(books);
            b.listEmptyView.setVisibility(books.isEmpty() ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupRecyclerView(View view) {
        RecyclerView lstCompanies = ViewCompat.requireViewById(view, R.id.list_recyclerView);
        lstCompanies.setHasFixedSize(true);
        listAdapter = new ListFragmentAdapter(getContext(), b);
        lstCompanies.setAdapter(listAdapter);
        lstCompanies.setLayoutManager(
                new LinearLayoutManager(requireActivity()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        Book bookToDelete = listAdapter.getItem(viewHolder.getAdapterPosition());
                        viewModel.setDeletedBook(bookToDelete);
                        viewModel.deleteBook(bookToDelete);
                        Snackbar.make(requireView(), "Libro eliminado", Snackbar.LENGTH_LONG)
                                .setAction("Deshacer", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        viewModel.insertBook(viewModel.getDeletedBook());
                                    }
                                }).show();
                    }
                });
        itemTouchHelper.attachToRecyclerView(lstCompanies);
    }

    private void setupListeners() {
        b.listEmptyView.setOnClickListener(v -> navController.navigate(R.id.action_listFragment_to_bookFragment));
        b.clFab.setOnClickListener(v -> navController.navigate(R.id.action_listFragment_to_bookFragment));
    }
}
