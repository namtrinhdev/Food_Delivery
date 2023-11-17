package namtdph08817.android.fooddelivery.model;

import java.util.List;

public class Messages {
    private String msg;
    private int status;
    private Users data;

    public Messages() {
    }

    public Messages(String msg, int status, Users data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Users getData() {
        return data;
    }

    public void setData(Users data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
