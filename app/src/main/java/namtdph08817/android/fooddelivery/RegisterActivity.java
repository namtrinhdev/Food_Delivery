package namtdph08817.android.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import namtdph08817.android.fooddelivery.classs.APIClass;
import namtdph08817.android.fooddelivery.interfaces.UserInterface;
import namtdph08817.android.fooddelivery.model.Messages;
import namtdph08817.android.fooddelivery.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private EditText ed_name, ed_email, ed_pass, ed_repass;
    private FrameLayout btn_register;
    private TextView txt_login;
    private ImageView btn_backpress;
    private UserInterface mInterface;
    private ProgressDialog dialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //anh xa
        ed_name = findViewById(R.id.ed_fullname_register);
        ed_email = findViewById(R.id.ed_email_register);
        ed_pass = findViewById(R.id.ed_password_register);
        ed_repass = findViewById(R.id.ed_confirmPass_register);
        btn_register = findViewById(R.id.btn_signup);
        txt_login = findViewById(R.id.txt_login);
        btn_backpress = findViewById(R.id.img_backpress1);
        dialog = new ProgressDialog(this);

        //tạo retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIClass.URL).addConverterFactory(GsonConverterFactory.create()).build();
        mInterface = retrofit.create(UserInterface.class);


        //click sign up
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Loading...");
                dialog.show();
                boolean validate = true;
                if (ed_name.getText().toString().trim().isEmpty()) {
                    ed_name.setError("Vui lòng nhập thông tin họ tên đầy đủ");
                    validate = false;
                }
                if (ed_email.getText().toString().trim().isEmpty()) {
                    ed_email.setError("Vui lòng nhập email của bạn 'abc@gmail.com'");
                    validate = false;
                }
                if (ed_pass.getText().toString().trim().isEmpty()) {
                    ed_pass.setError("Vui lòng nhập mật khẩu");
                    validate = false;
                }
                if (ed_repass.getText().toString().trim().isEmpty()) {
                    ed_repass.setError("Vui lòng nhập mật khẩu");
                    validate = false;
                }
                if (ed_name.getText().toString().trim().length() < 8 || ed_name.getText().toString().trim().length() > 16) {
                    ed_name.setError("Họ tên từ 8 đến 16 ký tự");
                    validate = false;
                }
                if (ed_pass.getText().toString().trim().length() < 8 || ed_pass.getText().toString().trim().length() > 16) {
                    ed_pass.setError("Mật khẩu tối thiếu 8 đến 16 ký tự");
                    validate = false;
                }
                if (ed_repass.getText().toString().trim().equals(ed_pass.getText().toString().trim())) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không trùng khớp", Toast.LENGTH_SHORT).show();
                    validate = false;
                }
                if (validate) {
                    Users users = new Users();
                    users.setFullname(ed_name.getText().toString());
                    users.setEmail(ed_email.getText().toString());
                    users.setSdt("");
                    users.setPasswd(ed_pass.getText().toString());
                    users.setDiaChi("");
                    users.setVaitro(1);
                    users.setAvatar("");
                    users.setSoTien(0);
                    Call<Messages> call = mInterface.registerUser(users);
                    call.enqueue(new Callback<Messages>() {
                        @Override
                        public void onResponse(Call<Messages> call, Response<Messages> response) {
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                            if (response.isSuccessful()) {
                                Messages msg = response.body();
                                Toast.makeText(RegisterActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        }
                        @Override
                        public void onFailure(Call<Messages> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Lỗi kết nối, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                            Log.e("onFailure register", t.toString());
                            if (dialog.isShowing()){
                                dialog.dismiss();
                            }
                        }
                    });
                }
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
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