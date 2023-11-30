package namtdph08817.android.fooddelivery.interfaces;

import namtdph08817.android.fooddelivery.model.FoodType;
import namtdph08817.android.fooddelivery.model.Foods;

public interface CURD_Interface {
    void updateData(Foods foods);
    void updateFoodType(FoodType foodType);
    void deleteData(String id);
}
