package com.example.evonza

import android.R.id.message
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.synthetic.main.business_single_list.view.*


class BusinessListAdapter(val context: Context, val businessList: List<DocumentSnapshot>, val cellClickListener: CellClickListener): RecyclerView.Adapter<BusinessListAdapter.BusinessListViewHolder> (){
    class BusinessListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.business_single_list, parent, false)
        return BusinessListViewHolder(view)
    }



    override fun getItemCount(): Int {
        return businessList.size
    }


    override fun onBindViewHolder(holder: BusinessListViewHolder, position: Int) {
        val currentData = businessList[position]
        holder.itemView.BusinessName.text = currentData.get("businessName").toString()
        holder.itemView.Businesslocation.text = currentData.get("city").toString() + ", " + currentData.get("state").toString()
        holder.itemView.Businesscategory.text = currentData.get("businessType").toString()

        holder.itemView.getservicebutton.setOnClickListener {
            cellClickListener.onCellClickListener("abc")

        }

    }
}