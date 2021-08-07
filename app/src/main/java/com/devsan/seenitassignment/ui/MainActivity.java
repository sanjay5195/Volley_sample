package com.devsan.seenitassignment.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.devsan.seenitassignment.R;
import com.devsan.seenitassignment.databinding.ActivityMainBinding;
import com.devsan.seenitassignment.model.BookVO;
import com.devsan.seenitassignment.model.BooksDataSource;
import com.devsan.seenitassignment.util.CustomClickListener;
import com.devsan.seenitassignment.util.DataResponseListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private BooksDataSource booksDataSource;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        booksDataSource = new BooksDataSource();

        setTitle("Book List");
        getAllBooksList();
    }

    private void getAllBooksList() {

        showProgress();
        booksDataSource.getAllBooksList(new DataResponseListener<ArrayList<BookVO>>() {
            @Override
            public void onSuccess(ArrayList<BookVO> bookVOS) {

                setRecyclerView(bookVOS);
                hideProgress();
            }

            @Override
            public void onFailure(String message) {

                showToast(message);
                hideProgress();
            }
        });
    }

    private void setRecyclerView(ArrayList<BookVO> bookVOS) {

        BooksAdapter booksAdapter = new BooksAdapter(bookVOS, new CustomClickListener() {
            @Override
            public void cardClicked(BookVO bookVO) {

            }
        });
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView.setAdapter(booksAdapter);
    }
}