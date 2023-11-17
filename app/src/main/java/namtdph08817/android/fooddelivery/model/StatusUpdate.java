package namtdph08817.android.fooddelivery.model;

public class StatusUpdate {
    String status,time;

    public StatusUpdate() {
    }

    public StatusUpdate(String status, String time) {
        this.status = status;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
