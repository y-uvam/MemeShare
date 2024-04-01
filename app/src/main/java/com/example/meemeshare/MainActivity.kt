package com.example.meemeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.meemeshare.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        load()
    }


    fun load() {
        val queue = Volley.newRequestQueue(this)
        var url = "https://vast-puce-mite-fez.cyclic.app/animeme"
//        val image = findViewById<ImageView>(R.id.image)


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                url = response.getString("url")
                Glide.with(this).load(url).into(binding.image)


            }, {
                Toast.makeText(this, "something went wrong", Toast.LENGTH_LONG).show()
            }
        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    fun nextMeme(view  : View) {
        load()
    }
    fun shareMeme(view  : View){
        val intent =Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Check out this meme ...")
        val chooser=Intent.createChooser(intent,"Share this Meme")
        startActivity(chooser)

    }


}