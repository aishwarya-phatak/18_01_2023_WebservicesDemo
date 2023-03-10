package com.example.webservicesdemo

import android.util.Log
import android.widget.Toast
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class WebUtil {
    companion object{
        fun simpleHTTPRequest(targetUrl : String){
                var url = URL(targetUrl)
            var httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connect()

           /* Log.e("tag","${httpsURLConnection.responseCode}")
            Log.e("tag","${httpsURLConnection.responseMessage}")
            Log.e("tag","${httpsURLConnection.contentLength}")
            Log.e("tag","${httpsURLConnection.content}")
            Log.e("tag","${httpsURLConnection.contentEncoding}")
            Log.e("tag","${httpsURLConnection.contentType}")*/

            var inStream = httpURLConnection.inputStream
            var data = ByteArray(1024 * 1)
            var count = 0
            var buffer = StringBuffer()

            count = inStream.read(data)
            while (count != -1){
                buffer.append(String(data,0,count))
                count = inStream.read(data)
            }
            inStream.close()
            //Log.e("tag",buffer.toString())
        }


        fun getAllUsers() : ArrayList<User>?{
            var url = URL("https://reqres.in/api/users?page=2")
            var httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connect()

            //Log.e("tag","${httpsURLConnection.responseCode}")

            if(httpURLConnection.responseCode != 200){
                return null
            }

            var responseBuffer = StringBuffer()
            var count = 0
            var data = ByteArray(1024 * 1)
            var inStream = httpURLConnection.inputStream

            count = inStream.read(data)
            while (count != -1){
                responseBuffer.append(String(data,0,count))
                count = inStream.read(data)
            }

            inStream.close()

            var jsonRoot = JSONObject(responseBuffer.toString())
            if(jsonRoot.has("page")){
                //Log.e("tag","${jsonRoot.getInt("page")}")
            }

            //Log.e("total pages","${jsonRoot.getInt("total_pages")}")

            var jUserArray = jsonRoot.getJSONArray("data")
            var users = ArrayList<User>()
            for (i in 0 until jUserArray.length()-1){
                    var jEachUser = jUserArray.getJSONObject(i)
                    users.add(
                        User(
                            jEachUser.getInt("id"),
                            jEachUser.getString("email"),
                            jEachUser.getString("first_name"),
                            jEachUser.getString("last_name"),
                            jEachUser.getString("avatar")
                        )
                    )
            }
            return users
        }

    }
}