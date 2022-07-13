package com.example.tiendapersonal.ui.fragment.productlistfragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendapersonal.R
import com.example.tiendapersonal.common.adapter.CardAdapter
import com.example.tiendapersonal.common.domainclasses.Card
import com.example.tiendapersonal.ui.MainActivity
import com.example.tiendapersonal.viewmodel.MainViewModel

class ProductListFragment : Fragment(){
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CardAdapter
    private lateinit var rView: RecyclerView
    private var mActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rView = view.findViewById(R.id.card_list)
        rView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        mActivity = activity as? MainActivity
        mActivity?.let {setupViewModel(it)}
    }

    private fun createCardList(cards: ArrayList<Card>, context: Context){
        if(cards.isEmpty()){
            Toast.makeText(activity, R.string.error, Toast.LENGTH_LONG).show()
        }else{
            adapter = CardAdapter(context, cards, onFullScreen)
            rView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupViewModel(context: FragmentActivity) {
        mainViewModel.getItems().observe(requireActivity()) { cards ->
            createCardList(cards, context)
        }
    }

    private val onFullScreen: (card: Card) -> Unit = {
        mainViewModel.getProductDetails(it.id)
        mActivity?.onFullscreen(it)
    }
}