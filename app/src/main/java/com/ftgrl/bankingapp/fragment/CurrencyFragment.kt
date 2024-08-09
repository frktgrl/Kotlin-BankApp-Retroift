package com.ftgrl.bankingapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ftgrl.bankingapp.adapter.RecyclerViewCurrencyAdapter
import com.ftgrl.bankingapp.databinding.FragmentCurrencyBinding
import com.ftgrl.bankingapp.model.CurrencyItem
import com.ftgrl.bankingapp.service.CurrencyAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class CurrencyFragment : Fragment(), RecyclerViewCurrencyAdapter.Listener {

    private lateinit var binding: FragmentCurrencyBinding
    private val BASE_URL = "https://api.freecurrencyapi.com/"
    private val currencyList = mutableListOf<CurrencyItem>()
    private var recyclerViewAdapter: RecyclerViewCurrencyAdapter? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        val view = binding.root

        // RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter = RecyclerViewCurrencyAdapter(currencyList, this)
        binding.recyclerView.adapter = recyclerViewAdapter

        // Veri yükleme işlemini başlat
        loadData()

        return view
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofit.create(CurrencyAPI::class.java)

        compositeDisposable.add(
            service.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { currencyResponse ->
                        currencyResponse.data?.let { rates ->
                            println("CAD: ${rates.CAD}")
                            println("EUR: ${rates.EUR}")
                            println("USD: ${rates.USD}")

                            // Listeyi temizleyip her döviz türü için yeni veriyi ekleyin
                            currencyList.clear()
                            currencyList.add(CurrencyItem("CAD", rates.CAD))
                            currencyList.add(CurrencyItem("EUR", rates.EUR))
                            currencyList.add(CurrencyItem("USD", rates.USD))

                            // Adapteri güncelleyin
                            recyclerViewAdapter?.notifyDataSetChanged()
                        }
                    },
                    { error ->
                        error.printStackTrace()
                    }
                )
        )
    }

    override fun onItemClick(currencyItem: CurrencyItem) {
        // Handle item click
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Dispose of any ongoing requests when the view is destroyed
        compositeDisposable.clear()
    }
}
