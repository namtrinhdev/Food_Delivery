package namtdph08817.android.fooddelivery.model;

public class LoaiThucPham {
    private String tenLoai;
    private int img;

    public LoaiThucPham(String tenLoai, int img) {
        this.tenLoai = tenLoai;
        this.img = img;
    }

    public LoaiThucPham() {
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
