package com.devsan.seenitassignment.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.devsan.seenitassignment.R;

import static com.devsan.seenitassignment.R.style.Theme_AppCompat_Dialog_Alert;
import static com.devsan.seenitassignment.R.style.Theme_MyDialog;

public class BaseActivity extends AppCompatActivity {

    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this,
                R.style.Theme_MyDialog);
    }

    public void showProgress() {

        if (null != mProgressDialog && !mProgressDialog.isShowing()) {
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.show();
        }
    }

    public void hideProgress() {

        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showToast(String messgae) {

        Toast.makeText(getApplicationContext(), messgae, Toast.LENGTH_SHORT).show();
    }
}
