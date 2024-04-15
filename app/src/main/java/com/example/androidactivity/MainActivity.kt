package com.example.androidactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val enterTextEditText by lazy { findViewById<EditText>(R.id.enter_text_edit_text) }
    private val sendTextButton by lazy { findViewById<Button>(R.id.send_text_button) }
    private val enterNumberEditText by lazy { findViewById<EditText>(R.id.enter_number_edit_text) }
    private val sendNumberButton by lazy { findViewById<Button>(R.id.send_number_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendTextButton.setOnClickListener {
            startSecondActivityWithData(enterTextEditText.text.toString())
        }

        sendNumberButton.setOnClickListener {
            sendImplicitIntentNumberPhone(enterNumberEditText.text.toString())
        }

        savedInstanceState?.let { restoreState(it) }
    }

    private fun startSecondActivityWithData(text: String) {
        val intent = SecondActivity.newInstance(this, text)
        startActivity(intent)
    }

    private fun sendImplicitIntentNumberPhone(number: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
        }
        startActivity(intent)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        enterTextEditText.setText(savedInstanceState.getString(ENTER_TEXT_EDIT_TEXT))
        enterNumberEditText.setText(savedInstanceState.getString(ENTER_NUMBER_EDIT_TEXT))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ENTER_TEXT_EDIT_TEXT, enterTextEditText.text.toString())
        outState.putString(ENTER_NUMBER_EDIT_TEXT, enterNumberEditText.text.toString())
    }

    companion object {
        const val ENTER_TEXT_EDIT_TEXT = "enter_text_edit_text"
        const val ENTER_NUMBER_EDIT_TEXT = "enter_number_edit_text"
    }
}
