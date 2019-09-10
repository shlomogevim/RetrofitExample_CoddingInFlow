package com.example.retrofitexample_coddinginflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

        val loogInTerceptor = HttpLoggingInterceptor()
        loogInTerceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(object :Interceptor{
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val request=chain.request()
                    val newRequest=request.newBuilder()
                        .header("Interceptor-Header","****** xyz ********")
                        .build()
                    return chain.proceed(newRequest)
                }

            })
            .addInterceptor(loogInTerceptor)
            .build()


        /*var retrofit = Retrofit.Builder()  // this version of retrofit add spcial header and Logcat
            .baseUrl("https://jsonplaceholder.typicode.com/") //must end with / (slash)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()*/

        var retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") //must end with / (slash)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

         retrofit1()
       // retrofit2()
        // retrofit3()
        // retrofit30()
        // retrofit4()
        // retrofit5()
        //  retrofit6()
         // retrofit60()
        //  retrofit61()
        //  retrofit7()
        // createPost1()
        // createPost10()
        //createPost2()
        //createPost3()


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
        val call = jsonPlaceHolderApi.getPosts30(3, 7)
        drawPostArray(call)
    }

    fun retrofit4() {
        val call = jsonPlaceHolderApi.getPosts4(3, "id", "desc")
        drawPostArray(call!!)
    }

    fun retrofit5() {
        val call = jsonPlaceHolderApi.getPosts5(arrayListOf(1, 3, 4))
        drawPostArray(call!!)
    }

    fun retrofit6() {
        var parameters = HashMap<String, String>()
        parameters.put("userId", "3")
        parameters.put("_sort", "id")
        parameters.put("_order", "desc")
        val call = jsonPlaceHolderApi.getPosts6(parameters)
        drawPostArray(call!!)
    }

    fun retrofit60() {
        var parameters = HashMap<String, String>()
        parameters.put("userId", "3")
        parameters.put("_sort", "id")
        parameters.put("_order", "desc")
        val call = jsonPlaceHolderApi.getPosts60("<<<<<<<<<<<<aaa>>>>>>>>>>",parameters)
        drawPostArray(call!!)
    }

    fun retrofit61() {
        var parameters = HashMap<String, String>()
        parameters.put("userId", "3")
        parameters.put("_sort", "id")
        parameters.put("_order", "desc")
        val headers=HashMap<String,String>()
        headers.put("Map-Header1","<<<<<<<<<<<< aaa >>>>>>>>>>")
        headers.put("Map-Header2","<<<<<<<<<<<< bbb >>>>>>>>>>")
        val call = jsonPlaceHolderApi.getPosts61(headers,parameters)
        drawPostArray(call!!)
    }



    fun retrofit7() {
        val call = jsonPlaceHolderApi.getPosts7("posts/3")
        drawPost(call)
    }

    private fun createPost1() {
        val post = Post(23, null, "New Title", "New Text")
        val call = jsonPlaceHolderApi.creatPost1(post)
        drawPost1(call!!)
    }

    private fun createPost10() {
        val post = Post(23, null, "New Title", "New Text")
        val call = jsonPlaceHolderApi.creatPost10("******** abc *********",post)
        drawPost1(call!!)
    }

    private fun createPost2() {
        val call = jsonPlaceHolderApi.creatPost2(24, "its title", "Its text")
        drawPost1(call!!)
    }

    private fun createPost3() {
        var myMap = HashMap<String, String>()
        myMap.put("userId", "36")
        myMap.put("title", "New new title")
        val call = jsonPlaceHolderApi.creatPost3(myMap)
        drawPost1(call!!)
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

    fun drawPost1(call: Call<Post>) {
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
                content += "Code: " + response.code() + "\n"
                content += "UserId : " + post.userId + "\n"
                content += "ID : " + post.id + "\n"
                content += "Title: " + post.title + "\n"
                content += "Text: " + post.text + "\n\n"

                text_view_result.text = content
            }
        })
    }
}



