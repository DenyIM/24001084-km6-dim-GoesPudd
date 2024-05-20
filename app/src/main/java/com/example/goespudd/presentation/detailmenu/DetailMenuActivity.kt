package com.example.goespudd.presentation.detailmenu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.goespudd.R
import com.example.goespudd.data.model.Menu
import com.example.goespudd.databinding.ActivityDetailMenuBinding
import com.example.goespudd.databinding.LayoutBtnOrderMenuBinding
import com.example.goespudd.databinding.LayoutDetailItemMenuBinding
import com.example.goespudd.databinding.LayoutDetailLocShopMenuBinding
import com.example.goespudd.databinding.LayoutDetailOrderMenuBinding
import com.example.goespudd.utils.proceedWhen
import com.example.goespudd.utils.toIndonesianFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailMenuActivity : AppCompatActivity() {
    private val binding: ActivityDetailMenuBinding by lazy {
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

    private val viewModel: DetailMenuViewModel by viewModel {
        parametersOf(intent.extras)
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
        detailBtnOrderMenuBinding.clBtnOrder.setOnClickListener {
            addProductToCart()
        }
    }

    private fun addProductToCart() {
        viewModel.addToCart().observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    Toast.makeText(
                        this,
                        getString(R.string.text_add_to_cart_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                    finish()
                },
                doOnError = {
                    Toast.makeText(this, getString(R.string.add_to_cart_failed), Toast.LENGTH_SHORT)
                        .show()
                },
                doOnLoading = {
                    Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show()
                },
            )
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
            if (detailLocShopMenuBinding.tvDetailDescMenu.text.isNotEmpty()) {
                val uri = Uri.parse(urlMaps)
                intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
            }
        }
    }

    private fun observeData() {
        viewModel.priceLiveData.observe(this) {
            detailBtnOrderMenuBinding.clBtnOrder.isEnabled = it != 0.0
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
