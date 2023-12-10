package namtdph08817.android.fooddelivery.interfaces;

import namtdph08817.android.fooddelivery.model.NapTien;
import namtdph08817.android.fooddelivery.model.ThanhToan;

public interface ChangeStatusDonNapInterface {
    void onChange(String id);
    void openDetail(ThanhToan item);
    void openDetailMoney(NapTien item);
}
