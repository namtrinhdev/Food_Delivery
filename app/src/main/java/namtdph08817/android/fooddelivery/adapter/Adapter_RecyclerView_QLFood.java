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
import namtdph08817.android.fooddelivery.model.Foods;

public class Adapter_RecyclerView_QLFood extends RecyclerView.Adapter<Adapter_RecyclerView_QLFood.ViewHolder> {
    private Context context;
    private List<Foods> list;
    private CURD_Interface anInterface;

    public Adapter_RecyclerView_QLFood(Context context, CURD_Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void setData(List<Foods> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Adapter_RecyclerView_QLFood.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_foods_ql, parent, false);
        return new Adapter_RecyclerView_QLFood.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_RecyclerView_QLFood.ViewHolder holder, int position) {
        Foods model = list.get(position);
        holder.tv_name.setText(model.getNameFood());
        holder.tv_price.setText(model.getPrice() + " đ");
        holder.tv_quantity.setText("SL: "+model.getQuantity());
        String url = APIClass.URL + "uploads/" + model.getImage();
        Glide.with(context).load(url).placeholder(R.drawable.img_default_image).into(holder.img_food);

        holder.itemView.setOnClickListener(view -> {
            anInterface.updateData(model);
        });

        holder.img_delete.setOnClickListener(view -> {
            anInterface.deleteData(model.get_id());
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price, tv_quantity;
        private ImageView img_food, img_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_food = itemView.findViewById(R.id.img_item_ql_food);
            img_delete = itemView.findViewById(R.id.img_item_delete_food);
            tv_name = itemView.findViewById(R.id.tv_item_namefood_ql);
            tv_price = itemView.findViewById(R.id.tv_item_price_ql);
            tv_quantity = itemView.findViewById(R.id.tv_item_quantity_ql);
        }
    }
}
