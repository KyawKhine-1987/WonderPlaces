package com.android.freelance.famousplaces.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.android.freelance.famousplaces.R
import com.android.freelance.famousplaces.ui.viewmodel.WondersDetailViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_wonders_detail.*

class WondersDetailActivity : AppCompatActivity() {

    private val LOG_TAG = WondersDetailActivity::class.java.name

    private val viewModel by lazy { ViewModelProviders.of(this).get(WondersDetailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST: onCreate() called...")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wonders_detail)

        //viewModel = ViewModelProviders.of(this).get(WondersDetailViewModel::class.java)

        relevantDataBindingInUI()


    }

    private fun relevantDataBindingInUI() {
        Log.i(LOG_TAG, "TEST: relevantDataBindingInUI() is called...")

        val wondersDetailImage: ImageView = this.findViewById(R.id.ivDetailWonders)
        val wondersDetailTitle: TextView = this.findViewById(R.id.tvDetailLocation)
        val wondersDetailDesp: TextView = this.findViewById(R.id.tvDetailDescription)

        val imageData = intent.getStringExtra("image")
        Glide.with(this).load(imageData).into(wondersDetailImage)

        wondersDetailTitle.text = intent.getStringExtra("title")
        wondersDetailDesp.text = intent.getStringExtra("desp")
       /* Glide.with(this).load(viewModel.detailImage).into(ivDetailWonders)
        tvDetailLocation.text = viewModel.detailLocation
        tvDetailDescription.text = viewModel.detailDescription*/
    }
}
