package com.example.lot_examen2_06032019.ui.list;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lot_examen2_06032019.R;
import com.example.lot_examen2_06032019.data.local.model.Book;
import com.example.lot_examen2_06032019.databinding.FragmentListBinding;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragmentAdapter extends ListAdapter<Book, ListFragmentAdapter.ViewHolder> {

    private final Context context;
    private FragmentListBinding b;

    protected ListFragmentAdapter(Context context, FragmentListBinding b) {
        super(new DiffUtil.ItemCallback<Book>() {
            @Override
            public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
                return TextUtils.equals(oldItem.getTitle(), newItem.getTitle())
                        && TextUtils.equals(oldItem.getAuthor(), newItem.getAuthor())
                        && oldItem.getYear() == newItem.getYear()
                        && TextUtils.equals(oldItem.getImage(), newItem.getImage())
                        && TextUtils.equals(oldItem.getSinopsis(), newItem.getSinopsis());
            }
        });
        this.context = context;
        this.b = b;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item, parent, false), context, b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    protected Book getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cardTitle;
        private TextView cardAuthor;
        private TextView cardYear;
        private ImageView image;

        Context contextVH;

        FragmentListBinding bVH;

        public ViewHolder(@NonNull View itemView, Context contextVH, FragmentListBinding bVH) {
            super(itemView);
            setupViews();
            this.contextVH = contextVH;
            itemView.setOnClickListener(v -> {
                String sinp;
                b.bsTitle.setText(getItem(getAdapterPosition()).getTitle());
                if(TextUtils.isEmpty(getItem(getAdapterPosition()).getSinopsis())) {
                    sinp = "<Sinopsis no disponible>";
                } else {
                    sinp = getItem(getAdapterPosition()).getSinopsis();
                }
                b.bsSinopsis.setText(sinp);
            });
        }

        private void setupViews() {
            cardTitle = ViewCompat.requireViewById(itemView, R.id.card_title);
            cardAuthor = ViewCompat.requireViewById(itemView, R.id.card_author);
            cardYear = ViewCompat.requireViewById(itemView, R.id.card_year);
            image = ViewCompat.requireViewById(itemView, R.id.card_image);
        }

        public void bind(Book book) {
            String url;
            cardTitle.setText(book.getTitle());
            cardAuthor.setText(book.getAuthor());
            cardYear.setText(Integer.toString(book.getYear()));
            if(TextUtils.isEmpty(book.getImage())) {
                url = "https://www.visitenkarterri.com/default/imagenes/no_imagen.sw278.sh155.ct1.png";
            } else {
                url = book.getImage();
            }

            Picasso.with(contextVH).load(url).into(image);
        }
    }
}
