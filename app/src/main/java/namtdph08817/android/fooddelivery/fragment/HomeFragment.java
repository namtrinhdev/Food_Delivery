package namtdph08817.android.fooddelivery.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.SearchActivity;
import namtdph08817.android.fooddelivery.adapter.AdapterQLDH;
import namtdph08817.android.fooddelivery.adapter.LoaiThucPhamAdapter;
import namtdph08817.android.fooddelivery.adapter.SlideShowAdapter;
import namtdph08817.android.fooddelivery.model.LoaiThucPham;
import namtdph08817.android.fooddelivery.model.Photo;

public class HomeFragment extends Fragment {
    private RecyclerView Gdview;
    private ArrayList<LoaiThucPham> arrayList = new ArrayList<>();
    private LoaiThucPhamAdapter adapter;
    private EditText ed_search;
    private ViewPager2 slideshow;
    private CircleIndicator3 circleIndicator3;
    private SlideShowAdapter slideShowAdapter;
    private List<Photo> list = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable mRunable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = slideshow.getCurrentItem();
            if (currentPosition == list.size() - 1){
                slideshow.setCurrentItem(0);
            }else {
                slideshow.setCurrentItem(currentPosition+1);
            }
        }
    };
    public HomeFragment() {
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        slideshow = view.findViewById(R.id.id_slide_show);
        circleIndicator3 = view.findViewById(R.id.id_circleindicator);
        ed_search = view.findViewById(R.id.ed_search);
        Gdview = view.findViewById(R.id.gridview);

        //slideshow
        listPhoto();
        setSlideShow();

        //tablayout
        TabLayout tabLayout = view.findViewById(R.id.id_tablayout_home);
        ViewPager2 viewPager2 = view.findViewById(R.id.id_viewpager2_home);
        AdapterQLDH adapterQLDH = new AdapterQLDH(requireActivity());
        viewPager2.setAdapter(adapterQLDH);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setCustomView(R.layout.custom_tablayout);
                switch (position){
                    case 0:
                        tab.setText("Hot");
                        break;
                    case 1:
                        tab.setText("Ăn sáng");
                        break;
                    case 2:
                        tab.setText("Ăn trưa");
                        break;
                    case 3:
                        tab.setText("Ăn tối");
                        break;
                    case 4:
                        tab.setText("Ăn lằm ăn lốn");
                        break;
                }

                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.bg_red));

                // Set màu text khi không được chọn
                tabLayout.setTabTextColors(getResources().getColor(R.color.bg_white), Color.parseColor("#FF4081"));
            }
        });
        mediator.attach();


        //list foodtype
        listData();
        adapter = new LoaiThucPhamAdapter(getActivity(),arrayList);
        Gdview.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        Gdview.setAdapter(adapter);

        ed_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

    }

    private void setSlideShow() {
        slideshow.setOffscreenPageLimit(3);
        slideshow.setClipChildren(false);
        slideshow.setClipToPadding(false);
        CompositePageTransformer cpt = new CompositePageTransformer();
        cpt.addTransformer(new MarginPageTransformer(40));
        cpt.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        slideshow.setPageTransformer(cpt);

        slideShowAdapter = new SlideShowAdapter(list, getActivity());
        slideshow.setAdapter(slideShowAdapter);
        circleIndicator3.setViewPager(slideshow);
        slideshow.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(mRunable);
                handler.postDelayed(mRunable,4000);
            }
        });
    }

    private void listPhoto() {
        list.add(new Photo(R.drawable.banner1));
        list.add(new Photo(R.drawable.banner2));
        list.add(new Photo(R.drawable.banner3));
        list.add(new Photo(R.drawable.banner4));
        list.add(new Photo(R.drawable.banner1));
        list.add(new Photo(R.drawable.banner2));
        list.add(new Photo(R.drawable.banner3));
        list.add(new Photo(R.drawable.banner4));
    }

    private void listData() {
        arrayList.add(new LoaiThucPham("Ăn sáng",R.drawable.icon_pho));
        arrayList.add(new LoaiThucPham("Ăn trưa",R.drawable.icon_banhmi));
        arrayList.add(new LoaiThucPham("Ăn tối",R.drawable.icon_pho));
        arrayList.add(new LoaiThucPham("Cơm",R.drawable.icon_banhmi));
        arrayList.add(new LoaiThucPham("Phở",R.drawable.icon_pho));
        arrayList.add(new LoaiThucPham("Bún",R.drawable.icon_banhmi));
    }

}