package namtdph08817.android.fooddelivery.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import namtdph08817.android.fooddelivery.R;


public class DaXacNhanMoneyFragment extends Fragment {


    public DaXacNhanMoneyFragment() {
        // Required empty public constructor
    }


    public static DaXacNhanMoneyFragment newInstance() {
        DaXacNhanMoneyFragment fragment = new DaXacNhanMoneyFragment();
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
        return inflater.inflate(R.layout.fragment_da_xac_nhan_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}