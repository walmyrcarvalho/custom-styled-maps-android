package me.walmyrcarvalho.styledmaps

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

  companion object {
    val CAMPUS: LatLng = LatLng(-23.570991, -46.649886)
    val ZOOM = 15f
  }

  val mapFragment by lazy { supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment }

  val buttonStyleOne by lazy { findViewById(R.id.fab_style_one) as FloatingActionButton }
  val buttonStyleTwo by lazy { findViewById(R.id.fab_style_two) as FloatingActionButton }
  val buttonStyleThree by lazy { findViewById(R.id.fab_style_three) as FloatingActionButton }
  val buttonStyleFour by lazy { findViewById(R.id.fab_style_four) as FloatingActionButton }

  val context: Context = this

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_maps)

    setupToolbar()
    setupMap()
  }

  override fun onMapReady(googleMap: GoogleMap) {
    googleMap.apply {

      // Changing map style on FAB click
      buttonStyleOne.setOnClickListener { setMapStyle(context, R.raw.style_retro, "Retro") }
      buttonStyleTwo.setOnClickListener { setMapStyle(context, R.raw.style_silver, "Silver") }
      buttonStyleThree.setOnClickListener { setMapStyle(context, R.raw.style_dark, "Dark") }

      // Let's make our own!
      buttonStyleFour.setOnClickListener {
        Toast.makeText(context, "Let's make our own!", Toast.LENGTH_SHORT).show()
      }

      // Adding a marker
      addMarker(MarkerOptions().position(CAMPUS).title("Hello, Campus!"))

      // Zooming to the Campus location
      moveCamera(CameraUpdateFactory.newLatLngZoom(CAMPUS, ZOOM))
    }
  }

  fun setupMap() {
    mapFragment.getMapAsync(this)
  }

  fun setupToolbar() {
    supportActionBar?.apply {
      title = "Google Campus - São Paulo"
      subtitle = "Rua Coronel Oscar Porto, 70, Paraíso"
    }
  }

  // Extension function for GoogleMap (Kotlin <3)
  fun GoogleMap.setMapStyle(context: Context, styleRes: Int, styleName: String): Boolean {
    Toast.makeText(context, "Theme: $styleName", Toast.LENGTH_SHORT).apply {
      setGravity(Gravity.CENTER, 0, 0)
      show()
    }

    return setMapStyle(MapStyleOptions.loadRawResourceStyle(context, styleRes))
  }
}
