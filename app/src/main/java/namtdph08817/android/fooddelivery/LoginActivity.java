package namtdph08817.android.fooddelivery;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_user,ed_pass;
    private CheckBox chb_remember;
    private FrameLayout btn_login;
    private TextView txt_sign_up;
    private ImageView btn_backpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //anh xa
        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_password);
        chb_remember = findViewById(R.id.ckb_remember);
        btn_login = findViewById(R.id.btn_signin);
        txt_sign_up = findViewById(R.id.txt_signup);
        btn_backpress = findViewById(R.id.img_backpress);

        //click login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

        //click dang ky
        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        //click btn back
        btn_backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //end
    }

}