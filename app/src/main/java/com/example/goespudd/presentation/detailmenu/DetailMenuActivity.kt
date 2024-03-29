package com.example.goespudd.presentation.detailmenu

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.example.goespudd.data.model.Menu
import com.example.goespudd.databinding.ActivityDetailMenuBinding
import com.example.goespudd.databinding.LayoutBtnOrderMenuBinding
import com.example.goespudd.databinding.LayoutDetailItemMenuBinding
import com.example.goespudd.databinding.LayoutDetailLocShopMenuBinding
import com.example.goespudd.databinding.LayoutDetailOrderMenuBinding
import com.example.goespudd.utils.toIndonesianFormat


class DetailMenuActivity : AppCompatActivity() {
    private val binding : ActivityDetailMenuBinding by lazy {
        ActivityDetailMenuBinding.inflate(layoutInflater)
    }

    private val detailItemMenuBinding: LayoutDetailItemMenuBinding by lazy {
        LayoutDetailItemMenuBinding.bind(binding.layoutDetailItemMenu.root)
    }

    private val detailLocShopMenuBinding: LayoutDetailLocShopMenuBinding by lazy {
        LayoutDetailLocShopMenuBinding.bind(binding.layoutDetailLocShopMenu.root)
    }

    private val detailOrderMenu: LayoutDetailOrderMenuBinding by lazy {
        LayoutDetailOrderMenuBinding.bind(binding.layoutDetailOrderMenu.root)
    }

    private val detailBtnOrderMenuBinding: LayoutBtnOrderMenuBinding by lazy {
        LayoutBtnOrderMenuBinding.bind(detailOrderMenu.layoutBtnOrder.root)
    }

    private val viewModel: DetailMenuViewModel by viewModels {
        GenericViewModelFactory.create(
            DetailMenuViewModel(intent?.extras)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindMenu(viewModel.menu)
        setClickListener()
        observeData()
    }

    private fun setClickListener() {
        binding.ibBackToHome.setOnClickListener {
            onBackPressed()
        }
        detailOrderMenu.ivMinus.setOnClickListener {
            viewModel.minus()
        }
        detailOrderMenu.ivPlus.setOnClickListener {
            viewModel.add()
        }
    }
    private fun bindMenu(menu: Menu?) {
        menu?.let { item ->
            detailItemMenuBinding.ivBannerImageMenu.load(item.imgUrl) {
                crossfade(true)
            }
            detailItemMenuBinding.tvDetailNameMenu.text = item.name
            detailItemMenuBinding.tvDetailDescMenu.text = item.desc
            detailItemMenuBinding.tvDetailPriceMenu.text = item.price.toIndonesianFormat()
            detailLocShopMenuBinding.tvDetailDescMenu.text = item.shopLoc
            openLocation(item.mapsLoc)
        }
    }

    private fun openLocation(urlMaps: String) {
        detailLocShopMenuBinding.tvDetailDescMenu.setOnClickListener {
            if (detailLocShopMenuBinding.tvDetailDescMenu.text.isNotEmpty()){
                val uri = Uri.parse(urlMaps)
                intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            }
        }
    }

    private fun observeData() {
        viewModel.priceLiveData.observe(this) {
            detailBtnOrderMenuBinding.tvOrderPrice.text = it.toIndonesianFormat()
        }
        viewModel.menuCountLiveData.observe(this) {
            detailOrderMenu.tvCount.text = it.toString()
        }
    }
    companion object {
        const val EXTRA_MENU = "EXTRA_MENU"
    }
}