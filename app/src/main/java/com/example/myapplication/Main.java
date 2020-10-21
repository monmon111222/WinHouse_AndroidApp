package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Main extends AppCompatActivity {
    private NavigationView navigation_view;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private Button Guest_Surf_Button;
    private Button User_Singin_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Guest_Surf_Button = (Button)findViewById(R.id.Guest_Surf_Button);
        Guest_Surf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contants.uid="0";
                Intent i = new Intent(Main.this, Menu.class);
                startActivity(i);
            }
        });
        User_Singin_Button = (Button)findViewById(R.id.User_Singin_Button);
        User_Singin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main.this, SignIn.class);
                startActivity(i);
            }
        });


    }
}
