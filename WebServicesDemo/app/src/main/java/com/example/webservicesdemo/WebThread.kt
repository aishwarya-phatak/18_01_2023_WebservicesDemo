package com.example.webservicesdemo

import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Message

class WebThread(var handler: Handler? = null) : AsyncTask<Any?, Any?, ArrayList<User>?>() {

    override fun doInBackground(vararg params: Any?): ArrayList<User>? {
        return WebUtil.getAllUsers()
    }

    override fun onPostExecute(result: ArrayList<User>?) {
        super.onPostExecute(result)
        var message = Message()
        message.obj = result
        //message.what = 1
        handler!!.sendMessage(message)
    }
}