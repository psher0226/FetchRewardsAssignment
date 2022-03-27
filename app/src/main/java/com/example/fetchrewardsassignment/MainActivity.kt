package com.example.fetchrewardsassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fetchRewardsItemList: MutableList<FetchRewardsItem>
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)

        runBlocking {
            val client = HttpClient(CIO) {
                install(JsonFeature) {

                }
            }

             fetchRewardsItemList = client.get("https://fetch-hiring.s3.amazonaws.com/hiring.json")

        }

         recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
         recyclerView.adapter = FetchRewardsAdapter(orderFetchRewardsItem(fetchRewardsItemList))

    }

    private fun orderFetchRewardsItem(itemList: MutableList<FetchRewardsItem>): MutableList<FetchRewardsItem> {

        val updatedList: MutableList<FetchRewardsItem> = arrayListOf()

        for (item in itemList) {
            if (item.name.isNullOrEmpty()) continue
            updatedList.add(item)
        }

        updatedList.sortWith(
            compareBy(
                { it.listId },
                { it.id }
            )
        )

        return updatedList
    }
}