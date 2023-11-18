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

import com.bumptech.glide.Glide;

import namtdph08817.android.fooddelivery.LoginActivity;
import namtdph08817.android.fooddelivery.QLDH_Activity;
import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.ViMoneyActivity;
import namtdph08817.android.fooddelivery.classs.APIClass;
import namtdph08817.android.fooddelivery.classs.SessionManager;

public class AccountFragment extends Fragment {
    private ImageView img;
    private SessionManager sessionManager;
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
        sessionManager = new SessionManager(getActivity());
        //set image avatar
        if (!sessionManager.getAvatar().equals("")){
            String url = APIClass.URL+"uploads/"+sessionManager.getAvatar();
            Glide.with(getActivity()).load(url).placeholder(R.drawable.logo).into(img);
        }

        view.findViewById(R.id.id_qldh).setOnClickListener(v -> {
                startActivity(new Intent(getActivity(), QLDH_Activity.class));
        });

        view.findViewById(R.id.id_your_wallet).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ViMoneyActivity.class));
        });

        view.findViewById(R.id.id_thong_tin_ca_nhan).setOnClickListener(v ->{
//            startActivity(new Intent(getActivity(), InformationActivity.class));
        });

        view.findViewById(R.id.id_logout).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            sessionManager.logoutUser();
            getActivity().finish();
        });
    }
}