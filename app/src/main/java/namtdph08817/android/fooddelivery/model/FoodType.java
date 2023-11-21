package namtdph08817.android.fooddelivery.model;

import java.io.Serializable;

public class FoodType implements Serializable {
    private String tenLoai;
    private int img;

    public FoodType(String tenLoai, int img) {
        this.tenLoai = tenLoai;
        this.img = img;
    }

    public FoodType() {
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
