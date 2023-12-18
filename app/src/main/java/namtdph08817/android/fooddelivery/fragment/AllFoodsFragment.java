package namtdph08817.android.fooddelivery.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import namtdph08817.android.fooddelivery.FoodDetailActivity;
import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.adapter.FoodsAdapter;
import namtdph08817.android.fooddelivery.classs.RetrofitClientAPI;
import namtdph08817.android.fooddelivery.interfaces.Foods_Interface;
import namtdph08817.android.fooddelivery.interfaces.Next_interface;
import namtdph08817.android.fooddelivery.model.Foods;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFoodsFragment extends Fragment {
    private static final String TAG = "AllFoodFrg";
    private RecyclerView recyclerView;
    private FoodsAdapter adapter;
    private Foods_Interface anInterface;

    public AllFoodsFragment() {
        // Required empty public constructor
    }

    public static AllFoodsFragment newInstance() {
        AllFoodsFragment fragment = new AllFoodsFragment();
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
        return inflater.inflate(R.layout.fragment_all_foods, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_recycle_all_food);

        anInterface = RetrofitClientAPI.getRetrofitInstance().create(Foods_Interface.class);
        adapter = new FoodsAdapter(getContext(), new Next_interface() {
            @Override
            public void onNextPage(Foods foods) {
                Intent i = new Intent(getActivity(), FoodDetailActivity.class);
                i.putExtra("food_data", (Serializable) foods);
                startActivity(i);
            }

            @Override
            public void onClickItem(String id) {

            }

        });
        loadData();
    }

    private void loadData() {
        Call<List<Foods>> call = anInterface.getAll();
        call.enqueue(new Callback<List<Foods>>() {
            @Override
            public void onResponse(Call<List<Foods>> call, Response<List<Foods>> response) {
                if (response.isSuccessful()){
                    List<Foods> list = response.body();
                    adapter.setData((ArrayList<Foods>) list);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Foods>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}