package namtdph08817.android.fooddelivery.model;

import java.io.Serializable;

public class Users implements Serializable {
    private String _id,fullname,email,sdt,passwd, diaChi;
    private int vaitro;
    private String avatar;
    private int soTien;

    public Users() {
    }

    public Users(String _id, String fullname, String email, String sdt, String passwd, String diaChi, int vaitro, String avatar, int soTien) {
        this._id = _id;
        this.fullname = fullname;
        this.email = email;
        this.sdt = sdt;
        this.passwd = passwd;
        this.diaChi = diaChi;
        this.vaitro = vaitro;
        this.avatar = avatar;
        this.soTien = soTien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getVaitro() {
        return vaitro;
    }

    public void setVaitro(int vaitro) {
        this.vaitro = vaitro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public Users(String _id, String fullname, String email, String sdt, String passwd, int vaitro, String avatar, int soTien) {
        this._id = _id;
        this.fullname = fullname;
        this.email = email;
        this.sdt = sdt;
        this.passwd = passwd;
        this.vaitro = vaitro;
        this.avatar = avatar;
        this.soTien = soTien;
    }
}
