package com.example.capstone_dankook_map

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class BuildingsActivity : AppCompatActivity() {

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_building)
        intent = Intent(applicationContext, MapsActivity::class.java)

        val search = findViewById<SearchView>(R.id.searchview)
        val listView = findViewById<ListView>(R.id.listView)

        val names = arrayOf("퇴계기념중앙도서관", "소프트웨어 ICT관", "미디어센터", "정문", "범정관", "혜당관(학생회관)",
            "사범관", "상경관", "인문관")

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1
            , names
        )

        listView.adapter = adapter

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search.clearFocus()
                if(names.contains(p0))
                {
                    adapter.filter.filter(p0)
                }else{

                    Toast.makeText(applicationContext, "Item not found", Toast.LENGTH_LONG).show()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return false
            }
        })
        listView.setOnItemClickListener { _, _, i, _ ->

            val clickedlist = names[i]
            if(clickedlist == "퇴계기념중앙도서관"){
                //intent = Intent(applicationContext, SelectActivity::class.java)
                //intent.putExtra("loc", "lib")
                intent.putExtra("lat", 37.321168128)
                intent.putExtra("lon", 127.1274602)
                intent.putExtra("title", "퇴계기념중앙도서관")
                intent.putExtra("change", true)
                intent.putExtra("inside", true)
                intent.putExtra("intent", "InsideActivity")

                startActivity(intent)
            }
            if(clickedlist == "정문"){
                intent.putExtra("lat", 37.323476641)
                intent.putExtra("lon", 127.12550096)
                intent.putExtra("title", "단국대학교 정문")
                intent.putExtra("snippet", "단국대의 관문")
                intent.putExtra("change", true)
                intent.putExtra("inside", false)

                startActivity(intent)
            }
            if(clickedlist == "소프트웨어 ICT관"||clickedlist == "미디어센터"||clickedlist == "범정관"||clickedlist == "혜당관(학생회관)"||clickedlist == "인문관"||clickedlist == "사범관"||clickedlist == "상경관"){
                //val toast = Toast.makeText(this, "서비스 준비중입니다.", Toast.LENGTH_SHORT)
                //toast.show()
                val layout = findViewById<View>(R.id.BuildLayout)
                Snackbar.make(layout, "서비스 준비중입니다.", 250).show()
            }
        }
    }
}