package com.ijniclohot.goodapplication

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

abstract class BaseActivity : AppCompatActivity() {
     lateinit var mProgressBar: ProgressBar

    override fun setContentView(layoutResId: Int) {
        val constraintLayout =
            layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout

        val frameLayout = constraintLayout.findViewById<FrameLayout>(R.id.activity_content)
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar)

        layoutInflater.inflate(layoutResId, frameLayout, true)

        super.setContentView(constraintLayout)
    }

    fun showProgressBar(visibility: Boolean) {
        if (visibility){
            mProgressBar.visibility = View.VISIBLE
        }else{
            mProgressBar.visibility = View.INVISIBLE
        }
    }

}