package com.stimednp.mapboxdroppingpins

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.stimednp.mapboxdroppingpins.CompanionString.Companion.ICON_ID
import com.stimednp.mapboxdroppingpins.CompanionString.Companion.LAYER_ID
import com.stimednp.mapboxdroppingpins.CompanionString.Companion.SOURCE_ID
import kotlinx.android.synthetic.main.activity_add_marker.*

class AddMarkerActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: MapboxMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, BuildConfig.MAPBOX_ACCESS_TOKEN)
        setContentView(R.layout.activity_add_marker)
        map_view.onCreate(savedInstanceState)
        map_view.getMapAsync(this)
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        map = mapboxMap
        initAddMarker(map)
        
        //DEFAULT ZOOM , YOU CAN CUSTOME THIS
        val latLng = LatLng(-5.1670937,119.4796103)
        val position = CameraPosition.Builder().target(latLng).zoom(13.0).tilt(10.0).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
    }

    private fun initAddMarker(map: MapboxMap) {
        val symbolLayers = ArrayList<Feature>()
        symbolLayers.add(Feature.fromGeometry(Point.fromLngLat(119.4796103, -5.1670937))) //remember to reverse LatLng
        symbolLayers.add(Feature.fromGeometry(Point.fromLngLat(119.4774773, -5.1695497)))
        map.setStyle(
            Style.Builder().fromUri(Style.MAPBOX_STREETS)
                .withImage(ICON_ID, BitmapUtils
                    .getBitmapFromDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.mapbox_marker_icon_default))!!)
                .withSource(GeoJsonSource(SOURCE_ID, FeatureCollection.fromFeatures(symbolLayers)))
                .withLayer(SymbolLayer(LAYER_ID, SOURCE_ID)
                    .withProperties(iconImage(ICON_ID), iconSize(1.0f), iconAllowOverlap(true), iconIgnorePlacement(true)))
        )
        {
            //Here is style loaded
        }
    }


    override fun onStart() {
        super.onStart()
        map_view.onStart()
    }

    override fun onResume() {
        super.onResume()
        map_view.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_view.onPause()
    }

    override fun onStop() {
        super.onStop()
        map_view.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_view.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        map_view.onSaveInstanceState(outState)
    }
}
