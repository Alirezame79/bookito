package com.methodproduction.bookito.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.methodproduction.bookito.Model.Book;
import com.methodproduction.bookito.Model.BookListAdapter;
import com.methodproduction.bookito.R;
import com.methodproduction.bookito.Retrofit.Global;
import com.methodproduction.bookito.ViewModel.MyViewModel;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartingFragment extends Fragment {

    EditText bookNameEditText;
    Button searchBtn;

    TextView noResultFound;
    TextView searchResultBoard;
    ProgressBar bookListProgressBar;

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_starting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        searchBookMet();
        editTextFocus();
    }

    private void editTextFocus() {
        bookNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchResultBoard.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                noResultFound.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchBookMet() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookListProgressBar.setVisibility(View.VISIBLE);

                String bookName = bookNameEditText.getText().toString();

                if (bookName.equals("") || bookName.length() < 3){
                    Toast.makeText(getContext(), "Enter a Valid text!", Toast.LENGTH_SHORT).show();
                    return;
                }

                searchResultBoard.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

                Global.getMyAPI().getBookName(bookName, bookName).enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        Log.d("TAG", "response" + response.body());
                        recyclerViewSet(response.body());
                        MyViewModel.updateList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {

                    }
                });


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyViewModel.bookList != null){
            recyclerViewSet(MyViewModel.bookList);
            searchResultBoard.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void recyclerViewSet(List<Book> arrayList) {
        bookListProgressBar.setVisibility(View.INVISIBLE);

        if (arrayList.size() == 0){
            noResultFound.setVisibility(View.VISIBLE);
        }else {
            noResultFound.setVisibility(View.INVISIBLE);
        }

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new BookListAdapter(arrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.book_list_recyclerview);
        bookNameEditText = view.findViewById(R.id.book_name_edit_text);
        searchBtn = view.findViewById(R.id.search_btn);
        searchResultBoard = view.findViewById(R.id.search_result_board);
        bookListProgressBar = view.findViewById(R.id.progress_bar_book_list);
        noResultFound = view.findViewById(R.id.no_result_found_board);
    }
}
