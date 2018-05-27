package com.example.chensinyu.android_test_5_3

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class TimeZoneSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_zone_select)

        //戻るボタンをタップした時などに対応
        setResult(Activity.RESULT_CANCELED)

        val list = findViewById<ListView>(R.id.clockList)
        val adapter = TimeZoneAdapter(this)
        list.adapter = adapter

        //リストタップ時の動作
        //Kotlinでは、ラムダ式で使用しない引き数を＿で省略できる
        list.setOnItemClickListener { _, _, position, _ ->
            //タップした場所のタイムゾーンをリストから得る
            val timeZone = adapter.getItem(position)

            //遷移元の画面に結果を返す
            val result = Intent()

            result.putExtra("timeZone",timeZone)
            // OKの結果を返す
            setResult(Activity.RESULT_OK,result)
            //画面を閉じる
            finish()

        }
    }
}
