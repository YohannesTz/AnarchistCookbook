package com.yohannes.dev.app.anarchistcookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yohannes.dev.app.anarchistcookbook.data.Anarchy
import com.yohannes.dev.app.anarchistcookbook.ui.ItemAdapter
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var anarchiesList: ArrayList<Anarchy> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)

        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = linearLayoutManager

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val anarchyArray = obj.getJSONArray("anarchies")
            for (i in 0 until anarchyArray.length()) {
                val titleObject = anarchyArray.getJSONObject(i)
                val titleString = titleObject.getString("title").drop(1)
                val data = titleObject.getString("data")
                val index = titleString.take(3)

                anarchiesList.add(Anarchy(index, titleString, data))
                Log.e("AnarchyTitle", anarchiesList[i].data)
            }
            Log.e("AnarchyList", anarchiesList.size.toString())
        } catch (e : JSONException) {
            e.printStackTrace()
        }

        val itemAdapter = ItemAdapter(anarchiesList)
        recyclerView.adapter = itemAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                showDefaultDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDefaultDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setTitle("About")
            setMessage("This App was developed by YohannesTz. the conent of this app doesn't reflect the developer's views. \n" +
                    "please don't do any illegal stuff and i won't take responsiblity for any damage you make!! \n" +
                    "\n https://yohannesTz.github.io")

            setNegativeButton("Okay") { _, _ ->
                Toast.makeText(context, "clicked positive button", Toast.LENGTH_SHORT);

            }
        }.create().show()
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("index.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }

        return json
    }
}