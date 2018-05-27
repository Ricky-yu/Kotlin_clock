package com.example.chensinyu.android_test_5_3

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextClock
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class TimeZoneAdapter(private val context:Context,private var timeZones: Array<String> = TimeZone.getAvailableIDs().sortedArrayDescending()):BaseAdapter() {


    private  val inflater = LayoutInflater.from(context)


    override fun getCount() = timeZones.size


    override fun getItem(position: Int) = timeZones[position]

    override fun getItemId(position: Int) = position.toLong()

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //convertViewがある場合はそれを使い、ない場合は新しく作る　
        val view = convertView ?: createView(parent)

        val timeZoneId = getItem(position)

        val timeZone = TimeZone.getTimeZone(timeZoneId)

        val viewHolder = view.tag as ViewHolder

        viewHolder.name.text = "${timeZone.displayName}(${timeZone.id})"
        viewHolder.clock.timeZone = timeZone.id

        return view

    }
    private  fun createView(parent: ViewGroup?) :View{
        val view = inflater.inflate(R.layout.list_time_zone_row,parent,false)
        view.tag = ViewHolder(view)
        return  view
    }

    private class ViewHolder(view:View){
        val name = view.findViewById<TextView>(R.id.timeZone)
        val clock = view.findViewById<TextClock>(R.id.clock)
    }
}