package me.sachinsinghdighan.startactivityforresultdemo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var userInfoTextView: TextView
    private lateinit var messageEditText: EditText
    private lateinit var sendResultButton: Button
    private lateinit var cancelButton: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        userInfoTextView = findViewById(R.id.userInfoTextView)
        messageEditText = findViewById(R.id.messageEditText)
        sendResultButton = findViewById(R.id.sendResultButton)
        cancelButton = findViewById(R.id.cancelButton)

        // Get data passed from MainActivity
        val userName = intent.getStringExtra("user_name") ?: "Unknown"
        val userAge = intent.getIntExtra("user_age", 0)

        userInfoTextView.text = "Hello $userName, Age: $userAge"

        // Send result back to MainActivity
        sendResultButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result_message", messageEditText.text.toString())
            resultIntent.putExtra("result_number", 42)

            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Close this activity and return to MainActivity
        }

        // Cancel operation
        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}