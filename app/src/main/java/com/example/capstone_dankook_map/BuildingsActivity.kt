package com.example.capstone_dankook_map

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class BuildingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_building)
        val door = findViewById<Button>(R.id.door_front)
        val lib = findViewById<Button>(R.id.Library)
        val ict = findViewById<Button>(R.id.software)
        val intent = Intent(applicationContext, MapsActivity::class.java)

        door.setOnClickListener{

            intent.putExtra("lat", 37.323476641)
            intent.putExtra("lon", 127.12550096)
            intent.putExtra("title", "단국대학교 정문")
            intent.putExtra("snippet", "단국대의 관문")
            intent.putExtra("change", true)
            intent.putExtra("isinside", false)

            startActivity(intent)
        }
        lib.setOnClickListener {
            intent.putExtra("lat", 37.321168128)
            intent.putExtra("lon", 127.1274602)
            intent.putExtra("title", "퇴계기념중앙도서관")
            intent.putExtra("snippet", "조용하게 공부할 수 있는 곳")
            intent.putExtra("change", true)
            intent.putExtra("isinside", true)
            intent.putExtra("intent", "InsideActivity")

            startActivity(intent)
        }
        ict.setOnClickListener {
            intent.putExtra("lat", 37.321168128)
            intent.putExtra("lon", 127.1274602)
            intent.putExtra("title", "ICT 미디어센터")
            intent.putExtra("snippet", "단국대 소프트웨어의 본부")
            intent.putExtra("change", true)
            intent.putExtra("isinside", true)
            intent.putExtra("intent", "InsideActivity2")

            startActivity(intent)
        }
    }
}