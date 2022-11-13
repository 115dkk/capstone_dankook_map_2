package com.example.capstone_dankook_map

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone_dankook_map.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val lat = intent.getDoubleExtra("lat", 37.3226546)
        val lon = intent.getDoubleExtra("lon", 127.1260339)
        var title = "단국대학교"
        var snippet = "한국 최고의 명문대학교"
        val change = intent.getBooleanExtra("change", false)
        val isinside = intent.getBooleanExtra("isinside", false)

        if(change) {
            title = intent.getStringExtra("title").toString()
            snippet = intent.getStringExtra("snippet").toString()
        }

        val dku = LatLng(lat, lon)
        val markerOptions = MarkerOptions() // 마커 생성
        markerOptions.position(dku)
        markerOptions.title(title) // 마커 제목
        markerOptions.snippet(snippet) // 마커 설명
        mMap.addMarker(markerOptions)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(dku)) // 초기 위치
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f)) // 줌의 정도

        if(isinside) {
            Handler().postDelayed({
                mMap.setOnMarkerClickListener {
                    when (intent.getStringExtra("intent").toString()) {
                        "InsideActivity" ->
                            intent = Intent(applicationContext, InsideActivity::class.java)
                        "InsideActivity2" ->
                            intent = Intent(applicationContext, InsideActivity2::class.java)
                    }
                    //var intent = Intent(applicationContext, BuildingsActivity::class.java)

                    startActivity(intent)
                    false
                }
            }, (3000).toLong())
        }
    }

 //   fun changeMapReady(googleMap: GoogleMap) {
 //       mMap = googleMap
//
//        val lat = intent.getDoubleExtra("lat", 37.3226546)
//        val lon = intent.getDoubleExtra("lon", 127.1260339)
//        var title = "단국대학교"
//        var snippets = "한국 체고의 명문머학교"
//
//        var change = intent.getBooleanExtra("change", false)
//
//        val dku = LatLng(lat, lon)
//        val markerOptions = MarkerOptions() // 마커 생성
//        markerOptions.position(dku)
//        markerOptions.title(title) // 마커 제목
//        markerOptions.snippet(snippets) // 마커 설명
//        mMap.addMarker(markerOptions)
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(dku)) // 초기 위치
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f)) // 줌의 정도
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, SplashActivity::class.java)
        val buildingIntent = Intent(applicationContext, BuildingsActivity::class.java)
        when (item?.itemId) {
            R.id.dku_buildings -> startActivity(buildingIntent)
            R.id.search_road -> startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
}