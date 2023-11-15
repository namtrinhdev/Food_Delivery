package namtdph08817.android.fooddelivery.interfaces;

import java.util.List;

import namtdph08817.android.fooddelivery.model.Messages;
import namtdph08817.android.fooddelivery.model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;

public interface UserInterface {
    @POST("api/users")
    Call<Messages> registerUser(@Body Users data);
    @GET("api/users")
    Call<List<Users>> getAllUser();
//    @PUT("/binhluan/{id}")
//    Call<BinhLuanModel> putData(@Path("id") String id,@Body BinhLuanModel data);
//    @DELETE("/binhluan/{id}")
//    Call<BinhLuanModel> deleteData(@Path("id") String id);
}
