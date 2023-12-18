package namtdph08817.android.fooddelivery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import namtdph08817.android.fooddelivery.adapter.Adapter_RecyclerView_FoodType2;
import namtdph08817.android.fooddelivery.adapter.Adapter_RecyclerView_QLFood;
import namtdph08817.android.fooddelivery.classs.RetrofitClientAPI;
import namtdph08817.android.fooddelivery.classs.SessionManager;
import namtdph08817.android.fooddelivery.interfaces.CURD_Interface;
import namtdph08817.android.fooddelivery.interfaces.FoodType_Interface;
import namtdph08817.android.fooddelivery.interfaces.Foods_Interface;
import namtdph08817.android.fooddelivery.interfaces.Item_Dialog;
import namtdph08817.android.fooddelivery.model.FoodType;
import namtdph08817.android.fooddelivery.model.Foods;
import namtdph08817.android.fooddelivery.model.Messages;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLFoodsActivity extends AppCompatActivity {
    private Adapter_RecyclerView_QLFood adapter;
    private Adapter_RecyclerView_FoodType2 foodTypeAdapter;
    private RecyclerView recyclerView;
    private SessionManager sessionManager;
    private Foods_Interface anInterface;
    private FoodType_Interface foodTypeInterface;
    private FloatingActionButton btnAdd;
    private ImageButton btnFilter;
    private static final String TAG = "FoodManagement";
    private static final int REQUEST_CODE_CHOOSE_IMAGE = 1;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 2;
    private String imagePath;
    private Uri selectedImageUri;
    private TextView tv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_foods);
        recyclerView = findViewById(R.id.id_recycle_ql_foods);
        btnAdd = findViewById(R.id.id_floatingbtn_add_ql_foods);
        btnFilter = findViewById(R.id.btnFilter_ql_foods);
        //khoi tao
        anInterface = RetrofitClientAPI.getRetrofitInstance().create(Foods_Interface.class);
        foodTypeInterface = RetrofitClientAPI.getRetrofitInstance().create(FoodType_Interface.class);
        sessionManager = new SessionManager(this);
        adapter = new Adapter_RecyclerView_QLFood(this, new CURD_Interface() {
            @Override
            public void updateData(Foods foods) {
                openDialogUpdate(foods);
            }

            @Override
            public void updateFoodType(FoodType foodType) {

            }

            @Override
            public void deleteData(String id) {
                openDialogDelete(id);
            }
        });
        loadData();

        btnFilter.setOnClickListener(view -> {
            openDialogBottom();
        });

        btnAdd.setOnClickListener(view -> {
            openDialogAddNew();
        });
    }

    private void openDialogDelete(String id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(QLFoodsActivity.this);
        dialog.setMessage("Bạn có muốn xóa ?");
        dialog.setNegativeButton("Xóa", (dialogInterface, i) -> {
            Call<Messages> call = anInterface.deleteFood(id);
            call.enqueue(new Callback<Messages>() {
                @Override
                public void onResponse(Call<Messages> call, Response<Messages> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(QLFoodsActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                        loadData();
                    }else {
                        Log.e(TAG, "onFailure");
                    }
                }

                @Override
                public void onFailure(Call<Messages> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        });
        dialog.setPositiveButton("Hủy", (dialogInterface, i) -> {

        });
        dialog.show();
    }

    private void openDialogUpdate(Foods foods) {
        Dialog dialog = new Dialog(QLFoodsActivity.this);
        dialog.setContentView(R.layout.dialog_update_food);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        EditText ed_name, ed_price, ed_quantity, ed_mota;
        TextView tv_foodtype;
        LinearLayout btn_pickImg, btn_pick_FoodType;
        Button btn_save;
        RecyclerView rcv_dialogupdate = dialog.findViewById(R.id.id_recycle_dialog_food);
        ed_name = dialog.findViewById(R.id.ed_nameFood_dialog_update);
        ed_price = dialog.findViewById(R.id.ed_price_dialog_update);
        ed_quantity = dialog.findViewById(R.id.ed_quantity_dialog_update);
        ed_mota = dialog.findViewById(R.id.ed_mota_dialog_update);
        tv_img = dialog.findViewById(R.id.tv_img_food_dialog);
        tv_foodtype = dialog.findViewById(R.id.tv_foodType_foodUpdate_dialog);
        btn_pickImg = dialog.findViewById(R.id.btn_pick_img_updateFood_dialog);
        btn_pick_FoodType = dialog.findViewById(R.id.btn_pickFoodType_updateFood_dialog);
        btn_save = dialog.findViewById(R.id.btn_save_editFood_dialog);
        //setText
        ed_name.setText(foods.getNameFood());
        ed_mota.setText(foods.getMota());
        ed_price.setText(String.valueOf(foods.getPrice()));
        ed_quantity.setText(String.valueOf(foods.getQuantity()));
        tv_img.setText(foods.getImage());
        StringBuilder result = new StringBuilder();
        for (int i= 0; i<foods.getFoodType().size() ; i++){
            result.append(foods.getFoodType().get(i).getTenLoai()+ ", ");
        }
        tv_foodtype.setText(result.toString());

        //click chon anh
        btn_pickImg.setOnClickListener(view -> {
            checkStoragePermissionAndOpenImageChooser();
            tv_img.setText(imagePath);
        });

        //click choose foodtype
        List<FoodType> list = new ArrayList<>();
        btn_pick_FoodType.setOnClickListener(view -> {
            rcv_dialogupdate.setVisibility(View.VISIBLE);
            foodTypeAdapter = new Adapter_RecyclerView_FoodType2(QLFoodsActivity.this, new Item_Dialog() {
                @Override
                public void onClickItem(FoodType foodType) {
                    list.add(foodType);
                }
            });
            loadDataFoodType(rcv_dialogupdate);
        });
        //click save
        btn_save.setOnClickListener(view -> {
            File file ;
            RequestBody requestFile;
            MultipartBody.Part bodyimg;
            if (imagePath == null){
                Log.d("file","o");
                bodyimg = null;
            }else {
                file = new File(imagePath);
                requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                bodyimg = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
            }

            Foods model = new Foods();
            model.setNameFood(ed_name.getText().toString());
            model.setFoodType(list);
            model.setMota(ed_mota.getText().toString());
            model.setPrice(Integer.parseInt(ed_price.getText().toString()));
            model.setQuantity(Integer.parseInt(ed_quantity.getText().toString()));

            Gson gson = new Gson();
            RequestBody bodyFoods = RequestBody.create(MediaType.parse("application/json"),gson.toJson(model));

            Call<Messages> call = anInterface.putFood(foods.get_id(), bodyimg, bodyFoods);
            call.enqueue(new Callback<Messages>() {
                @Override
                public void onResponse(Call<Messages> call, Response<Messages> response) {
                    if (response.isSuccessful()){
                        Messages msg = response.body();
                        Toast.makeText(QLFoodsActivity.this, msg.getMsg(), Toast.LENGTH_SHORT).show();
                        loadData();
                        dialog.dismiss();
                    }else {
                        Log.e(TAG, "onFailure");
                    }
                }
                @Override
                public void onFailure(Call<Messages> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        });

        dialog.show();
    }
    private void openDialogAddNew() {
        Dialog dialog = new Dialog(QLFoodsActivity.this);
        dialog.setContentView(R.layout.dialog_update_food);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        EditText ed_name, ed_price, ed_quantity, ed_mota;
        TextView tv_foodtype, tv_title;
        LinearLayout btn_pickImg, btn_pick_FoodType;
        Button btn_save;
        RecyclerView rcv_dialogupdate = dialog.findViewById(R.id.id_recycle_dialog_food);
        ed_name = dialog.findViewById(R.id.ed_nameFood_dialog_update);
        ed_price = dialog.findViewById(R.id.ed_price_dialog_update);
        ed_quantity = dialog.findViewById(R.id.ed_quantity_dialog_update);
        ed_mota = dialog.findViewById(R.id.ed_mota_dialog_update);
        tv_img = dialog.findViewById(R.id.tv_img_food_dialog);
        tv_foodtype = dialog.findViewById(R.id.tv_foodType_foodUpdate_dialog);
        tv_title = dialog.findViewById(R.id.tv_title_dialog_foods);
        btn_pickImg = dialog.findViewById(R.id.btn_pick_img_updateFood_dialog);
        btn_pick_FoodType = dialog.findViewById(R.id.btn_pickFoodType_updateFood_dialog);
        btn_save = dialog.findViewById(R.id.btn_save_editFood_dialog);

        btn_save.setText("Thêm mới");
        tv_title.setText("Thêm món ăn");

        //click chon anh
        btn_pickImg.setOnClickListener(view -> {
            checkStoragePermissionAndOpenImageChooser();
            tv_img.setText(imagePath);
        });

        //click choose foodtype
        List<FoodType> list = new ArrayList<>();
        btn_pick_FoodType.setOnClickListener(view -> {
            rcv_dialogupdate.setVisibility(View.VISIBLE);
            foodTypeAdapter = new Adapter_RecyclerView_FoodType2(QLFoodsActivity.this, foodType -> list.add(foodType));
            loadDataFoodType(rcv_dialogupdate);
        });
        //click save
        btn_save.setOnClickListener(view -> {

            if (imagePath == null){
                Toast.makeText(this, "Vui lòng chọn ảnh món ăn", Toast.LENGTH_SHORT).show();
            }else if (ed_name.getText().toString().trim().isEmpty()  ||
                    ed_mota.getText().toString().trim().isEmpty() ||
                    ed_price.getText().toString().trim().isEmpty() ||
                    ed_quantity.getText().toString().trim().isEmpty() ||
                    list.isEmpty()
            ){
                Toast.makeText(this, "không được để trống thông tin", Toast.LENGTH_SHORT).show();
            }
            else{
                File file = new File(imagePath);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part bodyimg = MultipartBody.Part.createFormData("img", file.getName(), requestFile);

                Foods model = new Foods();
                model.setNameFood(ed_name.getText().toString());
                model.setFoodType(list);
                model.setMota(ed_mota.getText().toString());
                model.setPrice(Integer.parseInt(ed_price.getText().toString()));
                model.setQuantity(Integer.parseInt(ed_quantity.getText().toString()));
                model.setDanhGia(5);
                Gson gson = new Gson();
                RequestBody bodyFoods = RequestBody.create(MediaType.parse("application/json"),gson.toJson(model));

                Call<Messages> call = anInterface.postFood(bodyimg, bodyFoods);
                call.enqueue(new Callback<Messages>() {
                    @Override
                    public void onResponse(Call<Messages> call, Response<Messages> response) {
                        if (response.isSuccessful()){
                            Messages msg = response.body();
                            Toast.makeText(QLFoodsActivity.this,msg.getMsg() , Toast.LENGTH_SHORT).show();
                            loadData();
                            dialog.dismiss();
                        }
                        else {
                            Log.e(TAG, "onResponse: fail" );
                        }
                    }

                    @Override
                    public void onFailure(Call<Messages> call, Throwable t) {
                        Log.e(TAG, "onFailure: ",t );
                    }
                });
            }


        });

        dialog.show();
    }

    private void loadDataFoodType(RecyclerView rcView) {
        Call<List<FoodType>> call = foodTypeInterface.getAll();
        call.enqueue(new Callback<List<FoodType>>() {
            @Override
            public void onResponse(Call<List<FoodType>> call, Response<List<FoodType>> response) {
                if (response.isSuccessful()){
                    List<FoodType> list = response.body();
                    foodTypeAdapter.setData(list);
                    rcView.setLayoutManager(new GridLayoutManager(QLFoodsActivity.this, 3));
                    rcView.setAdapter(foodTypeAdapter);
                }else {
                    Log.e(TAG, "onResponse fail" );
                }
            }
            @Override
            public void onFailure(Call<List<FoodType>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void loadData() {
        Call<List<Foods>> call = anInterface.getAll();
        call.enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                if (response.isSuccessful()){
                    List<Foods> list = response.body();
                    adapter.setData(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QLFoodsActivity.this, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);
                }else {
                    Log.e(TAG, "onResponse: ");
                }
            }
            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void openDialogBottom() {
        View view = getLayoutInflater().inflate(R.layout.dialog_filter_food, null);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
//        bottomSheetDialog.setCancelable(false);//tat cancel
        RecyclerView rcrView = dialog.findViewById(R.id.id_recycle_dialog_foodType);
        foodTypeAdapter = new Adapter_RecyclerView_FoodType2(QLFoodsActivity.this, foodType -> {
            loadDataFilter(foodType.get_id());
            dialog.dismiss();
        });
        Call<List<FoodType>> call = foodTypeInterface.getAll();
        call.enqueue(new Callback<List<FoodType>>() {
            @Override
            public void onResponse(Call<List<FoodType>> call, Response<List<FoodType>> response) {
                if (response.isSuccessful()){
                    List<FoodType> list = response.body();
                    foodTypeAdapter.setData(list);
                    rcrView.setLayoutManager(new GridLayoutManager(QLFoodsActivity.this, 3));
                    rcrView.setAdapter(foodTypeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<FoodType>> call, Throwable t) {

            }
        });
    }

    private void loadDataFilter(String id) {
        Call<List<Foods>> call = anInterface.getById(id);
        call.enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                if (response.isSuccessful()){
                    List<Foods> list = response.body();
                    adapter.setData(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QLFoodsActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {

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