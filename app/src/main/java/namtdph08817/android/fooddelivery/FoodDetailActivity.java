package namtdph08817.android.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import namtdph08817.android.fooddelivery.classs.APIClass;
import namtdph08817.android.fooddelivery.model.Cart;
import namtdph08817.android.fooddelivery.model.Cart2;
import namtdph08817.android.fooddelivery.model.Foods;

public class FoodDetailActivity extends AppCompatActivity {
    private Foods food;
    private TextView tv_mota, tv_namefood, tv_quantity, tv_price;
    private ImageView img_tru, img_cong, img_food, img_backpress;
    private Button btn_themGH;
    private int count = 1;
    private List<Cart> list = Cart2.getInstance().getCart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        anhxa();
        //get data food from intent
        Intent intent = getIntent();
        food = (Foods) intent.getSerializableExtra("food_data");
        //set textview food detail
        tv_namefood.setText(food.getNameFood());
        tv_mota.setText(food.getMota());
        String url = APIClass.URL + "uploads/" + food.getImage();
        Glide.with(this).load(url).into(img_food);
        tv_quantity.setText(String.valueOf(count));
        tv_price.setText(food.getPrice() + " đ");
        if (food.getQuantity() == 0) {
            count = 0;
            tv_quantity.setText(String.valueOf(count));
            tv_price.setText(count + " đ");
        }
        if (getQuantity() == food.getQuantity()){
            count = 0;
            tv_quantity.setText(String.valueOf(count));
            tv_price.setText(count + " đ");
        }
        //
        img_cong.setOnClickListener(view -> {
            if (list.size() != 0) {
                int index = -1;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getFoods().get_id().equals(food.get_id())) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    if (list.get(index).getSoLuong() <= food.getQuantity()) {
                        if (count < food.getQuantity() - list.get(index).getSoLuong() && count < 15) {
                            count++;
                            tv_quantity.setText(String.valueOf(count));
                            tv_price.setText(food.getPrice() * count + " đ");
                        }
                    }
                } else {
                    if (count < food.getQuantity() && count < 15) {
                        count++;
                        tv_quantity.setText(String.valueOf(count));
                        tv_price.setText(food.getPrice() * count + " đ");
                    }
                }

            } else {
                //list null
                if (count < food.getQuantity() && count < 15) {
                    count++;
                    tv_quantity.setText(String.valueOf(count));
                    tv_price.setText(food.getPrice() * count + " đ");
                }
            }

        });
        img_tru.setOnClickListener(view -> {
            if (count > 1) {
                count--;
                tv_quantity.setText(String.valueOf(count));
                tv_price.setText(food.getPrice() * count + " đ");
            }
        });
        img_backpress.setOnClickListener(view -> {
            onBackPressed();
        });

        btn_themGH.setOnClickListener(view -> {
            if (count != 0) {
                Cart cart = new Cart(food, count);
                if (list.size() == 0) {
                    list.add(cart);
                    Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    if (food.getQuantity() == count) {
                        count = 0;
                        tv_quantity.setText(String.valueOf(count));
                        tv_price.setText(food.getPrice() * count + " đ");
                    } else {
                        count = 1;
                        tv_quantity.setText(String.valueOf(count));
                        tv_price.setText(food.getPrice() * count + " đ");
                    }

                } else {
                    int index = -1;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getFoods().get_id().equals(cart.getFoods().get_id())) {
                            index = i;
                            break;
                        }
                    }
                    if (index != -1) {
                        int sum = list.get(index).getSoLuong() + count;
                        list.get(index).setSoLuong(sum);
                        if (food.getQuantity() > sum) {
                            count = 1;
                            tv_quantity.setText(String.valueOf(count));
                            tv_price.setText(food.getPrice() * count + " đ");
                        } else {
                            count = 0;
                            tv_quantity.setText(String.valueOf(count));
                            tv_price.setText(count * food.getPrice() + " đ");
                        }
                    } else {
                        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        list.add(cart);
                        if (food.getQuantity() == count) {
                            count = 0;
                            tv_quantity.setText(String.valueOf(count));
                            tv_price.setText(food.getPrice() * count + " đ");
                        } else {
                            count = 1;
                            tv_quantity.setText(String.valueOf(count));
                            tv_price.setText(food.getPrice() * count + " đ");
                        }
                    }
                }
            }
        });

    }

    private void anhxa() {
        tv_mota = findViewById(R.id.tv_description_food_detail);
        tv_namefood = findViewById(R.id.tv_name_food_detail);
        tv_quantity = findViewById(R.id.tv_number_food_detail);
        tv_price = findViewById(R.id.tv_price_food_detail);
        img_cong = findViewById(R.id.img_plus_food_detail);
        img_tru = findViewById(R.id.img_remove_food_detail);
        img_food = findViewById(R.id.img_food_detail);
        img_backpress = findViewById(R.id.img_backpress_food_detail);
        btn_themGH = findViewById(R.id.btn_addToCart_food_detail);
    }
    private int getQuantity(){
        if (list.size() != 0) {
            if (getIndex() != -1) {
                return list.get(getIndex()).getSoLuong();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private int getIndex(){
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getFoods().get_id().equals(food.get_id())) {
                return i;
            }
        }
        return -1;
    }

}