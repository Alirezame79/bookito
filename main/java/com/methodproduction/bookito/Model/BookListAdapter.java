package com.methodproduction.bookito.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.methodproduction.bookito.R;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder>{

    private List<Book> items;

    public BookListAdapter(List<Book> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookName.setText(items.get(position).getName());
        holder.author.setText(items.get(position).getAuthor());
//        holder.bookImage.
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bookName;
        public TextView author;
        public ImageView bookImage;

        public ViewHolder(View view){
            super(view);
            bookName = view.findViewById(R.id.book_name_text);
            author = view.findViewById(R.id.book_author_text);
            bookImage = view.findViewById(R.id.book_image);
        }
    }
}
