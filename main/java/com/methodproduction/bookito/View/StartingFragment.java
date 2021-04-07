package com.methodproduction.bookito.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.methodproduction.bookito.Model.Book;
import com.methodproduction.bookito.R;
import com.methodproduction.bookito.Retrofit.Global;
import com.methodproduction.bookito.ViewModel.MyViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartingFragment extends Fragment {

    EditText bookNameEditText;
    Button searchBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_starting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = bookNameEditText.getText().toString();

                if (bookName.equals("") || bookName.length() < 3){
                    Toast.makeText(getContext(), "Enter a Valid text!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Global.getMyAPI().getBookName(bookName, bookName).enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        Log.d("TAG", "response" + response.body());

//                        MyViewModel viewModel = new MyViewModel();
//                        viewModel.getInstance().setValue(response.body());
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();

//                        List<List<Book>> list = Arrays.asList(response.body());
                        String bookListString = gson.toJson(response.body(), Book.class);

                        bundle.putString("book_list_key", bookListString);
//                        bundle.putParcelableArrayList("book_list_key", response.body());

                        Navigation.findNavController(view).navigate(R.id.action_startingFragment_to_bookListFragment);
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void findViews(View view) {
        bookNameEditText = view.findViewById(R.id.book_name_edit_text);
        searchBtn = view.findViewById(R.id.search_book_name_btn);
    }
}
