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

import java.util.List;

import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.classs.APIClass;
import namtdph08817.android.fooddelivery.interfaces.CURD_Interface;
import namtdph08817.android.fooddelivery.model.FoodType;

public class Adapter_RecyclerView_FoodType1 extends RecyclerView.Adapter<Adapter_RecyclerView_FoodType1.viewHolder> {
    private Context context;
    private CURD_Interface anInterface;
    private List<FoodType> list;

    public Adapter_RecyclerView_FoodType1(Context context, CURD_Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void setData(List<FoodType> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter_RecyclerView_FoodType1.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food_type, parent, false);
        return new Adapter_RecyclerView_FoodType1.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_RecyclerView_FoodType1.viewHolder holder, int position) {
        FoodType model = list.get(position);
        holder.tv_name.setText(model.getTenLoai());
        String url = APIClass.URL +"uploads/" + model.getIcon();
        Glide.with(context).load(url).placeholder(R.drawable.img_default_image).into(holder.img_icon);
        holder.img_delete.setOnClickListener(view -> {
            anInterface.deleteData(model.get_id());
        });

        holder.itemView.setOnClickListener(view -> {
            anInterface.updateFoodType(model);
        });
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView img_icon, img_delete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_item_ql_foodType);
            img_icon = itemView.findViewById(R.id.img_avatar_item_ql_foodType);
            img_delete = itemView.findViewById(R.id.img_delete_item_ql_foodType);
        }
    }
}
