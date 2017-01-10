package me.walmyrcarvalho.styledmaps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

  companion object {
    val CAMPUS: LatLng = LatLng(-23.570991, -46.649886) // LatLng of Google Campus - São Paulo
    val ZOOM = 30f // Zoom level
  }

  val mapFragment by lazy { supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_maps)

    setupToolbar()
    setupMap()
  }

  override fun onMapReady(googleMap: GoogleMap) {
    googleMap.apply {

      // Adding a marker
      addMarker(MarkerOptions().position(CAMPUS).title("Hello, Campus!"))

      // Zoom
      moveCamera(CameraUpdateFactory.newLatLngZoom(CAMPUS, ZOOM))
    }
  }

  fun setupMap() {
    mapFragment.getMapAsync(this)
  }

  fun setupToolbar() {
    supportActionBar?.apply {
      title = "Google Campus - São Paulo"
      subtitle = "Rua Coronel Oscar Porto, 70"
    }
  }
}
