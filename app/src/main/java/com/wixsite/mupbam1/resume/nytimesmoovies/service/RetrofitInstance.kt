package com.wixsite.mupbam1.resume.nytimesmoovies.service

import com.wixsite.mupbam1.resume.nytimesmoovies.data.NestedJSONModel
import com.wixsite.mupbam1.resume.nytimesmoovies.data.Result
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInstance {
   @GET("/svc/movies/v2/reviews/all.json?api-key=RrM46TJB7XxwoYZZA53NSAc8jdtTgIwN")

    suspend fun getEmployeesNested(): Response<NestedJSONModel>
}