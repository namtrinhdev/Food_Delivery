package namtdph08817.android.fooddelivery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.List;

import namtdph08817.android.fooddelivery.adapter.Adapter_RecyclerView_FoodType1;
import namtdph08817.android.fooddelivery.classs.RetrofitClientAPI;
import namtdph08817.android.fooddelivery.interfaces.CURD_Interface;
import namtdph08817.android.fooddelivery.interfaces.FoodType_Interface;
import namtdph08817.android.fooddelivery.model.FoodType;
import namtdph08817.android.fooddelivery.model.Foods;
import namtdph08817.android.fooddelivery.model.Messages;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLFoodTypeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter_RecyclerView_FoodType1 adapter;
    private FloatingActionButton btn_add;
    private FoodType_Interface anInterface;
    private static final String TAG = "FoodTypeManagement";
    private static final int REQUEST_CODE_CHOOSE_IMAGE = 1;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 2;
    private String imagePath;
    private Uri selectedImageUri;
    private TextView tv_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_food_type);
        recyclerView = findViewById(R.id.id_recycle_ql_foodType);
        btn_add = findViewById(R.id.id_floatingbtn_add_ql_foodType);

        //khoi tao
        anInterface = RetrofitClientAPI.getRetrofitInstance().create(FoodType_Interface.class);
        adapter = new Adapter_RecyclerView_FoodType1(this, new CURD_Interface() {
            @Override
            public void updateData(Foods foods) {

            }

            @Override
            public void updateFoodType(FoodType foodType) {
                openDialogUpdate(foodType);
            }

            @Override
            public void deleteData(String id) {
                openDialogDelete(id);
            }
        });

        loadData();

        btn_add.setOnClickListener(view -> {
            openDialogAdd();
        });
    }

    private void openDialogDelete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QLFoodTypeActivity.this);
        builder.setMessage("Bạn có muốn xóa ?");

        builder.setPositiveButton("Xóa", (dialogInterface, i) -> {
            Call<Messages> call = anInterface.deleteFoodType(id);
            call.enqueue(new Callback<Messages>() {
                @Override
                public void onResponse(Call<Messages> call, Response<Messages> response) {
                    if (response.isSuccessful()){
                        Messages msg = response.body();
                        Toast.makeText(QLFoodTypeActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                        loadData();
                    }else {
                        Log.e(TAG, "onResponse:fail" );
                    }
                }
                @Override
                public void onFailure(Call<Messages> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        });

        builder.setNegativeButton("Hủy", (dialogInterface, i) ->{

        });

        builder.show();
    }

    private void openDialogUpdate(FoodType foodType) {
        Dialog dialog = new Dialog(QLFoodTypeActivity.this);
        dialog.setContentView(R.layout.dialog_food_type);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();

        LinearLayout btnPickImg = dialog.findViewById(R.id.btn_pick_img_foodType_dialog);
        EditText ed_name = dialog.findViewById(R.id.ed_name_foodType_dialog);
        Button btn_save = dialog.findViewById(R.id.btn_save_foodType_dialog);
        tv_img = dialog.findViewById(R.id.tv_img_foodType_dialog);

        ed_name.setText(foodType.getTenLoai());
        if (foodType.getIcon() == null){
            tv_img.setText("Chọn icon loại");
        }else {
            tv_img.setText(foodType.getIcon());
        }

        btnPickImg.setOnClickListener(view -> {
            checkStoragePermissionAndOpenImageChooser();
        });


        btn_save.setOnClickListener(view -> {
            if (ed_name.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Nhập tên loại", Toast.LENGTH_SHORT).show();
            }else {
                MultipartBody.Part imgBody;
                if (imagePath == null){
                    imgBody = null;
                }else {
                    File file = new File(imagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    imgBody = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
                }

                RequestBody tenloai = RequestBody.create(MediaType.parse("text/plain"), ed_name.getText().toString());
                Call<Messages> call = anInterface.putFoodType(foodType.get_id(),imgBody, tenloai);
                call.enqueue(new Callback<Messages>() {
                    @Override
                    public void onResponse(Call<Messages> call, Response<Messages> response) {
                        if (response.isSuccessful()){
                            Messages msg = response.body();
                            Toast.makeText(QLFoodTypeActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }else {
                            Log.e(TAG, "onResponse:fail" );
                        }
                    }
                    @Override
                    public void onFailure(Call<Messages> call, Throwable t) {
                        Log.e(TAG, "onFailure: ",t );
                    }
                });
            }
        });
    }
    private void openDialogAdd() {
        Dialog dialog = new Dialog(QLFoodTypeActivity.this);
        dialog.setContentView(R.layout.dialog_food_type);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();

        TextView tv_title = dialog.findViewById(R.id.tv_title_dialog_foodType);
        LinearLayout btnPickImg = dialog.findViewById(R.id.btn_pick_img_foodType_dialog);
        EditText ed_name = dialog.findViewById(R.id.ed_name_foodType_dialog);
        Button btn_save = dialog.findViewById(R.id.btn_save_foodType_dialog);
        tv_img = dialog.findViewById(R.id.tv_img_foodType_dialog);

        tv_title.setText("Thêm loại món");
        btn_save.setText("Thêm mới");

        btnPickImg.setOnClickListener(view -> {
            checkStoragePermissionAndOpenImageChooser();
        });


        btn_save.setOnClickListener(view -> {
            if (ed_name.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Nhập tên loại", Toast.LENGTH_SHORT).show();
            } else if (imagePath == null) {
                tv_img.setText("Chọn icon loại");
                Toast.makeText(this, "Chọn icon loại món ăn", Toast.LENGTH_SHORT).show();
            }else {
                File file = new File(imagePath);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part imgBody = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
                RequestBody tenloai = RequestBody.create(MediaType.parse("text/plain"), ed_name.getText().toString());
                Call<Messages> call = anInterface.postFoodType(imgBody, tenloai);
                call.enqueue(new Callback<Messages>() {
                    @Override
                    public void onResponse(Call<Messages> call, Response<Messages> response) {
                        if (response.isSuccessful()){
                            Messages msg = response.body();
                            Toast.makeText(QLFoodTypeActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }else {
                            Log.e(TAG, "onResponse:fail" );
                        }
                    }
                    @Override
                    public void onFailure(Call<Messages> call, Throwable t) {
                        Log.e(TAG, "onFailure: ",t );
                    }
                });
            }
        });
    }
    private void loadData() {
        Call<List<FoodType>> call = anInterface.getAll();
        call.enqueue(new Callback<List<FoodType>>() {
            @Override
            public void onResponse(Call<List<FoodType>> call, Response<List<FoodType>> response) {
                if (response.isSuccessful()){
                    List<FoodType> list = response.body();
                    adapter.setData(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QLFoodTypeActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                }else {
                    Log.e(TAG, "onResponse:fail" );
                }
            }

            @Override
            public void onFailure(Call<List<FoodType>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void checkStoragePermissionAndOpenImageChooser() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                // Nếu không có quyền, yêu cầu người dùng cấp quyền
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_REQUEST_CODE);
            } else {
                // Nếu đã có quyền, mở trình chọn ảnh
                openImageChooser();
            }
        } else {
            // Đối với các phiên bản Android dưới 6.0, không cần yêu cầu quyền động
            // Mở trình chọn ảnh
            openImageChooser();
        }
    }
    private void openImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), REQUEST_CODE_CHOOSE_IMAGE);
    }
    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(columnIndex);
        cursor.close();
        return imagePath;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Lấy đường dẫn của ảnh được chọn
            selectedImageUri = data.getData();
            imagePath = getPathFromUri(selectedImageUri);
            tv_img.setText(imagePath);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, mở trình chọn ảnh
                openImageChooser();
            } else {
                // Người dùng từ chối cấp quyền, có thể thông báo hoặc thực hiện các xử lý khác
            }
        }
    }
}