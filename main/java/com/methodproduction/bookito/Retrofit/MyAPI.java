package com.methodproduction.bookito.Retrofit;

import com.methodproduction.bookito.Model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyAPI {

    @Headers({
            "Accept: application/json"
    })
    @POST("search.php")
    Call<List<Book>> getBookName(@Query("bookName") String bookName, @Query("author") String author);
}
