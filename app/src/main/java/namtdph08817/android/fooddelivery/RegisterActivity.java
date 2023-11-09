package namtdph08817.android.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText ed_user, ed_pass, ed_repass;
    private FrameLayout btn_register;
    private TextView txt_login;
    private ImageView btn_backpress;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //anh xa
        ed_user = findViewById(R.id.ed_user_register);
        ed_pass = findViewById(R.id.ed_password_register);
        ed_repass = findViewById(R.id.ed_confirmPass_register);
        btn_register = findViewById(R.id.btn_signup);
        txt_login = findViewById(R.id.txt_login);
        btn_backpress = findViewById(R.id.img_backpress1);
        //click sign up
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //click login
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        //click btn back
        btn_backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }//end
}