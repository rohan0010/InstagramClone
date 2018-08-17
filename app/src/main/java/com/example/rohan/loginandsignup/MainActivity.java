package com.example.rohan.loginandsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener {
    TextView textView;
    Button button;
    EditText editText;
    EditText editText2;
    public void intent()
    {
        Intent intent=new Intent(getApplicationContext(),useractivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

            boob(view);

        }

        return false;
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.textView)
            if(textView.getText().toString().equals("or,signup")) {
                button.setText("Sign up");
                textView.setText("or,LOGIN");
            }
            else {
                button.setText("LOGIN");
                textView.setText("or,signup");
            }
    else
        if (view.getId() == R.id.backgroundRelativeLayout || view.getId() == R.id.logoImageView) {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }}

    public void boob(View view)
    {
        if(editText.getText().toString().equals("")||(editText2.getText().toString()).equals(""))
        {
            Toast.makeText(MainActivity.this,"Enter both username and password",Toast.LENGTH_SHORT).show();
        }
         else
             if (textView.getText().toString().equals("or,signup"))
        {
            ParseUser.logInInBackground(editText.getText().toString(), editText2.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (user != null) {

                        Log.i("Login", "Successful");
                        intent();

                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        // Log.i("Login", "Failed: " + e.toString());

                    }

                }
            });
        }
        else{
                ParseUser user = new ParseUser();
        user.setUsername(editText.getText().toString());
        user.setPassword(editText2.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    Log.i("Sign Up", "Successful");
                    intent();

                } else {

                    //Log.i("Sign Up", "Failed"+e.toString());
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        setTitle("Instagram");

        RelativeLayout backgroundRelativeLayout = (RelativeLayout) findViewById(R.id.backgroundRelativeLayout);

        ImageView logoImageView = (ImageView) findViewById(R.id.logoImageView);
        textView.setOnClickListener(this);
        backgroundRelativeLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);
        editText2.setOnKeyListener(this);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            intent();
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());


}}
