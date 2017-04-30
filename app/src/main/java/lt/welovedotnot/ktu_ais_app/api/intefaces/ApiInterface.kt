package lt.welovedotnot.ktu_ais_app.api.intefaces

import lt.welovedotnot.ktu_ais_app.api.models.LoginRequest
import lt.welovedotnot.ktu_ais_app.api.models.LoginResponse
import lt.welovedotnot.ktu_ais_app.api.models.ModulesRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by simonas on 4/30/17.
 */
interface ApiInterface {

    @POST("api/login")
    fun loginReq(@Body body: LoginRequest): Call<LoginResponse>

    @POST("api/get_modules")
    fun modulesReq(@Body body: ModulesRequest, @Header("cookie") cookie: String): Call<ResponseBody>

}