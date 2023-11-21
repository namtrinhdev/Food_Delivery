package namtdph08817.android.fooddelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.classs.APIClass;
import namtdph08817.android.fooddelivery.interfaces.Next_interface;
import namtdph08817.android.fooddelivery.model.Foods;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodViewHolder> {
    private Context context;
    private ArrayList<Foods> arrayList;
    private Next_interface anInterface;

    public FoodsAdapter(Context context, Next_interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }
    public void setData(ArrayList<Foods> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodsAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newfood,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsAdapter.FoodViewHolder holder, int position) {
        Foods model = arrayList.get(position);
        holder.tv_name.setText(model.getNameFood());
        holder.tv_price.setText(String.valueOf(model.getPrice()));
        String url = APIClass.URL + "uploads/"+model.getImage();
        Glide.with(context).load(url).into(holder.imgavatar);
        holder.itemView.setOnClickListener(view -> {
            anInterface.onNextPage(model);
        });
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
            imgavatar = itemView.findViewById(R.id.img_item_newfood);
        }
    }
}
