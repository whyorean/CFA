package com.cap.front.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cap.front.Content
import com.cap.front.Product
import com.cap.front.StreamBundle
import com.cap.front.databinding.ActivityMainBinding
import com.cap.front.view.epoxy.GenericCarouselController
import com.cap.front.viewmodel.StreamViewModel

class MainActivity : BaseActivity(), GenericCarouselController.Callbacks {

    private lateinit var B: ActivityMainBinding //ViewBinding
    private lateinit var VM: StreamViewModel //ViewModel
    private lateinit var C: GenericCarouselController //Epoxy Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        B = ActivityMainBinding.inflate(layoutInflater)
        VM = ViewModelProvider(this).get(StreamViewModel::class.java)

        setContentView(B.root)

        attachRecycler()

        VM.liveData.observe(this, {
            updateController(it)
        })
    }

    override fun onConnected() {
    }

    override fun onDisconnected() {
        Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show()
    }

    override fun onReconnected() {
        Toast.makeText(this, "Network restored", Toast.LENGTH_SHORT).show()
    }

    private fun attachRecycler() {
        C = GenericCarouselController(this)
        B.epoxyRecycler.setController(C)
        B.epoxyRecycler.layoutManager = LinearLayoutManager(this)
        C.setData(null)
    }

    private fun updateController(streamBundle: StreamBundle?) {
        C.setData(streamBundle)
    }

    override fun onHeaderClicked(content: Content) {

    }

    override fun onBundleScrolled(content: Content) {

    }

    override fun onProductClick(product: Product) {

    }

    override fun onProductLongClick(product: Product) {

    }
}