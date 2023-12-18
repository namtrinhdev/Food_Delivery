package namtdph08817.android.fooddelivery.model;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private Foods foods;
    private int soLuong;

    public Cart() {
    }


    public Cart(Foods foods, int soLuong) {
        this.foods = foods;
        this.soLuong = soLuong;
    }

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public void increaseQuantity(){
        soLuong++;
    }
    public int getTotalPrice(){
        return soLuong * foods.getPrice();
    }
}
