package com.example.fetchrewardsassignment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FetchRewardsAdapter(private val fetchRewardsList: MutableList<FetchRewardsItem>):
    RecyclerView.Adapter<FetchRewardsAdapter.FetchRewardsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FetchRewardsViewHolder{


        val itemView = if(viewType == 1){
            LayoutInflater.from(parent.context).inflate(R.layout.fetch_reward_card,parent,false)
        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.fetch_reward_separator_card, parent, false)
        }

        return FetchRewardsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FetchRewardsViewHolder, position: Int) {


        holder.itemId?.text  = String.format("Id: %d", fetchRewardsList[position].id)
        holder.itemName?.text = String.format("Id: %s", fetchRewardsList[position].name)
        holder.listId?.text = String.format("List Id: %d", fetchRewardsList[position].listId)


    }

    override fun getItemViewType(position: Int): Int {
        if(position < fetchRewardsList.size - 1){
            if(fetchRewardsList[position + 1].listId > fetchRewardsList[position].listId ){
                return 2
            }
        }

        return 1
    }

    override fun getItemCount() = fetchRewardsList.size

    inner class FetchRewardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemId: TextView? = itemView.findViewById(R.id.id)
        var itemName: TextView? = itemView.findViewById(R.id.name)
        var listId: TextView? = itemView.findViewById(R.id.listId)

    }

}

