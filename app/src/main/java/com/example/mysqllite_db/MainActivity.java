package com.example.mysqllite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, email;
    Button buttonOne, buttonTwo, buttonThree;

    DBHelper dbHelper = new DBHelper(this);
    private String TAG = "-----------------------------";
    ContentValues contentValues = new ContentValues();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameId);
        email = (EditText) findViewById(R.id.emailId);

        buttonOne = (Button) findViewById(R.id.buttonIdOne);
        buttonOne.setOnClickListener(this);

        buttonTwo = (Button) findViewById(R.id.buttonIdTwo);
        buttonTwo.setOnClickListener(this);

        buttonThree = (Button) findViewById(R.id.buttonIdThree);
        buttonThree.setOnClickListener(this);
        dbHelper = new DBHelper(this);


    }
    SQLiteDatabase openHelper = dbHelper.getWritableDatabase();

    @Override
    public void onClick(View v) {
        String  nameT = name.getText().toString();
        String  emailT = email.getText().toString();

        switch (v.getId()){
                case R.id.buttonIdOne:
                    contentValues.put(DBHelper.KEY_NAME, nameT);
                    contentValues.put(DBHelper.KEY_EMAIL, emailT);
                    Log.d(TAG, "onClick: "+contentValues.toString());
                    openHelper.insert(DBHelper.TABLE_NAME,null, contentValues);
                    Toast.makeText(this, "Click_ButtonOne", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonIdTwo:
                    Cursor cursor = openHelper.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
                    if (cursor.moveToFirst()){
                        int isIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                        int isName = cursor.getColumnIndex(DBHelper.KEY_NAME);
                        int isEmail = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
                        do {
                            Log.d("My first Data,,......................................................................................", cursor.getInt(isIndex) + "  Name "
                            + cursor.getString(isName) + " Email " + cursor.getString(isEmail));
                        }while (cursor.moveToNext());
                    }else {
                        Log.d("My First Data..................................................................", "Error sistem");
                    }
                    cursor.close();
                    Toast.makeText(this, "Click_ButtonTwo", Toast.LENGTH_SHORT).show();
                    break;
                  case R.id.buttonIdThree:

                    Toast.makeText(this, "Click_ButtonThree", Toast.LENGTH_SHORT).show();
                    break;

        }

    }
}