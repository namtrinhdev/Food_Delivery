package namtdph08817.android.fooddelivery.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import namtdph08817.android.fooddelivery.fragment.AllFoodsFragment;
import namtdph08817.android.fooddelivery.fragment.BreakfastFragment;
import namtdph08817.android.fooddelivery.fragment.DinnerFragment;
import namtdph08817.android.fooddelivery.fragment.FoodTypeHomeFragment;
import namtdph08817.android.fooddelivery.fragment.LunchFragment;

public class Adapter_Food_Home extends FragmentStateAdapter {
    public Adapter_Food_Home(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = AllFoodsFragment.newInstance();
                break;
            case 1:
                fragment = BreakfastFragment.newInstance();
                break;
            case 2:
                fragment = LunchFragment.newInstance();
                break;
            case 3:
                fragment = DinnerFragment.newInstance();
                break;
            case 4:
                fragment = FoodTypeHomeFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
