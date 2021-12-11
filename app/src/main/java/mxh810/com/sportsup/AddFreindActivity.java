package mxh810.com.sportsup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddFreindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_freind);



    }
}

/**
 * Sample code add item.
 */
//    public static final String EXTRA_TITLE =
//            "edu.neu.madcourse.myapplication.EXTRA_TITLE";
//    public static final String EXTRA_URL =
//            "edu.neu.madcourse.myapplication.EXTRA_URL";
//
//    private EditText editTextTile;
//    private EditText editTextURL;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_item);
//
//        editTextTile = findViewById(R.id.edit_text_tile);
//        editTextURL = findViewById(R.id.edit_text_url);
//
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
//        setTitle("Add Item");
//    }
//
//    private void saveNote() {
//        String title = editTextTile.getText().toString();
//        String url = editTextURL.getText().toString();
//
//        if(title.isEmpty() || url.isEmpty() || !isValidUrl(url)) {
//            Snackbar.make(findViewById(R.id.linear_layout),
//                    "Invalid Input, please re-enter!", Snackbar.LENGTH_LONG).show();
//            return;
//        }
//
//        Intent data = new Intent();
//        data.putExtra(EXTRA_TITLE,title);
//        data.putExtra(EXTRA_URL,url);
//        setResult(RESULT_OK,data);
//        finish();
//    }
//
//    private boolean isValidUrl(String url) {
//        Pattern p = Patterns.WEB_URL;
//        Matcher m = p.matcher(url.toLowerCase());
//        return m.matches();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.add_item_menu,menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.save_item:
//                saveNote();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }