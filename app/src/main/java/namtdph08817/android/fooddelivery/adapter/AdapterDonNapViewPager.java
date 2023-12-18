package namtdph08817.android.fooddelivery.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import namtdph08817.android.fooddelivery.fragment.ChoXacNhanMoneyFragment;
import namtdph08817.android.fooddelivery.fragment.DaGiaoHangFragment;
import namtdph08817.android.fooddelivery.fragment.DaHuyMoneyFragment;
import namtdph08817.android.fooddelivery.fragment.DaXacNhanMoneyFragment;

public class AdapterDonNapViewPager extends FragmentStateAdapter {
    public AdapterDonNapViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = ChoXacNhanMoneyFragment.newInstance();
                break;
            case 1:
                fragment = DaXacNhanMoneyFragment.newInstance();
                break;
            case 2:
                fragment = DaHuyMoneyFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
