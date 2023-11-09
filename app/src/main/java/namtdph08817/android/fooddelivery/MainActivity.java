package namtdph08817.android.fooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import namtdph08817.android.fooddelivery.adapter.ViewPagerAdapterr;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private ViewPagerAdapterr adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.id_viewpager2);
        tabLayout = findViewById(R.id.id_tablayout);

        adapter = new ViewPagerAdapterr(this);

        viewPager2.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.ic_baseline_home_24);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_favorite);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_baseline_shopping_cart_24);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.ic_account);
                        break;
                }
            }
        });
        mediator.attach();
        viewPager2.setUserInputEnabled(false);
    }
}