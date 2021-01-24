package maruf.com.myapplication.push

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import maruf.com.myapplication.R
import maruf.com.myapplication.menunavigasi


const val TOPIC="/topics/myTopic"
class MainActivity : AppCompatActivity() {
    val TAG="MainActifiy"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseService.sharedPref =getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
        val etTokent = findViewById<EditText>(R.id.idtoken)
FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
    FirebaseService.token =it.token
    etTokent.setText(it.token)
}
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        var simpan = findViewById(R.id.idsimpan) as Button
        val etTitle = findViewById<EditText>(R.id.idjudul)
        val etmessage = findViewById<EditText>(R.id.idpesan)

var intent=intent
val messge =intent.getStringExtra("KS")
        val to =intent.getStringExtra("DES")
        val txmesage = findViewById<TextView>(R.id.ididaaaaan)



                simpan.setOnClickListener{
            val title = ""+messge
            val message =""+to
                 //   val  receipeinToken=etTokent.text.toString()
                    if(title.isNotEmpty()&&message.isNotEmpty()){
                        PushNotification(
                                NotificationData(title,message),
                               TOPIC
                              //  receipeinToken

                        ).also {
                            sendnotification(it)


                            val intent = Intent(this, menunavigasi::class.java)
                            onBackPressed();
startActivity(intent)
finish()
                        }

                    }
        }
    }

    override fun onBackPressed(){

    }
   private  fun sendnotification (notification: PushNotification)= CoroutineScope(Dispatchers.IO).launch {
        try {

val response = Instance.api.postnotification(notification)
            if(response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)} ")

            } else{
                Log.e(TAG, response.errorBody().toString())
            }
        }catch (e:Exception){
            Log.e(TAG, e.toString())
            Toast.makeText(applicationContext,"this is toast message",Toast.LENGTH_SHORT).show()

        }

    }

}

