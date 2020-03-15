package com.dm.mvvmexample.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dm.mvvmexample.R
import com.dm.mvvmexample.api.response.Photo
import com.dm.mvvmexample.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.name.observe(viewLifecycleOwner, Observer {
            showPost(it.get(0))
        })

        textView.setOnClickListener { Toast.makeText(context,viewModel.getResultFromScope(),Toast.LENGTH_SHORT).show() }
    }

    fun showPost(p: Photo){
        Glide.with(this)
            .load(p.url_z)
            .into(imageView)
        textView.text = p.title
    }

}
