package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.RecyclerView
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
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler?.adapter = userDataAdapter

        getData()

    }

    private fun getData() {
        val api = RetrofitService.retrofit.create(UserApi::class.java)


       viewLifecycleOwner.lifecycleScope.launch() {
            (Dispatchers.IO)

            delay(1500)
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

                lifecycle.whenStarted {
                    Dispatchers.Main

                        userDataAdapter?.submitList(response)


                }

            } catch (e: Exception) {
                Log.d("Appd", "${e}")
            }
        }
    }
}
