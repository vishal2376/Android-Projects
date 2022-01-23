package com.example.newsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),NewsItemClicked {
	private lateinit var mAdapter: NewsListAdapter
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val layoutManager = LinearLayoutManager(this)
		recyclerView.layoutManager = layoutManager
		fetchData()
		mAdapter = NewsListAdapter(this)
		recyclerView.adapter = mAdapter
	}

	private fun fetchData() {
		val queue = Volley.newRequestQueue(this)

		//YOUR NEWS API HERE
		val KEY = "API KEY HERE"

		val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=$KEY"
		val getRequest: JsonObjectRequest = object : JsonObjectRequest(
			Method.GET,
			url,
			null,
			Response.Listener {
				val newsJsonArray = it.getJSONArray("articles")
				val newsArray = ArrayList<News>()
				for (i in 0 until newsJsonArray.length()) {
					val newsJsonObject = newsJsonArray.getJSONObject(i)
					val news = News(
						newsJsonObject.getString("author"),
						newsJsonObject.getString("title"),
						newsJsonObject.getString("url"),
						newsJsonObject.getString("urlToImage")
					)
					newsArray.add(news)
				}
				mAdapter.update(newsArray)
			},
			Response.ErrorListener {

			}
		) {
			@Throws(AuthFailureError::class)
			override fun getHeaders(): Map<String, String> {
				val params: MutableMap<String, String> = HashMap()
				params["User-Agent"] = "Mozilla/5.0"
				return params
			}
		}
		queue.add(getRequest)
	}
override fun onItemClicked(item: News){
		val builder = CustomTabsIntent.Builder()
		val customTabsIntent = builder.build()
		customTabsIntent.launchUrl(this, Uri.parse(item.url))
	}
}

