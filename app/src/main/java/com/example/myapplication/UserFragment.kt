package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.myapplication.databinding.FragmentUserBinding
import kotlinx.coroutines.*

class UserFragment : Fragment() {


    var binding: FragmentUserBinding? = null
    var userDataAdapter: RecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userDataAdapter = RecyclerAdapter()
        val recycler = binding?.recycler
        recycler?.adapter = userDataAdapter
        recycler?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        getData()

    }

    private fun getData() {
        val api = RetrofitService.retrofit.create(UserApi::class.java)

       viewLifecycleOwner.lifecycleScope.launch {
           withContext(Dispatchers.IO){
               try {
                   val response: List<UserUIModel> = api.getApi().map {
                       UserUIModel(
                           name = it.name.orEmpty(),
                           id = it.id ?: 0,
                           email = it.email.orEmpty(),
                           gender = it.gender.orEmpty(),
                           status = it.status.orEmpty()
                       )
                   }
                   withContext(Dispatchers.Main){
                       userDataAdapter?.submitList(response)
                   }

               } catch (e: Exception) {
                   Log.d("Appd", "${e}")
               }
           }


        }
    }
}
