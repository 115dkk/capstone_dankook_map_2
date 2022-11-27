package com.example.capstone_dankook_map

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.capstone_dankook_map.astar.AStar
import com.example.capstone_dankook_map.astar.Node
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class InsideActivity : AppCompatActivity() {

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f
    private lateinit var mImageView: ImageView

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inside)

        val btn = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.textView)
        val crawl1 = findViewById<Button>(R.id.crawl1)
        val crawl2 = findViewById<Button>(R.id.crawl2)
        val crawl3 = findViewById<Button>(R.id.crawl3)
        val crawl4 = findViewById<Button>(R.id.crawl4)
        val crawl5 = findViewById<Button>(R.id.crawl5)

        val imageview = findViewById<ImageView>(R.id.imageView3)

        mImageView= findViewById(R.id.imageView3)


        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())


        btn.setOnClickListener {
            intent = Intent(applicationContext, SelectActivity::class.java)
            startActivity(intent)
        }

        val target = intent.getStringExtra("target")
        val ans = intent.getStringExtra("ans")

        when (target) {
            "rounge" -> {
                imageview.setImageResource(R.mipmap.lib_fl_3_a)
                text.text = ans
            }
            "info" -> {
                imageview.setImageResource(R.mipmap.lib_fl_3_b)
                text.text = ans
            }
            "check" -> {
                imageview.setImageResource(R.mipmap.lib_fl_3_c)
                text.text = ans
            }
            "CTL" -> {
                imageview.setImageResource(R.mipmap.lib_fl_3_d)
                text.text = ans
            }
            "center" -> {
                imageview.setImageResource(R.mipmap.lib_fl_3_e)
                text.text = ans
            }
        }

        val dkuDB =
            Room.databaseBuilder(applicationContext, DKUMapLibDB::class.java, "DK_MAP_LIB_FLOOR")
                .createFromAsset("databases/DKU_Lib_Kor.db").build()

        CoroutineScope(Dispatchers.IO).launch {
            dkuDB.dklibDAO().delete(DKLib(0,"","","","","",""))
        }
        CoroutineScope(Dispatchers.IO).launch {
            dkuDB.dklibDAO().getAll()
        }
            crawl1.visibility = View.VISIBLE
            crawl1.setBackgroundColor(Color.TRANSPARENT)

            crawl1.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://lib.dankook.ac.kr/local/html/studyRoomGuide3")
                )
                startActivity(intent)
            }

            crawl2.visibility = View.VISIBLE
            crawl2.setBackgroundColor(Color.TRANSPARENT)

            crawl2.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://lib.dankook.ac.kr/search/tot")
                )
                startActivity(intent)
            }

            crawl3.visibility = View.VISIBLE
            crawl3.setBackgroundColor(Color.TRANSPARENT)

            crawl3.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://cms.dankook.ac.kr/web/dkcounsel")
                )
                startActivity(intent)
            }

            crawl4.visibility = View.VISIBLE
            crawl4.setBackgroundColor(Color.TRANSPARENT)

            crawl4.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://lib.dankook.ac.kr/local/html/loanGuide")
                )
                startActivity(intent)
            }

            crawl5.visibility = View.VISIBLE
            crawl5.setBackgroundColor(Color.TRANSPARENT)

            crawl5.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://lib.dankook.ac.kr/local/html/IDCardGuide")
                )
                startActivity(intent)
            }
        }

    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {

        // 제스처 이벤트를 처리하는 메소드를 호출
        mScaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    // 제스처 이벤트를 처리하는 클래스
    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {

            scaleFactor *= scaleGestureDetector.scaleFactor

            // 최소 0.5, 최대 2배
            scaleFactor = Math.max(0.5f, Math.min(scaleFactor, 2.0f))

            // 이미지에 적용
            mImageView.scaleX = scaleFactor
            mImageView.scaleY = scaleFactor
            return true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.libraryinsidemenu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val imageview = findViewById<ImageView>(R.id.imageView3)
        val text = findViewById<TextView>(R.id.textView)

        when (item.itemId) {
            R.id.b2f -> {
                imageview.setImageResource(R.mipmap.lib_fl_1)
                text.text = ""
            }
            R.id.b1f -> {
                imageview.setImageResource(R.mipmap.lib_fl_2)
                text.text = ""
            }
            R.id.u1f -> {
                imageview.setImageResource(R.mipmap.lib_fl_3)
                text.text = ""
            }
            R.id.u2f -> {
                imageview.setImageResource(R.mipmap.lib_fl_4)
                text.text = ""
            }
            R.id.u3f -> {
                imageview.setImageResource(R.mipmap.lib_fl_5)
                text.text = ""
            }
            R.id.u4f -> {
                imageview.setImageResource(R.mipmap.lib_fl_6)
                text.text = ""
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun astar(a:Int, b:Int): String {
        val initialNode = Node(10, 11)
        val finalNode = Node(a, b)
        val rows = 20
        val cols = 20
        val aStar = AStar(rows, cols, initialNode, finalNode)
        val blocksArray = arrayOf(
            intArrayOf(0, 5),
            intArrayOf(0, 6),
            intArrayOf(0, 7),
            intArrayOf(0, 8),
            intArrayOf(0, 9),
            intArrayOf(0, 10),
            intArrayOf(0, 11),
            intArrayOf(0, 16),
            intArrayOf(0, 19),
            intArrayOf(1, 5),
            intArrayOf(1, 6),
            intArrayOf(1, 7),
            intArrayOf(1, 8),
            intArrayOf(1, 9),
            intArrayOf(1, 10),
            intArrayOf(1, 11),
            intArrayOf(1, 16),
            intArrayOf(1, 19),
            intArrayOf(2, 5),
            intArrayOf(2, 11),
            intArrayOf(2, 19),
            intArrayOf(3, 5),
            intArrayOf(3, 6),
            intArrayOf(3, 7),
            intArrayOf(3, 9),
            intArrayOf(3, 10),
            intArrayOf(3, 11),
            intArrayOf(3, 13),
            intArrayOf(3, 14),
            intArrayOf(3, 15),
            intArrayOf(3, 16),
            intArrayOf(3, 17),
            intArrayOf(3, 18),
            intArrayOf(3, 19),
            intArrayOf(4, 0),
            intArrayOf(4, 1),
            intArrayOf(4, 2),
            intArrayOf(4, 3),
            intArrayOf(4, 17),
            intArrayOf(5, 0),
            intArrayOf(6, 0),
            intArrayOf(6, 18),
            intArrayOf(6, 19),
            intArrayOf(7, 0),
            intArrayOf(7, 1),
            intArrayOf(7, 2),
            intArrayOf(7, 3),
            intArrayOf(7, 4),
            intArrayOf(7, 5),
            intArrayOf(7, 18),
            intArrayOf(7, 19),
            intArrayOf(8, 2),
            intArrayOf(8, 5),
            intArrayOf(8, 15),
            intArrayOf(8, 16),
            intArrayOf(8, 17),
            intArrayOf(8, 18),
            intArrayOf(8, 19),
            intArrayOf(9, 0),
            intArrayOf(9, 2),
            intArrayOf(9, 3),
            intArrayOf(9, 4),
            intArrayOf(9, 5),
            intArrayOf(9, 15),
            intArrayOf(10, 9),
            intArrayOf(10, 11),
            intArrayOf(10, 15),
            intArrayOf(11, 2),
            intArrayOf(11, 4),
            intArrayOf(11, 5),
            intArrayOf(11, 7),
            intArrayOf(11, 9),
            intArrayOf(11, 11),
            intArrayOf(11, 13),
            intArrayOf(11, 15),
            intArrayOf(11, 16),
            intArrayOf(11, 17),
            intArrayOf(11, 18),
            intArrayOf(11, 19),
            intArrayOf(12, 0),
            intArrayOf(12, 1),
            intArrayOf(12, 2),
            intArrayOf(12, 4),
            intArrayOf(12, 7),
            intArrayOf(12, 9),
            intArrayOf(12, 10),
            intArrayOf(12, 11),
            intArrayOf(12, 13),
            intArrayOf(13, 4),
            intArrayOf(13, 7),
            intArrayOf(13, 9),
            intArrayOf(13, 10),
            intArrayOf(13, 11),
            intArrayOf(13, 13),
            intArrayOf(14, 4),
            intArrayOf(14, 7),
            intArrayOf(14, 9),
            intArrayOf(14, 10),
            intArrayOf(14, 11),
            intArrayOf(14, 13)
        )
        aStar.setBlocks(blocksArray)
        val path = aStar.findPath()
        var ans = ""
        for (node in path) {
            ans += node
        }
        return ans
    }
}