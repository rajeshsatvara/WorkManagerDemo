package com.rjz.workmanagerdemo.examples.b_pass_data_req

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.rjz.workmanagerdemo.R
import kotlinx.android.synthetic.main.activity_main.*


class DataRequestActivity : AppCompatActivity() {

    companion object {
        var KEY_TASK_DESC = "taskOne"
        var KEY_TASK_OUTPUT = "taskOneOutput"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setText("Data Pass Request")

        val data = Data.Builder()
            .putString(KEY_TASK_DESC, "Hey this is task one")
            .build()

        val request = OneTimeWorkRequest.Builder(MyDataWorker::class.java)
            .setInputData(data)
            .build()

        button.setOnClickListener {
            // call work with pass some data
            WorkManager.getInstance().enqueue(request)
        }

        // check status of work
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null) {
                    textView.append("\nStatus -> " + workInfo.state.name)

                    if (workInfo.state.isFinished) {
                        // get output data using key
                        var outPutData = workInfo.outputData.getString(KEY_TASK_OUTPUT) as String
                        textView.append("\n\nOutput is -> " + outPutData)
                    }
                }
            })
    }
}

