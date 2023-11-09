package namtdph08817.android.fooddelivery.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import namtdph08817.android.fooddelivery.fragment.AccountFragment;
import namtdph08817.android.fooddelivery.fragment.CartFragment;
import namtdph08817.android.fooddelivery.fragment.FavouriteFragment;
import namtdph08817.android.fooddelivery.fragment.HomeFragment;

public class ViewPagerAdapterr extends FragmentStateAdapter {
    public ViewPagerAdapterr(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = HomeFragment.newInstance();
                break;
            case 1:
                fragment = FavouriteFragment.newInstance();
                break;
            case 2:
                fragment = CartFragment.newInstance();
                break;
            case 3:
                fragment = AccountFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
