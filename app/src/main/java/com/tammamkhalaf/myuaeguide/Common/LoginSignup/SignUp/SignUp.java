package com.tammamkhalaf.myuaeguide.Common.LoginSignup.SignUp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tammamkhalaf.myuaeguide.Common.LoginSignup.Login;
import com.tammamkhalaf.myuaeguide.R;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tammamkhalaf.myuaeguide.R.string.Empty_Field;

public class SignUp extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText, slideText;

    TextInputLayout fullName,username,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up);

        //Hooks for animation
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

    }

    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
            return;
        }

        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);

        intent.putExtra("fullName", Objects.requireNonNull(fullName.getEditText()).getText().toString().trim());
        intent.putExtra("username", Objects.requireNonNull(username.getEditText()).getText().toString().trim());
        intent.putExtra("email", Objects.requireNonNull(email.getEditText()).getText().toString().trim());
        intent.putExtra("password", Objects.requireNonNull(password.getEditText()).getText().toString().trim());

        //Add Shared Animation
        Pair[] pairs = new Pair[5];
        pairs[0] = new Pair(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair(next, "transition_next_btn");
        pairs[2] = new Pair(login, "transition_login_btn");
        pairs[3] = new Pair(titleText, "transition_title_text");
        pairs[4] = new Pair(slideText, "transition_slide_text");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    private boolean validateFullName() {
        String val = Objects.requireNonNull(fullName.getEditText()).getText().toString().trim();
        if (val.isEmpty()) {
            fullName.setError(getString(Empty_Field));
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = Objects.requireNonNull(username.getEditText()).getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}z";

        if (val.isEmpty()) {
            username.setError(getString(Empty_Field));
            return false;
        } else if (val.length() > 20) {
            username.setError(getString(R.string.UserNameLarge));
            return false;
        } else if (val.contains(" ")) {//!val.matches(checkspaces)
            username.setError(getString(R.string.NoWhiteSpaces));
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError(getString(Empty_Field));
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError(getString(R.string.InvalidEmail));
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
//        String val = password.getEditText().getText().toString().trim();
//        String checkPassword = "^" +
//                //"(?=.*[0-9])" +         //at least 1 digit
//                //"(?=.*[a-z])" +         //at least 1 lower case letter
//                //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=S+$)" ;//+           //no white spaces
//                //".{4,}" +               //at least 4 characters
//                //"$";
//
//        if (val.isEmpty()) {
//            password.setError("Field can not be empty");
//            return false;
//        } else if (!(val.matches(checkPassword))) {
//            password.setError("Password should contain 4 characters!");
//            return false;
//        } else {
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
        if(Objects.requireNonNull(password.getEditText()).getText().toString().trim().length()<8 && !isValidPassword(password.getEditText().getText().toString().trim())){
            password.setError(getString(R.string.PasswordStrenght));
            return false;
        }else{
            System.out.println("Valid");
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }


    //*****************************************************************
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}