package com.example.lot_examen2_06032019.ui.book;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lot_examen2_06032019.R;
import com.example.lot_examen2_06032019.data.RepositoryImpl;
import com.example.lot_examen2_06032019.data.local.AppDatabase;
import com.example.lot_examen2_06032019.data.local.model.Book;
import com.example.lot_examen2_06032019.databinding.FragmentBookBinding;
import com.example.lot_examen2_06032019.utils.ValidationUtils;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;


public class BookFragment extends Fragment {

    FragmentBookBinding b;
    private BookFragmentViewModel viewModel;

    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         b = FragmentBookBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new BookFragmentViewModelFactory(new RepositoryImpl(
                AppDatabase.getInstance(requireContext().getApplicationContext()).bookDao())))
                .get(BookFragmentViewModel.class);
        setupViews();
    }

    private void setupViews() {
        Picasso.with(getContext()).load("https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201606/03/00106520410116____3__640x640.jpg").into(b.bookImage);
        b.bookFab.setOnClickListener(v -> save());
    }

    private void save() {
        if(validateAll()) {
            showDialog();
        } else {
            Snackbar.make(requireView(), "Error al guardar el libro", Snackbar.LENGTH_LONG).show();
        }
    }

    private void showDialog() {
        AlertDialog.Builder noti = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert);
        noti.setTitle("Guardar");
        noti.setMessage("¿Está seguro que desea guardar el libro?");
        noti.setPositiveButton("Sí", (dialog, which) -> saveYes());
        noti.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        Dialog dialog = noti.create();
        dialog.show();
    }

    private void saveYes() {
        String urlImg;
        if(TextUtils.isEmpty(b.bookTxtUrl.getText().toString())) {
            urlImg = "https://www.visitenkarterri.com/default/imagenes/no_imagen.sw278.sh155.ct1.png";
        } else {
            urlImg = b.bookTxtUrl.getText().toString();
        }

        Book book = new Book(b.bookTxtTitle.getText().toString(),
                b.bookTxtAuthor.getText().toString(),
                Integer.parseInt(b.bookTxtYear.getText().toString()),
                urlImg,
                b.bookTxtSinopsis.getText().toString());
        viewModel.insertBook(book);
        Snackbar.make(requireView(), "¡Libro guardado con éxito!", Snackbar.LENGTH_LONG).show();
        getFragmentManager().popBackStack();
    }

    private boolean validateAll() {
        Pattern patron = Pattern.compile("[1][0-9]{3}");
//        Pattern patron2 = Pattern.compile("[1-9][0-9]{2}");
        boolean flag = true;
        if(!ValidationUtils.isValidString(b.bookTxtTitle.getText().toString())) {
            b.title.setError("Campo vacío");
            flag = false;
        }
        if (!ValidationUtils.isValidString(b.bookTxtAuthor.getText().toString())) {
            b.author.setError("Campo vacío");
            flag = false;
        }
        if (!patron.matcher(b.bookTxtYear.getText().toString()).matches()
//                || !patron2.matcher(b.bookTxtYear.getText().toString()).matches()
                || !ValidationUtils.isValidString(b.bookTxtYear.getText().toString())) {
            if(!patron.matcher(b.bookTxtYear.getText().toString()).matches()) {
//                    || !patron2.matcher(b.bookTxtYear.getText().toString()).matches()) {
                b.year.setError("El formato no corresponde");
            } else {
                b.year.setError("Campo vacío");
            }
            flag = false;
        }
        return flag;
    }
}
