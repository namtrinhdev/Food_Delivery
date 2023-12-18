package namtdph08817.android.fooddelivery.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import namtdph08817.android.fooddelivery.fragment.AccountFragment;
import namtdph08817.android.fooddelivery.fragment.CartFragment;
import namtdph08817.android.fooddelivery.fragment.ChoLayHangFragment;
import namtdph08817.android.fooddelivery.fragment.ChoXacNhanFragment;
import namtdph08817.android.fooddelivery.fragment.DaGiaoHangFragment;
import namtdph08817.android.fooddelivery.fragment.DaHuyDonHangFragment;
import namtdph08817.android.fooddelivery.fragment.DangGiaoHangFragment;
import namtdph08817.android.fooddelivery.fragment.FavouriteFragment;
import namtdph08817.android.fooddelivery.fragment.HomeFragment;

public class AdapterQLDH extends FragmentStateAdapter {
    public AdapterQLDH(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = ChoXacNhanFragment.newInstance();
                break;
            case 1:
                fragment = ChoLayHangFragment.newInstance();
                break;
            case 2:
                fragment = DangGiaoHangFragment.newInstance();
                break;
            case 3:
                fragment = DaGiaoHangFragment.newInstance();
                break;
            case 4:
                fragment = DaHuyDonHangFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
