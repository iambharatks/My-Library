package com.example.mywishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavouriteBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_books);

        RecyclerView recyclerView = findViewById(R.id.bookRecView);
        BooksRecViewAdaptor adaptor = new BooksRecViewAdaptor(this,"FavouriteBooks");
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptor.setBooks(Utils.getInstance(this).getAllFavBooks());
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent (this , MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}