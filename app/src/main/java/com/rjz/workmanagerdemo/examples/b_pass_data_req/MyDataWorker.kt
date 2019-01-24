package com.rjz.workmanagerdemo.examples.b_pass_data_req

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rjz.workmanagerdemo.R

internal class MyDataWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        var desc = inputData.getString(DataRequestActivity.KEY_TASK_DESC) as String

        sendNotification("test task Pass data", desc);

        // pass output data
        var outputData = Data.Builder()
            .putString(DataRequestActivity.KEY_TASK_OUTPUT,"---all is done---")
            .build()

        setOutputData(outputData)
        return Result.SUCCESS
    }

    private fun sendNotification(task: String, msg: String) {

        // In "O" version 26 and > , must need to create channel for notification.
        //notification service
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // manage channel
            var channel = NotificationChannel("id1", "name1", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel);
        }

        val builder = NotificationCompat.Builder(applicationContext, "testChannel")
            .setContentTitle(task)
            .setContentText(msg)
            .setSmallIcon(R.mipmap.ic_launcher)

        manager.notify(1, builder.build())
    }
}
