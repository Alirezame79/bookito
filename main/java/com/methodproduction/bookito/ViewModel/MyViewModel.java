package com.methodproduction.bookito.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.methodproduction.bookito.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    public static List<Book> bookList;
    public MutableLiveData<List<Book>> myLiveData;

    public MutableLiveData<List<Book>> getInstance(){
        if (myLiveData == null){
            myLiveData = new MutableLiveData<>();
        }
        return myLiveData;
    }

    public void updateList(List<Book> list){
        bookList = list;
        myLiveData.setValue(bookList);
    }

    public void clearList(){
        bookList = null;
    }
}
