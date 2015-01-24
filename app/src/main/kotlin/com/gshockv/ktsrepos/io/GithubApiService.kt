package com.gshockv.ktsrepos.io

import retrofit.http.GET
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.http.Query
import com.gshockv.ktsrepos.model.ResponseEnvelope

/**
 * github.com api service
 */
public class GithubApiService() {

    public class object {
        val API_HEADER = "application/vnd.github.v3+json"

        val restBuilder = RestAdapter.Builder().setRequestInterceptor {
            ri -> ri.addHeader("Accept", API_HEADER)
        }
        val rest = restBuilder.setEndpoint("https://api.github.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
        private val api: GithubApi = rest.create(javaClass<GithubApi>())

        public fun searchRepos(q: String, callback: Callback<ResponseEnvelope>) {
            api.searchRepos(q, 100, callback)
        }
    }

    private trait GithubApi {
        GET("/search/repositories")
        fun searchRepos(
                Query("q") q: String,
                Query("per_page") perPage: Int,
                callback: Callback<ResponseEnvelope>)
    }
}
