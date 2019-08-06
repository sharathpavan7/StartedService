package com.example.startedservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button to start Service
        btnStartService.setOnClickListener {
            intent = Intent(this, DelayedMessageService::class.java)
            intent.putExtra(DelayedMessageService.MESSAGE, getString(R.string.question))
            startService(intent)
        }
    }
}
