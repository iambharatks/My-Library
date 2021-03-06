package com.example.mywishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class WantToReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read_book);

        RecyclerView recyclerView = findViewById(R.id.bookRecView);
        BooksRecViewAdaptor adaptor = new BooksRecViewAdaptor(this,"WanttoReadBooks");
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptor.setBooks(Utils.getInstance(this).getWantToReadBooks());
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent (this , MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}