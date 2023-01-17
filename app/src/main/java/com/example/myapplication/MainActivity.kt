package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vueApp = findViewById<WebView>(R.id.myVueApp)
            vueApp.settings.javaScriptEnabled = true
            vueApp.loadUrl("http://192.168.1.216:8080/")
            findViewById<Button>(R.id.myBtn).setOnClickListener {
                vueApp.evaluateJavascript("document.dispatchEvent(new Event(\"vueIncrease\"))",null)
            }
            vueApp.addJavascriptInterface(myVueAppInterface(this),"androidApp")
    }
    class myVueAppInterface (private val ctx:Context) {
        @JavascriptInterface
        fun toastText(txt: String) {
            Toast.makeText(ctx,txt,Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun func2(txt: String) {
            Toast.makeText(ctx,txt,Toast.LENGTH_SHORT).show()
        }
    }
}
