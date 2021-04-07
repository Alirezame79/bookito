package com.methodproduction.bookito.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.methodproduction.bookito.Model.Book;
import com.methodproduction.bookito.Model.BookListAdapter;
import com.methodproduction.bookito.R;
import com.methodproduction.bookito.ViewModel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {

    public List<Book> bookList = new ArrayList<>();

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    ArrayList<Book> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getList();
        Gson gson = new Gson();
        String stringList = null;
        Book book = new Book();
        book = gson.fromJson(getArguments().getString("book_list_key"), Book.class);
//        bookList = getArguments().getParcelableArrayList("book_list_key");

//        bookList = getList();
        recyclerviewController(view, bookList);
        Log.d("TAG", "book list in fragment 6" + bookList);
    }

    private void recyclerviewController(View view, List<Book> bookList) {
        Log.d("TAG", "book list in fragment 4" + bookList);
        recyclerView = view.findViewById(R.id.book_list_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new BookListAdapter(bookList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Log.d("TAG", "book list in fragment 5" + bookList);
    }

    private List<Book> getList() {
        MyViewModel viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        final LiveData<List<Book>> liveData = viewModel.getInstance();
        Log.d("TAG", "book list in fragment 1" + bookList);

//        liveData.observe(getViewLifecycleOwner(), new Observer<List<Book>>() {
//            @Override
//            public void onChanged(List<Book> list) {
//                Log.d("TAG", "book list in fragment 2" + bookList);
//                bookList = liveData.getValue();
//            }
//        });
        bookList = liveData.getValue();
        Log.d("TAG", "book list in fragment 3" + bookList);
        return bookList;
    }
}
