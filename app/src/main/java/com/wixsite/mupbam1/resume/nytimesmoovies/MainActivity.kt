package com.wixsite.mupbam1.resume.nytimesmoovies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.wixsite.mupbam1.resume.nytimesmoovies.adapter.MyAdapter
import com.wixsite.mupbam1.resume.nytimesmoovies.databinding.ActivityMainBinding
import com.wixsite.mupbam1.resume.nytimesmoovies.service.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
val tags= ArrayList<String>()
//val resultList: ArrayList<NestedJSONModel> = ArrayList()


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        parseJSON()
    }
    @SuppressLint("MyLog")
    fun parseJSON() {
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Create Service
        val service = retrofit.create(RetrofitInstance::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getEmployeesNested()

            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {

                    val items = response.body()
                    val results=items?.results

                    if (results !=null) {
                        for (i in 0..results.size-1){
                            val item_results = results.get(i)
                            var multimediaSRC = item_results.multimedia.src
                            tags.add(multimediaSRC)
                            var display_title = item_results.display_title
                            tags.add(display_title)
                            var summary_short = item_results.summary_short
                            tags.add(summary_short)
                        }
                        makeAdapter()
                    }

                } else {
                    Log.e("MyLog", "error - ${response.code().toString()}")
                }
            }
        }
    }

    private fun makeAdapter() {
        // Set the LayoutManager that this RecyclerView will use.
        binding.rcView.layoutManager = LinearLayoutManager(this)
        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = MyAdapter(this, tags)
        // adapter instance is set to the recyclerview to inflate the items.
        binding.rcView.adapter = itemAdapter
    }
}




