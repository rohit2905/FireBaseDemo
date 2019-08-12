package com.example.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(Dashboard.this,"Action Cannot Be Performed",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(Dashboard.this, MainActivity.class));
        Toast.makeText(this, "Login again to continue...", Toast.LENGTH_SHORT).show();
    }
    public void logout(View view) {
        Intent intent=new Intent(Dashboard.this,MainActivity.class);
        AlertDialog.Builder builder=new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("Are you sure you want to Logout?");
        builder.setIcon(R.drawable.logout1);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Dashboard.this,MainActivity.class));
                finishAndRemoveTask();

                Toast.makeText(Dashboard.this,"Logout Successfull",Toast.LENGTH_SHORT).show();
                finish();

            }


        });



        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
