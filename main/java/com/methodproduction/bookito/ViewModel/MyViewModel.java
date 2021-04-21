package com.methodproduction.bookito.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.methodproduction.bookito.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    public static List<Book> bookList;

    public static void updateList(List<Book> list){
        bookList = list;
    }

    public static void clearList(){
        bookList.clear();
    }
}
