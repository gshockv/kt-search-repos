package com.gshockv.ktsrepos.io

import android.content.Context
import retrofit.http.GET
import retrofit.Callback
import com.gshockv.ktsrepos.io.model.SearchRespoEnvelope
import retrofit.RequestInterceptor
import retrofit.RestAdapter
import retrofit.http.Query

/**
 * github.com api service
 */
public class GithubApiService() {

    public class object {
        val API_HEADER = "application/vnd.github.v3+json"

        val restBuilder = RestAdapter.Builder().setRequestInterceptor {
            ri -> ri.addHeader("Accept", API_HEADER)
        }
        val rest = restBuilder.setEndpoint("https://api.github.com").setLogLevel(RestAdapter.LogLevel.FULL).build()
        val service = rest.create(javaClass<GithubApi>())

        public fun searchRepos(q: String, c: Callback<SearchRespoEnvelope>) {
            service.searchRepos(q, c)
        }
    }

    public trait GithubApi {
        GET("/search/repositories")
        fun searchRepos(Query("q") q: String, callback: Callback<SearchRespoEnvelope>)
    }
}
