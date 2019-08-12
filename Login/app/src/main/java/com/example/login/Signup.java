package com.example.login;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Signup extends AppCompatActivity  {

    private Button sp;
    private EditText passwordTV,emailTV;
    private FirebaseAuth mAuth;
    String email, password;

    ProgressBar progressBar;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("SIGN UP");
        mAuth=FirebaseAuth.getInstance();
        emailTV=(EditText) findViewById(R.id.edt1);
        passwordTV=(EditText)findViewById(R.id.pdt1);
        progressBar=findViewById(R.id.p);
        sp=(Button)findViewById(R.id.reg1);
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     regis();

              }
        });
    }
    @SuppressLint("WrongViewCast")
    public void regis() {
        progressBar.setVisibility(View.VISIBLE);


        email = emailTV.getText().toString().trim();
        password = passwordTV.getText().toString();


        if (TextUtils.isEmpty(email)) {

            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter a Valid E-Mail", Toast.LENGTH_SHORT).show();
             progressBar.setVisibility(View.GONE);
            return;
        }




         if(TextUtils.isEmpty(password)) {

            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);

            return;

        }
        if(password.length()<6)
        {
            Toast.makeText(Signup.this, "Password must contain atleast 6 characters", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);

            return;

        }


        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {


                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);



                            Intent intent = new Intent(Signup.this, MainActivity.class);

                            startActivity(intent);

                        }


                        else{
                                 checkmail();
                                 Toast.makeText(getApplicationContext(), "Registration Failed!!", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                            return;

                        }
                       //checkmail();



                    }

                });
    }
   public void checkmail()
    {
        mAuth.fetchSignInMethodsForEmail(emailTV.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = !task.getResult().getSignInMethods().isEmpty();
              if(!check)
                {
                    //Toast.makeText(Signup.this, "valid mail", Toast.LENGTH_SHORT).show();
                    return;
                   }
              else {
                  new AlertDialog.Builder(Signup.this)
                          .setMessage("E-mail already exists")
                          .setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          return;
                      }
                  })
                          .show();


                  return;
              }


            }
        });



       return; }


}
