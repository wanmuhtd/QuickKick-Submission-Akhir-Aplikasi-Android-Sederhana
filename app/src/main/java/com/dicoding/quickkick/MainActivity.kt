package com.dicoding.quickkick

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvShoes: RecyclerView
    private val list = ArrayList<Shoes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.toolbar_color, theme)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val aboutPage: ImageView = findViewById(R.id.about_page)
        aboutPage.setOnClickListener {
            val intent = Intent(this, AboutMeActivity::class.java)
            startActivity(intent)
        }

        rvShoes = findViewById(R.id.rv_shoes)
        rvShoes.setHasFixedSize(true)
        list.addAll(getListShoes())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            rvShoes.layoutManager = GridLayoutManager(this, 3)
        } else {
            rvShoes.layoutManager = LinearLayoutManager(this)
        }
        val listShoesAdapter = ListShoesAdapter(list)
        rvShoes.adapter = listShoesAdapter

        listShoesAdapter.setOnItemClickCallBack(object : ListShoesAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Shoes) {
                showSelectedShoes(data)
            }
        })
    }

    private fun showSelectedShoes(shoes: Shoes) {
        Toast.makeText(this, "You choose " + shoes.name, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Recycle")
    private fun getListShoes(): ArrayList<Shoes> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataType = resources.getStringArray(R.array.data_type)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataColor = resources.getStringArray(R.array.data_color)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listShoes = ArrayList<Shoes>()
        for (i in dataName.indices) {
            val shoes = Shoes(dataName[i], dataType[i], dataPrice[i], dataColor[i], dataDescription[i],dataPhoto.getResourceId(i, -1))
            listShoes.add(shoes)
        }
        return listShoes
    }
}