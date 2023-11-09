package namtdph08817.android.fooddelivery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import namtdph08817.android.fooddelivery.R;
import namtdph08817.android.fooddelivery.model.Photo;

public class SlideShowAdapter extends RecyclerView.Adapter<SlideShowAdapter.PhotoHolder> {
    private final List<Photo> list ;
    private final Context context;

    public SlideShowAdapter(List<Photo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SlideShowAdapter.PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo,parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideShowAdapter.PhotoHolder holder, int position) {
        Photo photo = list.get(position);
        holder.img.setImageResource(photo.getImgResource());
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_slideshow);
        }
    }
}
