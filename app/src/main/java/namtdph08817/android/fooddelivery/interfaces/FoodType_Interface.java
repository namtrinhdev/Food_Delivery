package namtdph08817.android.fooddelivery.interfaces;

import java.util.List;

import namtdph08817.android.fooddelivery.model.FoodType;
import namtdph08817.android.fooddelivery.model.Messages;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface FoodType_Interface {
    @GET("api/foodtype")
    Call<List<FoodType>> getAll();
    @Multipart
    @POST("api/foodtype")
    Call<Messages> postFoodType(@Part MultipartBody.Part img,
                                @Part("tenloai")RequestBody tenloai);
    @Multipart
    @PUT("api/foodtype/{id}")
    Call<Messages> putFoodType(@Path("id") String id,
                               @Part MultipartBody.Part img,
                               @Part("tenLoai")RequestBody tenloai);
    @DELETE("api/foodtype/{id}")
    Call<Messages> deleteFoodType(@Path("id") String id);
}
