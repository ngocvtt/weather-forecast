package com.ngocvtt.weatherforecast.network


import android.annotation.SuppressLint
import com.ngocvtt.weatherforecast.network.convert.JsonConvertFactory
import com.ngocvtt.weatherforecast.utils.Logger
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.*
import retrofit2.http.*
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

internal class Client(private var domain: String, converter: Converter.Factory = JsonConvertFactory()) {
    companion object {
        fun getUnsafeOkHttpClient() : OkHttpClient.Builder {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }

            return builder
        }
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(domain)
        .addConverterFactory(converter)
        .client(getUnsafeOkHttpClient().build())
        .build()

    fun get(endPoint: String, headers: Map<String, String>, params: Map<String, String>, listener: ClientListener) {
        val api = retrofit.create(API::class.java)

        val fullURL = fullUrl(endPoint)

        Logger.printLog("get url:", fullURL)
        Logger.printLog("headers:", headers)
        Logger.printLog("params:", params)

        api.get(fullURL, headers, params).enqueue(object : Callback<JSONObject> {
            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                handleOnResponse(response, listener)
            }

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                handleOnFailure(t, listener)
            }
        })
    }


    private fun handleOnResponse(response: Response<JSONObject>, listener: ClientListener?) {
        if (listener != null) {
            if (response.code() == 200) {
                listener.success(response.body() as JSONObject)
            }
            else {
                try {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    listener.fail(jObjError.getString("message"))
                }catch (e: Exception){
                    Logger.printLog(e.message.toString())
                    listener.fail(response.message())
                }
            }
        }
    }

    private fun handleOnFailure(t: Throwable, listener: ClientListener?) {
        t.printStackTrace()

        listener?.fail("Network Error")
    }

    private fun fullUrl(url: String) : String {
        return if (url.startsWith("http", true)) url
        else domain + url
    }



    private interface API {
        @GET
        fun get(@Url url:String, @HeaderMap headers: Map<String, String>, @QueryMap params: Map<String, String>) : Call<JSONObject>
    }

    interface ClientListener {
        fun success(jsonObject: JSONObject)
        fun fail(error: String)
    }
}