package com.example.tiendapersonal.ui.fragment.productdetailsfragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.tiendapersonal.R
import com.example.tiendapersonal.common.adapter.SliderImageAdapter
import com.example.tiendapersonal.datasource.domainclasses.ProductDetails
import com.example.tiendapersonal.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ProductDetailsFragment : BottomSheetDialogFragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private var sliderAdapter: SliderImageAdapter? = null
    private lateinit var imageUrls: ArrayList<String>
    private lateinit var productTitle: TextView
    private lateinit var legalsContainer: TextView
    private lateinit var imgSlidePosition: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productTitle = view.findViewById(R.id.productTitle)
        imgSlidePosition = view.findViewById(R.id.imgSlidePosition)
        legalsContainer = view.findViewById(R.id.descriptionContainer)
        val viewPager = view.findViewById<ViewPager2>(R.id.imgSlider)

        imageUrls = ArrayList()
        activity?.let {fragmentActivity ->
            sliderAdapter = SliderImageAdapter(fragmentActivity, imageUrls)
            viewPager.adapter = sliderAdapter
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val slidePosition = (position+1).toString()+"/"+imageUrls.size
                    imgSlidePosition.text = slidePosition
                }
            })
            setupViewModel()
        }
    }

    private fun fillProductDetails(productDetails: ProductDetails?){
        //limpio fragment
        imageUrls.clear()
        productTitle.text = ""
        legalsContainer.text = ""

        productDetails?.let {
            try{
                val imgs = it.images
                for (img in imgs)
                    imageUrls.add(img.url)

                val slidePosition = "1/"+imageUrls.size
                imgSlidePosition.text = slidePosition

                productTitle.text = productDetails.name
                legalsContainer.text = productDetails.legal


            }catch (e: Exception){
                showErrorToast()
            }



        } ?: run {
            //es null
            showErrorToast()
        }
        sliderAdapter?.notifyDataSetChanged()
    }

    private fun showErrorToast(){
        Toast.makeText(activity, R.string.error, Toast.LENGTH_LONG).show()
    }

    private fun setupViewModel() { //obtengo datos del viewmodel
        mainViewModel.getProductDetailsLiveData().observe(viewLifecycleOwner) { productDetails ->
            fillProductDetails(productDetails)
        }
    }
}