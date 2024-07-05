package com.example.thirdeyesystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AlertsAdapter(context: Context, private val alertData: List<String>) :
    ArrayAdapter<String>(context, 0, alertData) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val alert = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_alert, parent, false)
        val alertTextView: TextView = view.findViewById(R.id.alert_text)

        alertTextView.text = alert

        return view
    }
}
