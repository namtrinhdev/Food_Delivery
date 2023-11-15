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

import namtdph08817.android.fooddelivery.QLDH_Activity;
import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.ViMoneyActivity;

public class AccountFragment extends Fragment {
    private CardView qldh;
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
        qldh = view.findViewById(R.id.id_qldh);

        qldh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QLDH_Activity.class));
            }
        });

        view.findViewById(R.id.id_your_wallet).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ViMoneyActivity.class));
        });

    }
}