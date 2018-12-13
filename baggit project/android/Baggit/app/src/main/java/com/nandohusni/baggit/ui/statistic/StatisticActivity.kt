package com.nandohusni.baggit.ui.statistic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nandohusni.baggit.R
import kotlinx.android.synthetic.main.activity_statistic.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.toolbar.*


class StatisticActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic)

        setSupportActionBar(toolbar)
        val bar = supportActionBar!!
        bar.setDisplayHomeAsUpEnabled(true)
        bar.setDisplayShowTitleEnabled(false)
        toolbar_title.text = getString(R.string.statixtic)

        val url = getString(R.string.url_statistic)
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = MyBrowser(progress)



        webview.loadUrl(url)
    }

    private inner class MyBrowser(val progressBar: ProgressBar) : WebViewClient() {

        private var progressBar2: ProgressBar? = null


        init {

            this.progressBar2 = progressBar
            progressBar.visibility = View.VISIBLE

        }


        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            // TODO Auto-generated method stub
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url)
            progressBar?.visibility = View.GONE
        }

    }
}
