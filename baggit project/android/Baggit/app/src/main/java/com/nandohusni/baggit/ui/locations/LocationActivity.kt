package com.nandohusni.baggit.ui.locations

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ListFragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.addLokasi.AddLocationActivity
import com.nandohusni.baggit.ui.locations.fragment.LocationFragment
import com.nandohusni.baggit.ui.locations.fragment.list.ListLocationFragment
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

class LocationActivity : AppCompatActivity() {



    companion object {

        var name : String? = null
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                supportFragmentManager.beginTransaction().replace(R.id.container,LocationFragment()).commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,ListLocationFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        setSupportActionBar(toolbar)
        val bar = supportActionBar!!
        bar.setDisplayHomeAsUpEnabled(true)
        bar.setDisplayShowTitleEnabled(false)
        toolbar_title.text = "Shopping Locations"

        name = intent.getStringExtra("type")

        supportFragmentManager.beginTransaction().add(R.id.container,LocationFragment()).commit()


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.tambah_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){

            R.id.menuTambah -> startActivity<AddLocationActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}
