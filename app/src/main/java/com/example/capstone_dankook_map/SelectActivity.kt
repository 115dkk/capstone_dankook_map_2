package com.example.capstone_dankook_map

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity


class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_building)
        intent = Intent(applicationContext, InsideActivity::class.java)

        val search = findViewById<SearchView>(R.id.searchview)
        val listView = findViewById<ListView>(R.id.listView)

        val names = arrayOf("도산라운지", "정보검색실","대출실","CTL","대학생활상담센터")

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

            val click_list = names[i]
            when(click_list){
                "도산라운지" ->{
                    intent.putExtra("target", "rounge")
                    intent.putExtra("ans", "중앙 유리문을 통과하여 왼쪽으로 이동하면 그루터기 옆 도산라운지가 보입니다.")

                    startActivity(intent)
                }
                "정보검색실" ->{
                    intent.putExtra("target", "info")
                    intent.putExtra("ans", "중앙 유리문을 통과하면 도산라운지와 대출실 사이 정보검색실이 보입니다.")

                    startActivity(intent)
                }
                "대출실" ->{
                    intent.putExtra("target", "check")
                    intent.putExtra("ans", "중앙 유리문을 통과하여 오른쪽으로 이동하면 엘리베이터 앞 대출실이 보입니다.")

                    startActivity(intent)
                }
                "CTL" ->{
                    intent.putExtra("target", "CTL")
                    intent.putExtra("ans", "정문에서 왼쪽에 존재하는 들샘길계단 앞으로 이동한 후, ATM쪽으로 이동하면 CTL이 보입니다.")

                    startActivity(intent)
                }
                "대학생활상담센터" ->{
                    intent.putExtra("target", "center")
                    intent.putExtra("ans", "정문에서 오른쪽에 존재하는 언덕계단 앞으로 이동한 후, 경비실 옆 통로 끝 대학생활상담센터가 보입니다.")

                    startActivity(intent)
                }
            }
        }
    }
}