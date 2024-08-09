package com.ftgrl.bankingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ftgrl.bankingapp.databinding.ActivityMainBinding
import com.ftgrl.bankingapp.fragment.CurrencyFragment
import com.ftgrl.bankingapp.fragment.HomeFragment
import com.ftgrl.bankingapp.fragment.NotificationFragment
import com.ftgrl.bankingapp.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.menuHome -> replaceFragment(HomeFragment())
                R.id.menuHistory -> replaceFragment(CurrencyFragment())
                R.id.menuNotification-> replaceFragment(NotificationFragment())
                R.id.menuProfile -> replaceFragment(ProfileFragment())

                else ->{

                }

            }

            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
