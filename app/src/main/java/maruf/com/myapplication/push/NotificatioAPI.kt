package maruf.com.myapplication.push

import maruf.com.myapplication.push.Constants.Companion.CONTENT_TYPE
import maruf.com.myapplication.push.Constants.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificatioAPI {

    @Headers("Authorization: key =$SERVER_KEY","Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postnotification(
            @Body notification: PushNotification
    ): Response<ResponseBody>



}