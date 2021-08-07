package com.devsan.seenitassignment.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.devsan.seenitassignment.R;
import com.devsan.seenitassignment.databinding.ActivityBookDetailBinding;
import com.devsan.seenitassignment.model.BookVO;
import com.devsan.seenitassignment.model.BooksDataSource;
import com.devsan.seenitassignment.util.DataResponseListener;

public class BookDetailActivity extends BaseActivity {

    public static final String EXTRA_DATA = "extra_data";

    private BooksDataSource booksDataSource;
    ActivityBookDetailBinding activityBookDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBookDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_book_detail);
        booksDataSource = new BooksDataSource();

        setTitle("Detail Screen");
        BookVO bookVO = (BookVO) getIntent().getSerializableExtra(EXTRA_DATA);
        activityBookDetailBinding.setViewItem(bookVO);
        setData(bookVO.getId());
    }

    private void setData(int id) {

        showProgress();
        booksDataSource.getBook(id, new DataResponseListener<BookVO>() {
            @Override
            public void onSuccess(BookVO bookVO) {

                activityBookDetailBinding.setViewItem(bookVO);
                hideProgress();
            }

            @Override
            public void onFailure(String message) {

                showToast(message);
                hideProgress();
            }
        });
    }
}