package yanovski.master_thesis.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.data.models.api.Account;
import yanovski.master_thesis.data.models.api.CategoriesResponse;
import yanovski.master_thesis.data.models.api.Credentials;
import yanovski.master_thesis.data.models.api.GraduationDateRequest;
import yanovski.master_thesis.data.models.api.GraduationDateResponse;
import yanovski.master_thesis.data.models.api.GraduationDatesResponse;
import yanovski.master_thesis.data.models.api.InterestResponse;
import yanovski.master_thesis.data.models.api.NewThesis;
import yanovski.master_thesis.data.models.api.NewThesisProposal;
import yanovski.master_thesis.data.models.api.StudentsResponse;
import yanovski.master_thesis.data.models.api.TeacherResponse;
import yanovski.master_thesis.data.models.api.TeachersResponse;
import yanovski.master_thesis.data.models.api.ThesisResponse;
import yanovski.master_thesis.data.models.api.TokenResponse;

/**
 * Created by Samuil on 5/8/2016.
 */
public interface MasterThesisServices {
    @POST("register")
    Call<TokenResponse> register(@Body Account account);

    @POST("register")
    Call<TokenResponse> login(@Body Credentials credentials);

    @GET("teachers")
    Call<TeachersResponse> getTeachers();

    @GET("students")
    Call<StudentsResponse> getStudents();

    @POST("teachers")
    Call<TeacherResponse> updateCurrentTeacher(@Body Account account);

    @POST("students")
    Call<StudentsResponse> updateCurrentStudent(@Body Account account);

    @GET("categories")
    Call<CategoriesResponse> getCategories();

    @PUT("theses")
    Call<ThesisResponse> createThesis(@Body NewThesis data);

    @PUT("theses/proposals")
    Call<ThesisResponse> createThesisProposal(@Body NewThesisProposal data);

    @POST("theses/{key}/approve")
    Call<ThesisResponse> approveThesisProposal(@Path("key") String key);

    @POST("theses/{key}/decline")
    Call<ThesisResponse> declineThesisProposal(@Path("key") String key);

    @PUT("interests")
    Call<InterestResponse> createInterest(@Body Interest interest);

    @POST("interests/{key}")
    Call<InterestResponse> updateInterest(@Path("key") String key, @Body Interest interest);

    @DELETE("interests/{key}")
    Call<InterestResponse> deleteInterest(@Path("key") String key);

    @GET("dates")
    Call<GraduationDatesResponse> getDateS(@Body GraduationDateRequest date);

    @PUT("dates")
    Call<GraduationDateResponse> createDate(@Body GraduationDateRequest date);

    @POST("dates/{key}")
    Call<GraduationDateResponse> updateDate(@Path("key") String key, @Body GraduationDateRequest date);

    @POST("dates/{key}")
    Call<GraduationDateResponse> daleteDate(@Path("key") String key);
}
