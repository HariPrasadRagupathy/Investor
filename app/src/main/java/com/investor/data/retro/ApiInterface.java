package com.investor.data.retro;



import com.investor.models.DepositHistoryResponse;
import com.investor.models.DepositRequest;
import com.investor.models.DepositResponse;
import com.investor.models.EarningsResponse;
import com.investor.models.GeneralResponse;
import com.investor.models.InvestCancelResponse;
import com.investor.models.InvestmentPlans;
import com.investor.models.LoginRequest;
import com.investor.models.LoginResponse;
import com.investor.models.OrderIdRequest;
import com.investor.models.PaymentListResponse;
import com.investor.models.ProofUploadRequest;
import com.investor.models.ProofUploadResponse;
import com.investor.models.TokenUpdateRequest;
import com.investor.models.UserIdRequest;
import com.investor.models.WithDrawHistoryResponse;
import com.investor.models.WithDrawRequest;
import com.investor.models.WithDrawResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Vyancorp on 2/27/2018.
 */

public interface ApiInterface {

    @GET("index")
    Call<LoginResponse> login(@Query("id") List<Long> taskIds);

    @POST("index")
    Call<LoginResponse> login(@Query("type") String type,
                                   @Query("key") String key,@Body LoginRequest registerRequest);


    @GET("index")
    Call<InvestmentPlans> getInvestPlans(@Query("type") String type,
                                         @Query("key") String key);

    @POST("index")
    Call<DepositResponse> deposit(@Query("type") String type,
                                @Query("key") String key, @Body DepositRequest depositRequest);


    @POST("index")
    Call<DepositHistoryResponse> deposit_history(@Query("type") String type,
                                                @Query("key") String key, @Body UserIdRequest userIdRequest);


    @POST("index")
    Call<WithDrawResponse> withdraw(@Query("type") String type,
                                    @Query("key") String key, @Body WithDrawRequest withdrawRequest);

    @POST("index")
    Call<EarningsResponse> earnings(@Query("type") String type,
                                           @Query("key") String key, @Body UserIdRequest userIdRequest);


    @POST("index")
    Call<WithDrawHistoryResponse> withdraw_history(@Query("type") String type,
                                           @Query("key") String key, @Body UserIdRequest userIdRequest);


    @POST("index")
    Call<ProofUploadResponse> uploadProof(@Query("type") String type,
                                          @Query("key") String key, @Body ProofUploadRequest proofUploadRequest);

    @POST("index")
    Call<ProofUploadResponse> cancelDeposit(@Query("type") String type,
                                          @Query("key") String key, @Body OrderIdRequest orderIdRequest);

    @POST("index")
    Call<InvestCancelResponse> cancelWithdraw(@Query("type") String type,
                                              @Query("key") String key, @Body OrderIdRequest orderIdRequest);


    @POST("index")
    Call<InvestCancelResponse> updateToken(@Query("type") String type,
                                              @Query("key") String key, @Body TokenUpdateRequest tokenUpdateRequest);

    @GET("index")
    Call<PaymentListResponse> paymentmode_list(@Query("type") String type,
                                               @Query("key") String key);


  /*  @POST("parent_register")
    Call<GeneralResponse> registerParent(@Body RegisterRequest registerRequest);

    @POST("otp_verify")
    Call<GeneralResponse> otpVerify(@Body OTPverifyRequest otpVerifyRequest);

    @POST("parent_login")
    Call<GeneralResponse> loginParent(@Body ParentLoginRequest parentLoginRequest);

    @POST("parent_forgotpassword")
    Call<GeneralResponse> forgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

    @POST("resend_otp")
    Call<GeneralResponse> resendOtp(@Body ResendOTPRequest resendOTPRequest);

    @POST("parent_profile")
    Call<ParentProfileResponse> getParentProfile(@Body ParentProfileRequest parentProfileRequest);

    @POST("student_list")
    Call<ParentProfileResponse> getStudentList(@Body ParentProfileRequest parentProfileRequest);*/




}
