package com.methodproduction.bookito.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import com.methodproduction.bookito.Model.Book;
import com.methodproduction.bookito.R;
import com.squareup.picasso.Picasso;


public class BookDetailFragment extends Fragment {

    ImageView image;
    TextView name;
    TextView author;
    TextView pubDate;
    TextView detail;
    Button downloadBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        Log.d("TAG", "getArgument()" + getArguments().getString("book_detail_key"));

        Gson gson = new Gson();

        Book book = gson.fromJson(getArguments().getString("book_detail_key"), Book.class);
        name.setText(getString(R.string.name) + book.getName());
        author.setText(getString(R.string.author) + book.getAuthor());
        pubDate.setText(getString(R.string.publish_date) + book.getPubDate());
        detail.setText(getString(R.string.detail) + book.getDetails());

        Picasso.get().load("http://" + book.getImageUrl()).into(image);

        Log.d("TAG", "image url " + "http://" + book.getImageUrl());
    }

    private void findViews(View view) {
        image = view.findViewById(R.id.book_image);
        name = view.findViewById(R.id.book_name);
        author = view.findViewById(R.id.book_author);
        pubDate = view.findViewById(R.id.book_pub_date);
        detail = view.findViewById(R.id.book_details);
        downloadBtn = view.findViewById(R.id.download_btn);
    }

}
