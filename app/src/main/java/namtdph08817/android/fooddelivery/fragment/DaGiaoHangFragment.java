package namtdph08817.android.fooddelivery.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import namtdph08817.android.fooddelivery.DetailDonHangActivity;
import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.adapter.Adapter_RecyclerView_QLDH;
import namtdph08817.android.fooddelivery.classs.RetrofitClientAPI;
import namtdph08817.android.fooddelivery.classs.SessionManager;
import namtdph08817.android.fooddelivery.interfaces.ChangeStatusDonNapInterface;
import namtdph08817.android.fooddelivery.interfaces.ThanhToanAPI_Interface;
import namtdph08817.android.fooddelivery.model.NapTien;
import namtdph08817.android.fooddelivery.model.ThanhToan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaGiaoHangFragment extends Fragment {
    private static final String TAG = "DeliveredFrg";
    private RecyclerView recyclerView;
    private Adapter_RecyclerView_QLDH adapter;
    private ThanhToanAPI_Interface api_interface;
    private SessionManager sessionManager;
    private ProgressBar progressBar;
    private LinearLayout view_empty;

    public DaGiaoHangFragment() {
        // Required empty public constructor
    }

    public static DaGiaoHangFragment newInstance() {
        DaGiaoHangFragment fragment = new DaGiaoHangFragment();
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
        return inflater.inflate(R.layout.fragment_da_giao_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_recycle_dagiao);
        progressBar = view.findViewById(R.id.id_progressBar_DaGiaoHang);
        view_empty = view.findViewById(R.id.id_emptyView_daGiaoHang);

        //khoi tao
        sessionManager = new SessionManager(getContext());
        api_interface = RetrofitClientAPI.getRetrofitInstance().create(ThanhToanAPI_Interface.class);
        adapter = new Adapter_RecyclerView_QLDH(getContext(), new ChangeStatusDonNapInterface() {
            @Override
            public void onChange(String id) {
            }

            @Override
            public void openDetail(ThanhToan item) {
                Intent i = new Intent(getActivity(), DetailDonHangActivity.class);
                i.putExtra("donhang", item);
                startActivity(i);
            }

            @Override
            public void openDetailMoney(NapTien item) {

            }
        });

        //view du lieu len recyclerview
        loadData();
    }
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        view_empty.setVisibility(View.INVISIBLE);
        Call<List<ThanhToan>> call = api_interface.getDsDaGiao(sessionManager.getIdUser());
        call.enqueue(new Callback<List<ThanhToan>>() {
            @Override
            public void onResponse(Call<List<ThanhToan>> call, Response<List<ThanhToan>> response) {
                if (response.isSuccessful()){
                    List<ThanhToan> list = response.body();
                    adapter.setData(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setAdapter(adapter);
                    if (list.isEmpty()){
                        view_empty.setVisibility(View.VISIBLE);
                    }
                }else {
                    Log.e(TAG, "onFailure: get data fail from api" );
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onFailure(Call<List<ThanhToan>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối server, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ",t );
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}