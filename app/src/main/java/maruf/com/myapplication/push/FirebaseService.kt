package maruf.com.myapplication.push

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import maruf.com.myapplication.R
import maruf.com.myapplication.menunavigasi
import kotlin.random.Random


private const val CHANNEL_ID="my_channel"
class FirebaseService:FirebaseMessagingService() {

    companion object{
        var sharedPref :SharedPreferences?=null

        var token: String?
        get() {
            return sharedPref?.getString("token","")
        }
        set(value){
            sharedPref?.edit()?.putString("token",value)?.apply()
        }
    }


    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token =newToken
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val intent = Intent(this, menunavigasi::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID= Random.nextInt()

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            createNotificationchanel(notificationManager)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val bitmap :Bitmap=BitmapFactory.decodeResource(applicationContext.resources, R.drawable.icom)
        val bitmapLarge:Bitmap=BitmapFactory.decodeResource(applicationContext.resources, R.drawable.notof)
        val pendingIntent = PendingIntent.getActivity(this,0, intent, FLAG_ONE_SHOT)
        val notification=NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.notof)
            .setAutoCancel(true)
                .setLargeIcon(bitmapLarge)
                .setStyle(NotificationCompat.BigTextStyle().bigText("biaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatmap"))

                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationID,notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationchanel(notificationManager: NotificationManager){
        val channelName="channelName"
        val channel =NotificationChannel(CHANNEL_ID,channelName, IMPORTANCE_HIGH).apply {
            description = "My channel deskription"
            enableLights(true)
            lightColor = Color.GREEN
        }
            notificationManager.createNotificationChannel(channel)
    }
}