package com.ftgrl.bankingapp.service

import com.ftgrl.bankingapp.model.CurrencyResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyAPI {
    @GET("v1/latest?apikey=fca_live_3EJsP7tr7Qo4Bevxj1irN8qjhMWvvkPJX5FRgwo7")
    fun getData(): Observable<CurrencyResponse>
}
