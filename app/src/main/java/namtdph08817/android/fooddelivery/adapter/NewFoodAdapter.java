package namtdph08817.android.fooddelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namtdph08817.android.fooddelivery.R;

public class NewFoodAdapter extends RecyclerView.Adapter<NewFoodAdapter.FoodViewHolder> {
    private Context context;
    private ArrayList<String> arrayList;

    public NewFoodAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NewFoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newfood,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewFoodAdapter.FoodViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (arrayList.size() !=0 ){
            return arrayList.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name,tv_price,tv_space;
        private ImageView imgavatar;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_item_namefood);
            tv_price = itemView.findViewById(R.id.tv_item_money);
            tv_space = itemView.findViewById(R.id.tv_item_distance);
            imgavatar = itemView.findViewById(R.id.img_item_newfood);
        }
    }
}
