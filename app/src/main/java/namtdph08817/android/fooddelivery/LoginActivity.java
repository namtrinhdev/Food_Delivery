package namtdph08817.android.fooddelivery;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import namtdph08817.android.fooddelivery.classs.APIClass;
import namtdph08817.android.fooddelivery.classs.SessionManager;
import namtdph08817.android.fooddelivery.interfaces.UserInterface;
import namtdph08817.android.fooddelivery.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_user,ed_pass;
    private CheckBox chb_remember;
    private FrameLayout btn_login;
    private TextView txt_sign_up;
    private ImageView btn_backpress;
    private SessionManager sessionManager;
    private UserInterface userInterface;
    private ProgressDialog progressDialog;
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
        sessionManager = new SessionManager(this);
        checkRemember();
        //tao client retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClass.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userInterface = retrofit.create(UserInterface.class);

        //click login
        progressDialog = new ProgressDialog(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Login...");
                progressDialog.show();
                Call<List<Users>> call = userInterface.getAllUser();
                call.enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        if (response.isSuccessful()){
                            List<Users> mList = response.body();
                            for (Users user : mList){
                                if (checkLogin(user.getEmail(),user.getPasswd())){
                                    if (!chb_remember.isChecked()){
                                        sessionManager.setIsRemember(false);
                                    }
                                    sessionManager.setIsRemember(true);
                                    sessionManager.createLoginSession(user);
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(LoginActivity.this, "Có lỗi xảy ra, vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Lỗi kết nối server, vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        Log.e("onFailure", t.toString());
                    }
                });

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

    private boolean checkLogin(String email, String password){
        if (ed_user.getText().toString().trim().equals(email) && ed_pass.getText().toString().trim().equals(password)){
            return true;
        }
        return false;
    }
    private void checkRemember(){
        if(sessionManager.isRemember()){
            chb_remember.setChecked(true);
            ed_user.setText(sessionManager.getEmail());
            ed_pass.setText(sessionManager.getToken());
        }else {
            chb_remember.setChecked(false);
            ed_user.setText("");
            ed_pass.setText("");
        }
    }
}