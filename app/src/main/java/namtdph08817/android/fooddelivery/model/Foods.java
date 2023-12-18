package namtdph08817.android.fooddelivery.model;

import java.io.Serializable;
import java.util.List;

public class Foods implements Serializable {
    private String _id, nameFood;
    private int price;
    private List<FoodType> foodType;
    private int quantity, danhGia;
    private String image, mota;

    public Foods() {
    }

    public Foods(String _id, String nameFood, int price, List<FoodType> foodType, int quantity, int danhGia, String image) {
        this._id = _id;
        this.nameFood = nameFood;
        this.price = price;
        this.foodType = foodType;
        this.quantity = quantity;
        this.danhGia = danhGia;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<FoodType> getFoodType() {
        return foodType;
    }

    public void setFoodType(List<FoodType> foodType) {
        this.foodType = foodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
