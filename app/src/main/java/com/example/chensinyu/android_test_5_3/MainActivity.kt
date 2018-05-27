package com.example.chensinyu.android_test_5_3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeZone = TimeZone.getDefault()

        val timeZoneView = findViewById<TextView>(R.id.timeZone)
        timeZoneView.setText(timeZone.displayName)
//        timeZoneView.text = timeZone.displayName

        val addButton = findViewById<Button>(R.id.add)
        addButton.setOnClickListener{
            val intent = Intent(this, TimeZoneSelectActivity::class.java)
            startActivityForResult(intent,1)
        }

        showWorldClocks()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){
            val timeZone = data.getStringExtra("timeZone")

            //プリファレンスから、保存しているタイムゾーンを得る
            val pref = getSharedPreferences("prefs",Context.MODE_PRIVATE)
            val timeZones = pref.getStringSet("time_zone", mutableSetOf())
            //保存していたタイムゾーン一覧に追加
                timeZones.add(timeZone.toString())
             pref.edit().putStringSet("time_zone",timeZones).apply()
            //リスト再表示
            showWorldClocks()
        }
    }

    private fun showWorldClocks(){
        val pref = getSharedPreferences("prefs",Context.MODE_PRIVATE)
        val timeZones = pref.getStringSet("time_zone", setOf())

        val list = findViewById<ListView>(R.id.clockList)
        list.adapter = TimeZoneAdapter(this,timeZones.toTypedArray())
    }
}

