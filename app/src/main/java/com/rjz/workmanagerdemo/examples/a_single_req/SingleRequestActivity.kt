package com.rjz.workmanagerdemo.examples.a_single_req

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.rjz.workmanagerdemo.R
import kotlinx.android.synthetic.main.activity_main.*

/*task one*/
internal class SingleRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setText("Single Request")

        // simple call work
        val request = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        button.setOnClickListener {
            // call work
            WorkManager.getInstance().enqueue(request)
        }

        // check status of work
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null) {
                    textView.append("\nStatus -> " + workInfo.state.name)
                }
            })
    }
}

