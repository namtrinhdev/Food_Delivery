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
import namtdph08817.android.fooddelivery.model.Cart;

public class Adapter_DonHang extends RecyclerView.Adapter<Adapter_DonHang.DonHangViewHolder> {
    private Context context;
    private List<Cart> arrayList;

    public Adapter_DonHang(Context context) {
        this.context = context;
    }
    public void setData(List<Cart> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Adapter_DonHang.DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donhang,parent,false);
        return new Adapter_DonHang.DonHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_DonHang.DonHangViewHolder holder, int position) {
        Cart model = arrayList.get(position);
        holder.tv_nameFood.setText(model.getFoods().getNameFood());
        holder.tv_price.setText(model.getFoods().getPrice()+" đ");
        holder.tv_quantity.setText(model.getSoLuong()+"");
        String url = APIClass.URL + "uploads/"+model.getFoods().getImage();
        Glide.with(context).load(url).placeholder(R.drawable.img_default_image).into(holder.img);
    }

    @Override
    public int getItemCount() {
        if (arrayList.size() !=0 ){
            return arrayList.size();
        }
        return 0;
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nameFood, tv_price, tv_quantity;
        private ImageView img;
        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nameFood = itemView.findViewById(R.id.tv_item_namefood_donhang);
            tv_price = itemView.findViewById(R.id.tv_item_price_donHang);
            tv_quantity = itemView.findViewById(R.id.tv_item_quantity_donHang);
            img = itemView.findViewById(R.id.img_item_donHang);
        }
    }
}
