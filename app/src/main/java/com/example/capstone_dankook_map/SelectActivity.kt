package com.example.capstone_dankook_map

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity


class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_select)

        val rounge = findViewById<Button>(R.id.rounge)
        val info = findViewById<Button>(R.id.info_room)
        val check = findViewById<Button>(R.id.checkout)
        val ctl = findViewById<Button>(R.id.ctl)
        val center = findViewById<Button>(R.id.center)

        intent = Intent(applicationContext, InsideActivity::class.java)

        rounge.setOnClickListener{
            intent.putExtra("target", "rounge")
            intent.putExtra("ans", "중앙 유리문을 통과하여 왼쪽으로 이동하면 그루터기 옆 도산라운지가 보입니다.")

            startActivity(intent)
        }
        info.setOnClickListener{
            intent.putExtra("target", "info")
            intent.putExtra("ans", "중앙 유리문을 통과하면 도산라운지와 대출실 사이 정보검색실이 보입니다.")

            startActivity(intent)
        }
        check.setOnClickListener{
            intent.putExtra("target", "check")
            intent.putExtra("ans", "중앙 유리문을 통과하여 오른쪽으로 이동하면 엘리베이터 앞 대출실이 보입니다.")

            startActivity(intent)
        }
        ctl.setOnClickListener{
            intent.putExtra("target", "CTL")
            intent.putExtra("ans", "정문에서 왼쪽에 존재하는 들샘길계단 앞으로 이동한 후, ATM쪽으로 이동하면 CTL이 보입니다.")

            startActivity(intent)
        }
        center.setOnClickListener{
            intent.putExtra("target", "center")
            intent.putExtra("ans", "정문에서 오른쪽에 존재하는 언덕계단 앞으로 이동한 후, 경비실 옆 통로 끝 대학생활상담센터가 보입니다.")

            startActivity(intent)
        }
    }
}