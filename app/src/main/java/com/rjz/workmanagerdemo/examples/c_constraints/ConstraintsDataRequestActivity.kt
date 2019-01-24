package com.rjz.workmanagerdemo.examples.c_constraints

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.rjz.workmanagerdemo.R
import kotlinx.android.synthetic.main.activity_main.*


class ConstraintsDataRequestActivity : AppCompatActivity() {

    companion object {
        var KEY_TASK_DESC = "taskTwo"
        var KEY_TASK_OUTPUT = "taskTwoOutput"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setText("Constraints Request")

        //add work
        val data = Data.Builder()
            .putString(KEY_TASK_DESC, "Hey this is task constraints")
            .build()

        //add Constraints
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val request = OneTimeWorkRequest.Builder(MyConstraintDataWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
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

