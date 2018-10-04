package info.firozansari.android_intent_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import info.firozansari.android_intent_example.utils.Constant;

public class UserOverviewActivity extends AppCompatActivity {

    public static final int SUB_ACTIVITY_CREATE_USER = 10;

    private TextView textView1;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_overview);

        setUpView();

        handleIntent();

    }

    private void handleIntent() {
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } 
        }
    }

    private void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            textView1.setText(sharedText);
        }
    }


    private void setUpView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView1 = (TextView) findViewById(R.id.textView1);

        button = (Button) findViewById(R.id.create_user_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserOverviewActivity.this, CreateUserActivity.class);
                startActivityForResult(intent, SUB_ACTIVITY_CREATE_USER);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nothing here!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    // This is the callback for the started activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SUB_ACTIVITY_CREATE_USER && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String name = extras.getString(Constant.USER_NAME);
                textView1.setText(name);
            }
        }
    }

}
