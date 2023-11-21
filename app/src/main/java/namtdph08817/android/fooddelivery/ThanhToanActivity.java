package namtdph08817.android.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import namtdph08817.android.fooddelivery.classs.SessionManager;
import namtdph08817.android.fooddelivery.model.Cart2;

public class ThanhToanActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private RecyclerView recyclerView;
    private FrameLayout btn_address,btn_choose_thanhToan;
    private RelativeLayout view_viTien;
    private Button btn_Dat_Hang;
    private TextView tv_address, tv_pt_thanhToan, tv_soDuHT, tv_tongDonHang, tv_soDuMoi,tv_Total_Price, tv_msg, tv_title;
    private boolean tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        sessionManager = new SessionManager(this);
        anhxa();

        //set dia chi
        if (sessionManager.getAddress().equals("")){
            tv_address.setText("Thêm địa chỉ giao hàng");
        }else {
            tv_address.setText(sessionManager.getAddress());
        }
        btn_address.setOnClickListener(view -> {
            if (sessionManager.getAddress().equals("")){
                startActivity(new Intent(ThanhToanActivity.this, InformationActivity.class));
            }
        });
        //an vi, set total price
        setVisibilityVi(false);
        tv_Total_Price.setText(Cart2.instance.getTotalPrice()+" đ");

        //click option thanh toan
        tag= true;
        btn_choose_thanhToan.setOnClickListener(view -> {
            openDialogBottom();
        });
        //back press
        findViewById(R.id.img_backpress_thanhtoan).setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void anhxa() {
        tv_address = findViewById(R.id.tv_address_thanhToan);
        tv_pt_thanhToan = findViewById(R.id.tv_phuongThuc_thanhToan);
        tv_soDuHT = findViewById(R.id.tv_soDu_thanhToan);
        tv_tongDonHang = findViewById(R.id.tv_soTienPhai_thanhToan);
        tv_soDuMoi = findViewById(R.id.tv_finalMoney_thanhToan);
        tv_Total_Price = findViewById(R.id.tv_price_thanhToan);
        tv_msg = findViewById(R.id.tv_msg_thanhToan);
        btn_address = findViewById(R.id.id_address_thanhToan);
        btn_choose_thanhToan = findViewById(R.id.btn_option_thanhToan);
        btn_Dat_Hang = findViewById(R.id.btn_datHang);
        recyclerView = findViewById(R.id.id_recycle_thanhToan);
        tv_title = findViewById(R.id.tv_title_money_thanhToan);
        view_viTien = findViewById(R.id.id_yourMoney_thanhToan);
    }
    private void setVisibilityVi(boolean visible){
        if (visible){
            int totalPrice = Cart2.getInstance().getTotalPrice();
            tv_title.setVisibility(View.VISIBLE);
            view_viTien.setVisibility(View.VISIBLE);
            tv_soDuHT.setText(sessionManager.getMoney() +" đ");
            tv_tongDonHang.setText(totalPrice+" đ");
            tv_soDuMoi.setText(sessionManager.getMoney() - totalPrice +" đ" );
            if (sessionManager.getMoney() < totalPrice){
                tv_msg.setVisibility(View.VISIBLE);
            }else {
                tv_msg.setVisibility(View.INVISIBLE);
            }
        }else {
            tv_title.setVisibility(View.INVISIBLE);
            view_viTien.setVisibility(View.INVISIBLE);
            tv_msg.setVisibility(View.INVISIBLE);
        }
    }

    private void openDialogBottom(){
        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
//        bottomSheetDialog.setCancelable(false);//tat cancel
        RadioGroup radioGroup = view.findViewById(R.id.rd_group_thanhToan);
        RadioButton btn_cash = view.findViewById(R.id.rd_cash);
        RadioButton btn_wallet = view.findViewById(R.id.rd_wallet);
        if (tag){
            btn_cash.setChecked(true);
        }else {
            btn_wallet.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rd_cash:
                        setVisibilityVi(false);
                        tv_pt_thanhToan.setText("Thanh toán bằng tiền mặt");
                        tag = true;
                        dialog.dismiss();
                        break;
                    case R.id.rd_wallet:
                        setVisibilityVi(true);
                        tv_pt_thanhToan.setText("Thanh toán bằng tài khoản ví");
                        tag = false;
                        dialog.dismiss();
                        break;
                }
            }
        });
    }
}