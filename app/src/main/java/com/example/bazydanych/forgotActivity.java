package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgotActivity extends AppCompatActivity {

    Button forgot_pswd;
    EditText first_name_n;
    EditText second_name_n;
    EditText ID_n;
    EditText new_pswd_n;
    EditText new_pswd_2_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        forgot_pswd = findViewById(R.id.Forgot_password);
        first_name_n = findViewById(R.id.Forgot_first);
        second_name_n = findViewById(R.id.Forgot_second);
        ID_n = findViewById(R.id.Forgot_ID);
        new_pswd_n = findViewById(R.id.Forgot_pswd);
        new_pswd_2_n = findViewById(R.id.Forgot_new_pswd);



        forgot_pswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = first_name_n.getText().toString();
                String second_name = second_name_n.getText().toString();
                String ID = ID_n.getText().toString();
                String new_pswd = new_pswd_n.getText().toString();
                String new_pswd_2 = new_pswd_2_n.getText().toString();

                if(!new_pswd.equals("") && !new_pswd_2.equals("") && !ID.equals("") && !second_name.equals("") && !first_name.equals("")) {
                    forgotBackground bg = new forgotBackground(forgotActivity.this);
                    bg.execute(first_name,second_name,ID,new_pswd,new_pswd_2);
                }
            }
        });
    }
}