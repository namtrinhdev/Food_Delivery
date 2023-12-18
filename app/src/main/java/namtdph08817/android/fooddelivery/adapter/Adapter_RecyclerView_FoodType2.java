package namtdph08817.android.fooddelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.interfaces.Item_Dialog;
import namtdph08817.android.fooddelivery.interfaces.Next_interface;
import namtdph08817.android.fooddelivery.model.FoodType;

public class Adapter_RecyclerView_FoodType2 extends RecyclerView.Adapter<Adapter_RecyclerView_FoodType2.FoodViewHolder> {
    private Context context;
    private List<FoodType> arrayList;
    private Item_Dialog anInterface;

    public Adapter_RecyclerView_FoodType2(Context context, Item_Dialog anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }
    public void setData(List<FoodType> arrayList){
        this.arrayList =arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter_RecyclerView_FoodType2.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_foodtype,parent,false);
        return new Adapter_RecyclerView_FoodType2.FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_RecyclerView_FoodType2.FoodViewHolder holder, int position) {
        FoodType model = arrayList.get(position);
        holder.tv_typeFood.setText(model.getTenLoai());
        holder.itemView.setOnClickListener(v ->{
            anInterface.onClickItem(model);
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
        private TextView tv_typeFood;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_typeFood = itemView.findViewById(R.id.tv_item_foodtype);
        }
    }
}
