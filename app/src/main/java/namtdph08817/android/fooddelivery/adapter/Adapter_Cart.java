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
import namtdph08817.android.fooddelivery.interfaces.Cart_Update_Interface;
import namtdph08817.android.fooddelivery.model.Cart;

public class Adapter_Cart extends RecyclerView.Adapter<Adapter_Cart.CartViewHolder> {
    private Context context;
    private ArrayList<Cart> arrayList;
    private Cart_Update_Interface anInterface;
    private int count;

    public Adapter_Cart(Context context,Cart_Update_Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }
    public void setData(ArrayList<Cart> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter_Cart.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);
        return new Adapter_Cart.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Cart.CartViewHolder holder, int position) {
        Cart model = arrayList.get(position);
        holder.tv_name.setText(model.getFoods().getNameFood());
        holder.tv_price.setText(String.valueOf(model.getFoods().getPrice()));
        holder.tv_quantity.setText(String.valueOf(model.getSoLuong()));
        String url = APIClass.URL+"uploads/"+model.getFoods().getImage();
        Glide.with(context).load(url).into(holder.img_avatar);
        count = model.getSoLuong();
        holder.img_increase.setOnClickListener(view -> {
            if (count < model.getFoods().getQuantity() && count < 15){
                count++;
                holder.tv_quantity.setText(String.valueOf(count));
                model.setSoLuong(count);
                anInterface.onUpdateCount();
            }
        });
        holder.img_diminish.setOnClickListener(view -> {
            if (count > 1){
                count--;
                holder.tv_quantity.setText(String.valueOf(count));
                model.setSoLuong(count);
                anInterface.onUpdateCount();
            }else {
                anInterface.onDelete(holder.getAdapterPosition());
            }
        });

        holder.itemView.setOnLongClickListener(view -> {
            anInterface.onDelete(holder.getAdapterPosition());
            return false;
        });
    }

    @Override
    public int getItemCount() {
        if (arrayList.size() !=0 ){
            return arrayList.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price, tv_quantity;
        private ImageView img_avatar, img_increase, img_diminish;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_item_namefood_cart);
            tv_price = itemView.findViewById(R.id.tv_item_price_cart);
            tv_quantity = itemView.findViewById(R.id.tv_item_quantity_cart);
            img_avatar = itemView.findViewById(R.id.img_item_cart);
            img_increase = itemView.findViewById(R.id.img_item_plus_cart);
            img_diminish = itemView.findViewById(R.id.img_item_remove_cart);
        }
    }
}
