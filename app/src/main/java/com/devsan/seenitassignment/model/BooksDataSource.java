package com.devsan.seenitassignment.model;

import com.devsan.seenitassignment.Volley.VolleyWrapper;
import com.devsan.seenitassignment.util.DataResponseListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BooksDataSource {

    private final static String BASE_URL = "https://tpbookserver.herokuapp.com/";
    private final static String BOOKS_LIST_URL = "books";
    private final static String BOOK_SINGLE_URL = "book/";

    private final VolleyWrapper volleyWrapper;
    private final Gson mGson;

    public BooksDataSource() {
        volleyWrapper = new VolleyWrapper();
        mGson = new GsonBuilder().create();
    }

    public void getAllBooksList(DataResponseListener<ArrayList<BookVO>> listener) {

        volleyWrapper.getRequest(BASE_URL + BOOKS_LIST_URL, new DataResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {

                ArrayList<BookVO> bookVOS = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            BookVO model = mGson.fromJson(jsonArray.opt(i).toString(), BookVO.class);
                            bookVOS.add(model);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onSuccess(bookVOS);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }

    public void getBook(int id, DataResponseListener<BookVO> listener) {

        volleyWrapper.getRequest(BASE_URL + BOOK_SINGLE_URL + id, new DataResponseListener<Object>() {

            @Override
            public void onSuccess(Object response) {

                BookVO bookVO = new BookVO();
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    bookVO = mGson.fromJson(jsonObject.toString(), BookVO.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onSuccess(bookVO);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }
}
