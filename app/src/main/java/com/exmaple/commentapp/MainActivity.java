package com.exmaple.commentapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.button)
    Button button;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = this;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();

                if(name.length() == 0)
                {
                    Toast.makeText(context, "Please Enter User Name!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    setUserName(name);

                    Intent i = new Intent(context, FeedActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
