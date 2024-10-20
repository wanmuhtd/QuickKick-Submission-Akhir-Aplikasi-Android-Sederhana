package com.dicoding.quickkick

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        window.statusBarColor = resources.getColor(R.color.toolbar_color, theme)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val shoes = intent.getParcelableExtra<Shoes>("shoes")

        val ivShoesDetail: ImageView = findViewById(R.id.iv_shoes_detail)
        val tvItemName: TextView = findViewById(R.id.tv_item_name)
        val tvItemType: TextView = findViewById(R.id.tv_item_type)
        val tvItemPrice: TextView = findViewById(R.id.tv_item_price)
        val tvItemColor: TextView = findViewById(R.id.tv_item_color)
        val tvItemDescription: TextView = findViewById(R.id.tv_item_description)

        tvItemName.text = shoes?.name ?: ""
        tvItemType.text = shoes?.type ?: ""
        tvItemPrice.text = shoes?.price ?: ""
        tvItemDescription.text = shoes?.description ?: ""
        tvItemColor.text = shoes?.color ?: ""
        ivShoesDetail.setImageResource(shoes?.photo ?: 0)

        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        val shareAction: TextView = findViewById(R.id.action_share)
        shareAction.setOnClickListener {
            shareItem(shoes)
            true
        }
    }

    private fun shareItem(shoes: Shoes?) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out these shoes!")
            putExtra(
                Intent.EXTRA_TEXT, """
                Name: ${shoes?.name}
                Type: ${shoes?.type}
                Price: ${shoes?.price}
                Description: ${shoes?.description}
                Color: ${shoes?.color}
            """.trimIndent()
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}