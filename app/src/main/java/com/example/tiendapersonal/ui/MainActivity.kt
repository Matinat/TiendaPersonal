package com.example.tiendapersonal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiendapersonal.R
import com.example.tiendapersonal.common.domainclasses.Card
import com.example.tiendapersonal.ui.fragment.productdetailsfragment.ProductDetailsFragment
import com.example.tiendapersonal.ui.fragment.productlistfragment.IProductListFragment
import com.example.tiendapersonal.ui.fragment.productlistfragment.ProductListFragment

class MainActivity : AppCompatActivity(),IProductListFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) { //para no relanzar el fragment al girar la pantalla o al minimizar app
            launchProductListFragment() //inicio fragment con lista de productos "recomendados". (Se emula con una categoría elegida a mano)
        }
    }

    private fun launchProductListFragment(){
        val fragment = ProductListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.mainContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun launchProductDetailsFragment(){
        val fragment = ProductDetailsFragment()
        fragment.show(supportFragmentManager, "ProductDetails")

//
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//
//        fragmentTransaction.replace(R.id.mainContainer, fragment)
//        fragmentTransaction.addToBackStack(null)
//        fragmentTransaction.commit()
    }

    override fun onFullscreen(card: Card) {
        launchProductDetailsFragment()
    }

}