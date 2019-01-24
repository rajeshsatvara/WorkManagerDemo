package com.rjz.workmanagerdemo.examples

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rjz.workmanagerdemo.R
import com.rjz.workmanagerdemo.examples.c_constraints.ConstraintsDataRequestActivity
import com.rjz.workmanagerdemo.examples.b_pass_data_req.DataRequestActivity
import com.rjz.workmanagerdemo.examples.a_single_req.SingleRequestActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnSingle.setOnClickListener {
            startActivity(Intent(HomeActivity@ this, SingleRequestActivity::class.java))
        }
        btnPassData.setOnClickListener {
            startActivity(Intent(HomeActivity@ this, DataRequestActivity::class.java))
        }
        btnConstraint.setOnClickListener {
            startActivity(Intent(HomeActivity@ this, ConstraintsDataRequestActivity::class.java))
        }
    }
}
