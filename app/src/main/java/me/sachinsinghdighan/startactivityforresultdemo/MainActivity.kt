package me.sachinsinghdighan.startactivityforresultdemo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var openSecondActivityButton: Button

    /*companion object {
        private const val REQUEST_CODE_SECOND_ACTIVITY = 100
    }*/


    // Modern way to handle activity results
    @SuppressLint("SetTextI18n")
    private val secondActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val data = result.data
                val message = data?.getStringExtra("result_message") ?: "No message"
                val number = data?.getIntExtra("result_number", 0) ?: 0

                resultTextView.text = "Result: $message, Number: $number"
            }
            Activity.RESULT_CANCELED -> {
                resultTextView.text = "Operation was cancelled"
            }
            else -> {
                resultTextView.text = "Unknown result code: ${result.resultCode}"
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        openSecondActivityButton = findViewById(R.id.openSecondActivityButton)

        openSecondActivityButton.setOnClickListener {
            // Create intent to start SecondActivity
            val intent = Intent(this, SecondActivity::class.java)

            // You can pass data to the second activity
            intent.putExtra("user_name", "John Doe")
            intent.putExtra("user_age", 25)

            // Launch activity using the modern approach
            secondActivityResultLauncher.launch(intent)

            // Start activity for result (DEPRECATED - see modern approach below)
            //@Suppress("DEPRECATION")
            //startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY)
        }
    }


    /*@SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_SECOND_ACTIVITY -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        // Get data from the returning activity
                        val message = data?.getStringExtra("result_message") ?: "No message"
                        val number = data?.getIntExtra("result_number", 0) ?: 0

                        resultTextView.text = "Result: $message, Number: $number"
                    }
                    Activity.RESULT_CANCELED -> {
                        resultTextView.text = "Operation was cancelled"
                    }
                    else -> {
                        resultTextView.text = "Unknown result code: $resultCode"
                    }
                }
            }
        }
    }*/
}