package com.example.cupidscafe.Activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cupidscafe.Adapter.CategoryAdapter
import com.example.cupidscafe.Adapter.OfferAdapter
import com.example.cupidscafe.Adapter.PopularAdapter
import com.example.cupidscafe.R
import com.example.cupidscafe.ViewModel.MainViewModel
import com.example.cupidscafe.databinding.ActivityIntroBinding
import com.example.cupidscafe.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategory()
        initPopular()
        initOffers()
        bottomMenu()
        //setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    private fun bottomMenu() {
        binding.cartBtn.setOnClickListener{startActivity(Intent(this, CartActivity::class.java))}
    }

    private fun initOffers() {
        binding.progressBarOffer.visibility=View.VISIBLE
        viewModel.offer.observe(this, Observer{
            binding.recyclerViewOffer.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.recyclerViewOffer.adapter=OfferAdapter(it)
            binding.progressBarOffer.visibility=View.GONE
        })
        viewModel.loadOffer()
    }

    private fun initPopular() {
      binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.popular.observe(this, Observer{
            binding.recyclerViewPopular.layoutManager=
                LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.recyclerViewPopular.adapter= PopularAdapter(it)
                binding.progressBarPopular.visibility=View.GONE
        })
        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.recyclerViewCategory.layoutManager=
                LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }
}