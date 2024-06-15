package com.androidacademy.premierleaguefixtures
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.button_back_to_first).setOnClickListener {
            navigateBackToFirstActivity()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigateBackToFirstActivity()
    }

    private fun navigateBackToFirstActivity() {
        val intent = Intent(this, FirstActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish() // Optionally finish the current activity
    }
}