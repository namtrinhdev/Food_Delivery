package namtdph08817.android.fooddelivery.interfaces;

import java.util.List;

import namtdph08817.android.fooddelivery.model.Messages;
import namtdph08817.android.fooddelivery.model.Users;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserInterface {
    @POST("api/users")
    Call<Messages> registerUser(@Body Users data);
    @GET("api/users")
    Call<List<Users>> getAllUser();
    @GET("api/users/{id}")
    Call<Users> getUserById(@Path("id") String id);
    @FormUrlEncoded
    @POST("api/users/checklogin")
    Call<Messages> checkLogin(@Field("email") String email, @Field("passwd") String passwd);
    @Multipart
    @PUT("api/users/{id}")
    Call<Users> putUser(@Path("id") String id,
                        @Part MultipartBody.Part avatar,
                        @Part("user") RequestBody user);
}
