package com.example.mywishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private Button btnCRead, btnARead, btnWRead, btnAFavourites;
    private TextView txtName, txtAuthor, txtPages, txtShortDec, txtLongDec, txtShowName, txtShowPages, txtShowAuthor;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        //TODO:Get the data from the recycler view
        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentReadingBook(incomingBook);
                    handleFavouriteBook(incomingBook);
                }
            }
        }
    }

    private void handleFavouriteBook(final Book incomingBook) {
        final ArrayList<Book> favouriteBooks = Utils.getInstance(this).getAllFavBooks();

        boolean existInFavouriteBooks = false;
        for (Book b : favouriteBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInFavouriteBooks = true;
                break;
            }
        }
        if (existInFavouriteBooks) {
            btnAFavourites.setEnabled(false);
        } else {
            btnAFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToFavouriteBooks( incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        //TODO : Navigate the user to already read books Activity
                        Intent intent = new Intent(BookActivity.this,FavouriteBooks.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentReadingBook(final Book incomingBook) {
        final ArrayList<Book> currentReadBooks = Utils.getInstance(this).getCurReadBooks();

        boolean existInCurrentReadBooks = false;
        for (Book b : currentReadBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInCurrentReadBooks = true;
                break;
            }
        }
        if (existInCurrentReadBooks) {
            btnCRead.setEnabled(false);
        } else {
            btnCRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(Utils.getInstance(BookActivity.this).addToCurrentReadBooks( incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        //TODO : Navigate the user to already read books Activity
                        Intent intent = new Intent(BookActivity.this,CurrentReadBooks.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book incomingBook) {
        final ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToReadBooks = false;
        for (Book b : wantToReadBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInWantToReadBooks = true;
                break;
            }
        }
        if (existInWantToReadBooks) {
            btnWRead.setEnabled(false);
        } else {
            btnWRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(Utils.getInstance(BookActivity.this).addToWantToReadBooks( incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        //TODO : Navigate the user to want to read books Activity
                        Intent intent = new Intent(BookActivity.this,WantToReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(final Book incomingBook) {
        final ArrayList<Book> alreadyReadBook = Utils.getInstance(this).getAllreadyReadBooks();

        boolean existInAlreadyReadBooks = false;
        for (Book b : alreadyReadBook) {
            if (b.getId() == incomingBook.getId()) {
                existInAlreadyReadBooks = true;
                break;
            }
        }
        if (existInAlreadyReadBooks) {
            btnARead.setEnabled(false);
        } else {
            btnARead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        //TODO : Navigate the user to already read books Activity
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void setData(Book book) {
        txtShowAuthor.setText(book.getAuthor());
        txtShowPages.setText(String.valueOf(book.getPages()));
        txtShowName.setText(book.getName());
        txtShortDec.setText(book.getShortDesc());
        txtLongDec.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageurl())
                .into(imgBook);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtName = findViewById(R.id.txtName);
        txtShortDec = findViewById(R.id.txtShortDec);
        txtLongDec = findViewById(R.id.txtLongDec);
        txtShowName = findViewById(R.id.txtShowName);
        txtShowPages = findViewById(R.id.txtShowPages);
        txtShowAuthor = findViewById(R.id.txtShowAuthor);

        btnCRead = findViewById(R.id.btnCRead);
        btnARead = findViewById(R.id.btnARead);
        btnWRead = findViewById(R.id.btnWRead);
        btnAFavourites = findViewById(R.id.btnAFavourites);

        imgBook = findViewById(R.id.imgBook);

    }
}