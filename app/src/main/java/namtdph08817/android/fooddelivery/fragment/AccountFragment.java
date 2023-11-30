package namtdph08817.android.fooddelivery.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import namtdph08817.android.fooddelivery.InformationActivity;
import namtdph08817.android.fooddelivery.LoginActivity;
import namtdph08817.android.fooddelivery.QLDH_Activity;
import namtdph08817.android.fooddelivery.QLDonNapActivity;
import namtdph08817.android.fooddelivery.QLFoodTypeActivity;
import namtdph08817.android.fooddelivery.QLFoodsActivity;
import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.ViMoneyActivity;
import namtdph08817.android.fooddelivery.classs.APIClass;
import namtdph08817.android.fooddelivery.classs.SessionManager;
import namtdph08817.android.fooddelivery.model.Cart2;

public class AccountFragment extends Fragment {
    private ImageView img;
    private TextView tv_name, tv_email;
    private SessionManager sessionManager;
    private CardView btn_your_wallet, btn_ql_donNap,btn_ql_food, btn_ql_foodType;
    public AccountFragment() {
        // Required empty public constructor
    }
    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img = view.findViewById(R.id.img_avatar);
        tv_name = view.findViewById(R.id.tv_nameUser_account);
        tv_email = view.findViewById(R.id.tv_email_account);
        btn_your_wallet = view.findViewById(R.id.id_your_wallet);
        btn_ql_donNap = view.findViewById(R.id.id_qld_napTien);
        btn_ql_food = view.findViewById(R.id.id_qlfood);
        btn_ql_foodType = view.findViewById(R.id.id_ql_food_type);

        //khoitao
        sessionManager = new SessionManager(getActivity());

        //set info user
        setInfoUser();
        //set visibility
        setUIPhanQuyen();

        //ql loai thuc an
        btn_ql_foodType.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), QLFoodTypeActivity.class));
        });
        // ql mon an
        btn_ql_food.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), QLFoodsActivity.class));
        });
        //ql don nap
        btn_ql_donNap.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), QLDonNapActivity.class));
        });

        // ql don hang
        view.findViewById(R.id.id_qldh).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), QLDH_Activity.class));
        });

        // vi cua ban
        btn_your_wallet.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ViMoneyActivity.class));
        });

        // thong tin ca nhan
        view.findViewById(R.id.id_thong_tin_ca_nhan).setOnClickListener(v ->{
            startActivity(new Intent(getActivity(), InformationActivity.class));
        });

        //dang xuat
        view.findViewById(R.id.id_logout).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            sessionManager.logoutUser();
            Cart2.getInstance().clear();
            getActivity().finish();
        });
    }

    private void setUIPhanQuyen() {
        if (sessionManager.getVaiTro()==0){
            btn_your_wallet.setVisibility(View.GONE);
        }else {
            btn_ql_foodType.setVisibility(View.GONE);
            btn_ql_food.setVisibility(View.GONE);
            btn_ql_donNap.setVisibility(View.GONE);
        }
    }

    private void setInfoUser() {
        if (!sessionManager.getAvatar().equals("")){
            String url = APIClass.URL+"uploads/"+sessionManager.getAvatar();
            Glide.with(getActivity()).load(url).placeholder(R.drawable.img_default_user).into(img);
        }
        tv_name.setText(sessionManager.getFullName());
        tv_email.setText(sessionManager.getEmail());
    }

    @Override
    public void onResume() {
        super.onResume();
        setInfoUser();
    }
}