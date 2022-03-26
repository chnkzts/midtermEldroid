package com.example.midtermeldroidturtoga;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText bookId, bookTitle, bookGenre, bookPrice, bookStack;
    Button create, retrieveAll, retrieveById,update, delete, clear;
    DatabaseHelper DBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bookId = findViewById(R.id.bId);
        bookTitle = findViewById(R.id.bookTitle);
        bookGenre = findViewById(R.id.bookGenre);
        bookPrice = findViewById(R.id.bookPrice);
        bookStack = findViewById(R.id.bookStack);



        create = findViewById(R.id.bCreate);
        retrieveAll = findViewById(R.id.bRetrieveAll);
        retrieveById = findViewById(R.id.bRetrieveId);
        update = findViewById(R.id.bUpdate);
        delete = findViewById(R.id.bDelete);
        clear = findViewById(R.id.bClear);



        DBhelper = new DatabaseHelper(this);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = bookId.getText().toString();
                String title = bookTitle.getText().toString();
                String genre = bookGenre.getText().toString();
                String price = bookPrice.getText().toString();
                String stack = bookStack.getText().toString();
                Boolean createdata = DBhelper.create(id, title, genre, price, stack);

                if(createdata == true){
                    Toast.makeText(MainActivity.this,"Successful!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Unsuccessful!", Toast.LENGTH_SHORT).show(); } }});


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = bookId.getText().toString();
                String title = bookTitle.getText().toString();
                String genre = bookGenre.getText().toString();
                String price = bookPrice.getText().toString();
                String stack = bookStack.getText().toString();

                if(id.isEmpty() && title.isEmpty() && genre.isEmpty() && price.isEmpty() && stack.isEmpty() ){
                    Toast.makeText(getApplicationContext(),"Already Empty!",Toast.LENGTH_SHORT).show();
                }else{
                    bookId.setText("");
                    bookTitle.setText("");
                    bookGenre.setText("");
                    bookPrice.setText("");
                    bookStack.setText("");
                } }});


        retrieveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor result = DBhelper.getdata();

                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Records Found!", Toast.LENGTH_SHORT).show();
                    return; }
                while (result.moveToNext()) {
                    buffer.append("Book ID: " + result.getString(0) + "\n");
                    buffer.append("Book Title: " + result.getString(1) + "\n");
                    buffer.append("Book Genre: " + result.getString(2) + "\n");
                    buffer.append("Book Price: " + result.getString(3) + "\n");
                    buffer.append("Book Stack: " + result.getString(4) + "\n\n"); }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Book Details");
                builder.setMessage(buffer.toString());
                builder.show();}});


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = bookId.getText().toString();
                Boolean deletedata = DBhelper.delete(id);

                if(deletedata == true){
                    Toast.makeText(MainActivity.this,"Successful!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Unsuccessful!", Toast.LENGTH_SHORT).show(); } }});


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = bookId.getText().toString();
                String title = bookTitle.getText().toString();
                String genre = bookGenre.getText().toString();
                String price = bookPrice.getText().toString();
                String stack = bookStack.getText().toString();
                Boolean updatedata = DBhelper.update(id, title, genre, price, stack);

                if(updatedata == true){
                    Toast.makeText(MainActivity.this,"Successful!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Unsuccessful!", Toast.LENGTH_SHORT).show(); } }});



        retrieveById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor result = DBhelper.retrievedata(bookId);
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Not Existed!", Toast.LENGTH_SHORT).show();
                    return; }
                while (result.moveToNext()) {
                    buffer.append("Book ID: " + result.getString(0) + "\n");
                    buffer.append("Book Title: " + result.getString(1) + "\n");
                    buffer.append("Book Genre: " + result.getString(2) + "\n");
                    buffer.append("Book Price: " + result.getString(3) + "\n");
                    buffer.append("Book Stack: " + result.getString(4) + "\n\n"); }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Book Details");
                builder.setMessage(buffer.toString());
                builder.show();}});




    }
}