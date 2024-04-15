package com.example.androidactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.myTextView)
        val receivedValue = savedInstanceState?.getString(EXTRA_DATA) ?: intent.getStringExtra(EXTRA_DATA)
        textView.text = receivedValue
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val textView = findViewById<TextView>(R.id.myTextView)
        outState.putString(EXTRA_DATA, textView.text.toString())
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"

        fun newInstance(context: Context, data: String): Intent {
            return Intent(context, SecondActivity::class.java)
                .putExtra(EXTRA_DATA, data)
        }
    }
}
