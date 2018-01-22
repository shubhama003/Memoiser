package com.example.shubhama003.memonew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.shubhama003.memonew.DbHelper.show;


public class MainActivity extends AppCompatActivity {
    int p=5;
    SQLiteDatabase db;
    DbHelper mDbHelper;
    ListView list;
    int[] colours = {R.color.c1,R.color.c2,R.color.c3,R.color.c4,R.color.c5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.commentslist);
        mDbHelper = new DbHelper(this);
        db= mDbHelper.getWritableDatabase();
        final ImageView alarmImage = (ImageView) findViewById(R.id.alarmImage);

        String[] from = {mDbHelper.TITLE, mDbHelper.DETAIL, mDbHelper.TYPE, mDbHelper.TIME, mDbHelper.DATE};
        final String[] column = {mDbHelper.C_ID, mDbHelper.TITLE, mDbHelper.DETAIL, mDbHelper.TYPE, mDbHelper.TIME, mDbHelper.DATE};
        int[] to = {R.id.title, R.id.Detail, R.id.type, R.id.time, R.id.date};

        final Cursor cursor = db.query(mDbHelper.TABLE_NAME, column, null, null ,null, null, null);
        //final SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to,0);



       final adapter qwerty = new adapter(this, R.layout.list_entry, cursor, from, to,colours);


        list.setAdapter(qwerty);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView, View view, int position,
            long id){
                TextView t = view.findViewById(R.id.title);
                String tre = t.getText().toString();

                Intent intent = new Intent(MainActivity.this, View_Note.class);
                intent.putExtra("Titl",tre);
                //intent.putExtra(getString(R.string.rodId), id+1);
                startActivity(intent);
            }

        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t = view.findViewById(R.id.title);
                final String tre = t.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder
                        .setTitle(getString(R.string.delete_title))
                        .setMessage(getString(R.string.delete_message))
                        .setIcon(R.drawable.alert)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               final String smu = tre;
                                String[] qwe = {smu};
                                db.delete(DbHelper.TABLE_NAME, DbHelper.TITLE + "=?" , qwe);
                                db.close();
                                Intent openCreateNote = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(openCreateNote);

                            }
                        })
                        .setNegativeButton(getString(R.string.no), null)                        //Do nothing on no
                        .show();
                 return true;
            }
        });

        //Typeface ty = Typeface.createFromAsset(getAssets(),"fonts/ps.ttf");


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_new:
                Intent openCreateNote = new Intent(MainActivity.this, CreateNote.class);
                startActivity(openCreateNote);
                return true;
            case R.id.showIt:
               String p = show(db);
                if(p!=null)
                {Toast.makeText(this,p,Toast.LENGTH_SHORT).show();}
                else
                {Toast.makeText(this,"No items",Toast.LENGTH_SHORT).show();}
                return true;

            case R.id.about:
                  Intent intent = new Intent(MainActivity.this,About.class);
                  startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
