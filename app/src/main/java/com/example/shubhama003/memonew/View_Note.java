package com.example.shubhama003.memonew;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



public class View_Note extends AppCompatActivity {

    SQLiteDatabase db;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        final String s = getIntent().getExtras().getString("Titl");

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
        String args[] = {s};
        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_NAME + " where " + dbHelper.TITLE + "=?" , args);
        TextView title = (TextView) findViewById(R.id.title);
        TextView detail = (TextView) findViewById(R.id.detail);
        TextView notetype = (TextView) findViewById(R.id.note_type_ans);
        TextView time = (TextView) findViewById(R.id.alertvalue);
        TextView date = (TextView) findViewById(R.id.datevalue);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                title.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TITLE)));
                detail.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DETAIL)));
                notetype.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TYPE)));
                time.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TIME)));
                date.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DATE)));

            }
            cursor.close();
        }
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String s = getIntent().getExtras().getString("Titl");

        switch (item.getItemId()) {
            case R.id.action_back:
                Intent openMainActivity = new Intent(this, MainActivity.class);
                startActivity(openMainActivity);
                return true;
            case R.id.action_edit:

                Intent openEditNote = new Intent(View_Note.this, Edit_Note.class);
                openEditNote.putExtra("Titl",s);
                startActivity(openEditNote);
                return true;

            case R.id.action_discard:
                AlertDialog.Builder builder = new AlertDialog.Builder(View_Note.this);
                builder
                        .setTitle(getString(R.string.delete_title))
                        .setMessage(getString(R.string.delete_message))
                        .setIcon(R.drawable.alert)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                final String s = getIntent().getExtras().getString("Titl");
                                String[] qwe = {s};
                                db.delete(DbHelper.TABLE_NAME, DbHelper.TITLE + "=?" , qwe);

                                db.close();
                                Intent openMainActivity = new Intent(View_Note.this, MainActivity.class);
                                startActivity(openMainActivity);

                            }
                        })
                        .setNegativeButton(getString(R.string.no), null)                        //Do nothing on no
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
