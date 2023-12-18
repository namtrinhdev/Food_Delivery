package namtdph08817.android.fooddelivery.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import namtdph08817.android.fooddelivery.classs.SessionManager;
import namtdph08817.android.fooddelivery.fragment.AccountFragment;
import namtdph08817.android.fooddelivery.fragment.CartFragment;
import namtdph08817.android.fooddelivery.fragment.FavouriteFragment;
import namtdph08817.android.fooddelivery.fragment.HomeFragment;
import namtdph08817.android.fooddelivery.fragment.ThongKeFragment;

public class ViewPagerAdapterr extends FragmentStateAdapter {
    private SessionManager sessionManager;
    public ViewPagerAdapterr(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        sessionManager = new SessionManager(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (sessionManager.getVaiTro() == 0){
            switch (position) {
                case 0:
                    fragment = ThongKeFragment.newInstance();
                    break;
                case 1:
                    fragment = AccountFragment.newInstance();
                    break;
            }
        }else {
            switch (position) {
                case 0:
                    fragment = HomeFragment.newInstance();
                    break;
                case 1:
                    fragment = CartFragment.newInstance();
                    break;
                case 2:
                    fragment = AccountFragment.newInstance();
                    break;
            }
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        if (sessionManager.getVaiTro() == 0){
            return 2;
        }
        return 3;
    }
}
