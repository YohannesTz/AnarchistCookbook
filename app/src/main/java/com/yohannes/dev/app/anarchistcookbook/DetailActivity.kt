package com.yohannes.dev.app.anarchistcookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.io.IOException
import java.nio.charset.Charset

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemIndex: String = intent.getStringExtra("data").toString()
        Log.e("itemIndex", itemIndex)
        val content = loadStringfromText(itemIndex)

        val contentTextView = findViewById<TextView>(R.id.contentText)
        contentTextView.text = content
    }

    private fun loadStringfromText(itemIndex: String): String {
        val text: String?
        try {
            val inputStream = assets.open(itemIndex)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            text = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }

        return text
    }
}