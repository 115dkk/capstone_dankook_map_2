package com.example.capstone_dankook_map

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class InsideActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inside)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.ictinsidemenu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val imageview = findViewById<ImageView>(R.id.imageView3)

        when (item?.itemId) {
            R.id.b3f -> imageview.setImageResource(R.mipmap.ict_b1f)
            R.id.b2f -> imageview.setImageResource(R.mipmap.ict_1f)
            R.id.b1f -> imageview.setImageResource(R.mipmap.ict_2f)
            R.id.u1f -> imageview.setImageResource(R.mipmap.ict_3f)
            R.id.u2f -> imageview.setImageResource(R.mipmap.ict_4f)
            R.id.u3f -> imageview.setImageResource(R.mipmap.ict_5f)
        }
        return super.onOptionsItemSelected(item)
    }
}