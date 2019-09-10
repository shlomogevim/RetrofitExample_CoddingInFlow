package com.example.retrofitexample_coddinginflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") //must end with / (slash)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        //retrofit1()
        //retrofit2()
       // retrofit3()
       // retrofit30()
       // retrofit4()
       // retrofit5()
      //  retrofit6()
        retrofit7()


    }

    fun retrofit1() {
        val call = jsonPlaceHolderApi.getPosts1()
        drawPostArray(call)
    }
    fun retrofit2() {
        val call = jsonPlaceHolderApi.getPosts2(3)
        drawPost(call)
    }
    fun retrofit3() {
        val call = jsonPlaceHolderApi.getPosts3(3)
        drawPostArray(call)
    }
    fun retrofit30() {
        val call = jsonPlaceHolderApi.getPosts30(3,7)
        drawPostArray(call)
    }
    fun retrofit4() {
        val call = jsonPlaceHolderApi.getPosts4(3,"id", "desc")
        drawPostArray(call!!)
    }
    fun retrofit5() {
        val call = jsonPlaceHolderApi.getPosts5(arrayListOf(1,3,4))
        drawPostArray(call!!)
    }
    fun retrofit6() {
        var parameters=HashMap<String,String>()
        parameters.put("userId","3")
        parameters.put("_sort","id")
        parameters.put("_order","desc")
        val call = jsonPlaceHolderApi.getPosts6(parameters)
        drawPostArray(call!!)
    }
    fun retrofit7() {
        val call = jsonPlaceHolderApi.getPosts7("posts/3")
        drawPost(call)
    }


















    fun drawPostArray(call: Call<List<Post>>) {
        call.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                text_view_result.text = t.message
            }
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    text_view_result.text = " Code: ${response.code()}"
                    return
                }
                var st = ""
                val posts = response.body()!!
                for (post in posts) {
                    var content = ""
                    content += "UserId : " + post.userId + "\n"
                    content += "ID : " + post.id + "\n"
                    content += "Title: " + post.title + "\n"
                    content += "Text: " + post.text + "\n\n"
                    st += content
                }
                text_view_result.text = st
            }
        })
    }


    fun drawPost(call: Call<Post>) {
        call.enqueue(object : Callback<Post> {

            override fun onFailure(call: Call<Post>, t: Throwable) {
                text_view_result.text = t.message
            }


            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    text_view_result.text = " Code: ${response.code()}"
                    return
                }
                val post = response.body()!!
                var st = ""

                var content = ""
                content += "UserId : " + post.userId + "\n"
                content += "ID : " + post.id + "\n"
                content += "Title: " + post.title + "\n"
                content += "Text: " + post.text + "\n\n"

                text_view_result.text = content
            }
        })
    }
}



