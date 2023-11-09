package namtdph08817.android.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HelloActivity extends AppCompatActivity {
    private Button btn_login,btn_register;
    private LinearLayout mhc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        //anhxa
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        mhc = findViewById(R.id.ln_manhinhchao);

        //man hinh chao
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mhc.setVisibility(View.INVISIBLE);
            }
        },2000);

        //click login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelloActivity.this,LoginActivity.class));
            }
        });

        //click sign up
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelloActivity.this,RegisterActivity.class));
            }
        });

    //end
    }
}