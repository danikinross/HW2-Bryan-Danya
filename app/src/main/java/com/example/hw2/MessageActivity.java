package com.example.hw2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "MessageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Message message = (Message) bundle.getSerializable("message");
            if (message != null) {
                TextView name = findViewById(R.id.name);
                TextView text = findViewById(R.id.text);
                ImageView avatar = findViewById(R.id.avatar);

                // Display message data
                name.setText(message.Name);
                text.setText(message.Text);
                Glide.with(this).load(message.Avatar).into(avatar);
            } else {
                Log.e(TAG, "Message is null");
                finish(); // Close the activity if message is null
            }
        } else {
            Log.e(TAG, "Bundle is null");
            finish(); // Close the activity if bundle is null
        }
    }
}