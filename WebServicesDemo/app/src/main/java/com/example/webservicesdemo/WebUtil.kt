package com.example.webservicesdemo

import android.util.Log
import android.widget.Toast
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WebUtil {
    companion object{
        fun simpleHTTPRequest(targetUrl : String){
                var url = URL(targetUrl)
            var httpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.connect()

            Log.e("tag","${httpsURLConnection.responseCode}")
            Log.e("tag","${httpsURLConnection.responseMessage}")
            Log.e("tag","${httpsURLConnection.contentLength}")
            Log.e("tag","${httpsURLConnection.content}")
            Log.e("tag","${httpsURLConnection.contentEncoding}")
            Log.e("tag","${httpsURLConnection.contentType}")

            var inStream = httpsURLConnection.inputStream
            var data = ByteArray(1024 * 1)
            var count = 0
            var buffer = StringBuffer()

            count = inStream.read(data)
            while (count != -1){
                buffer.append(String(data,0,count))
                count = inStream.read(data)
            }
            inStream.close()
            Log.e("tag",buffer.toString())
        }

    }
}