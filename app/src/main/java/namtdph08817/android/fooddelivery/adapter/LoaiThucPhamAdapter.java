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
import namtdph08817.android.fooddelivery.model.FoodType;


public class LoaiThucPhamAdapter extends RecyclerView.Adapter<LoaiThucPhamAdapter.viewHodel> {
    private Context context;
    private ArrayList<FoodType> list;

    public LoaiThucPhamAdapter(Context context, ArrayList<FoodType> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiThucPhamAdapter.viewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaithucpham,parent,false);
        return new viewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThucPhamAdapter.viewHodel holder, int position) {
        FoodType model = list.get(position);
        holder.tvname.setText(model.getTenLoai());
        holder.img.setImageResource(model.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHodel extends RecyclerView.ViewHolder {
        private TextView tvname;
        private  ImageView img;
        public viewHodel(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tv_typename);
            img = itemView.findViewById(R.id.image_type);
        }
    }
}
