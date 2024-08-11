package com.example.cupidscafe.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cupidscafe.Adapter.CartAdapter
import com.example.cupidscafe.Helper.ChangeNumberItemsListener
import com.example.cupidscafe.R
import com.example.cupidscafe.databinding.ActivityCartBinding
import com.example.project1762.Helper.ManagmentCart

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var management: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        setContentView(R.layout.activity_cart)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        management = ManagmentCart(this)
        calculateCart()
        setVariable()
        initCartList()
    }

    private fun initCartList() {
        with(binding){
            cartView.layoutManager=LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL,false)
            cartView.adapter=CartAdapter(management.getListCart(),this@CartActivity, object :ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }

            })
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener{
            finish()
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        val totalFee = management.getTotalFee()

        tax = Math.round((totalFee * percentTax) * 100) / 100.0
        val total = Math.round((totalFee + tax + delivery) * 100) / 100
        val itemTotal = Math.round(totalFee * 100) / 100

        with(binding) {
            totalFeeTxt.text="$$itemTotal"
            taxTxt.text="$$tax"
            deliverTxt.text="$$delivery"
            totalTxt.text="$$total"
        }
    }
}
