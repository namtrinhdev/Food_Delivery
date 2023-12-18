package namtdph08817.android.fooddelivery.model;

import java.io.Serializable;

public class FoodType implements Serializable {
    private String _id, tenLoai;
    private String icon;

    public FoodType(String tenLoai, String icon) {
        this.tenLoai = tenLoai;
        this.icon = icon;
    }

    public FoodType() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
