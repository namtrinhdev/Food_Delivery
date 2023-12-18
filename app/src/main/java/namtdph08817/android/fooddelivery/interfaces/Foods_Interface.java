package namtdph08817.android.fooddelivery.interfaces;

import java.util.List;

import namtdph08817.android.fooddelivery.model.Foods;
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

public interface Foods_Interface {
    @GET("api/foods")
    Call<List<Foods>> getAll();

    @GET("api/foods/{id}")
    Call<List<Foods>> getById(@Path("id") String id);
    @Multipart
    @POST("api/foods")
    Call<Messages> postFood(@Part MultipartBody.Part img,
                            @Part("foods") RequestBody foods);
    @Multipart
    @PUT("api/foods/{id}")
    Call<Messages> putFood(@Path("id") String id,
                           @Part MultipartBody.Part img,
                           @Part("foods") RequestBody foods);
    @DELETE("api/foods/{id}")
    Call<Messages> deleteFood(@Path("id")String id);
}
