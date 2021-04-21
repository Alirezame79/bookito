package com.methodproduction.bookito.Model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bookName.setText(items.get(position).getName());
        holder.author.setText(items.get(position).getAuthor());

        holder.booksContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book;
                book = bookInitializer(position);

                Gson gson = new Gson();
                String bookObject = gson.toJson(book, Book.class);

                Bundle bundle = new Bundle();
                bundle.putString("book_detail_key", bookObject);

                Navigation.findNavController(v).navigate(R.id.action_startingFragment_to_bookDetailFragment, bundle);
            }
        });
    }

    private Book bookInitializer(int position) {
        Book book = new Book();
        book.setName(items.get(position).getName());
        book.setAuthor(items.get(position).getAuthor());
        book.setPubDate(items.get(position).getPubDate());
        book.setDetails(items.get(position).getDetails());
        book.setImageUrl(items.get(position).getImageUrl());
        return book;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bookName;
        public TextView author;
        public LinearLayout booksContainer;

        public ViewHolder(View view){
            super(view);
            bookName = view.findViewById(R.id.book_name_text);
            author = view.findViewById(R.id.book_author_text);
            booksContainer = view.findViewById(R.id.book_text_container);
        }
    }
}
