package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    Button login,signup;
    EditText user,pass;
    String loguser,logpass;
    private FirebaseAuth mAuth;
    ProgressBar progressbar;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        login=findViewById(R.id.btn1);
        signup=findViewById(R.id.btn2);
        user=findViewById(R.id.et1);
        pass=findViewById(R.id.pt1);
        progressbar = findViewById(R.id.progressbar);
        mAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressbar.setVisibility(View.VISIBLE);

                loguser=user.getText().toString().trim();
                  logpass=pass.getText().toString().trim();


                if (TextUtils.isEmpty(loguser)) {

                    Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_SHORT).show();
                    //user.setError("Enter Username");
                    progressbar.setVisibility(View.GONE);

                    return;


                }

                if (TextUtils.isEmpty(logpass)) {

                    Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
                    //pass.setError("Enter Password");
                    progressbar.setVisibility(View.GONE);

                    return;

                }



                mAuth.signInWithEmailAndPassword(loguser, logpass)

                        .addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {

                            @Override

                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {


                                    Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();

                                    progressbar.setVisibility(View.GONE);



                                    Intent intent = new Intent(MainActivity.this, Dashboard.class);

                                    startActivity(intent);

                                }

                                else {

                                    Toast.makeText(getApplicationContext(), "Invalid email/password", Toast.LENGTH_SHORT).show();

                                    progressbar.setVisibility(View.GONE);

                                }

                            }

                        });

            }
        });


    }
    public void su(View v)
    {
        startActivity(new Intent(MainActivity.this,Signup.class));

    }
}
