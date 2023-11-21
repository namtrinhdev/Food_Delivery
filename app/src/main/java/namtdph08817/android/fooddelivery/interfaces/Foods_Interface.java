package namtdph08817.android.fooddelivery.interfaces;

import java.util.List;

import namtdph08817.android.fooddelivery.model.Foods;
import namtdph08817.android.fooddelivery.model.Messages;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Foods_Interface {
    @GET("api/foods")
    Call<List<Foods>> getAll();

    @GET("api/foods/{id}")
    Call<List<Foods>> getById(@Path("id") String id);
    @POST("api/foods")
    Call<Messages> postFood(@Body Foods foods);

}
