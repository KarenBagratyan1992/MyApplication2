package com.example.myapplication

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerRowBinding

class RecyclerAdapter() :
    androidx.recyclerview.widget.ListAdapter<UserUIModel, RecyclerAdapter.CustomViewHplder>(
        UserDiffUtil()
    ) {
    val data: MutableList<UserUIModel> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): CustomViewHplder {

        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHplder(binding)
    }


    override fun onBindViewHolder(holder: CustomViewHplder, position: Int) {
        val currentData = getItem(position)
        holder.textName.text = currentData.name
        holder.text_status.text = currentData.status
        holder.text_gender.text = currentData.gender
        holder.text_email.text = currentData.email
        holder.textId.text = currentData.id.toString()


    }


    class CustomViewHplder(private val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val textName: TextView
        val textId: TextView
        val text_email: TextView
        val text_gender: TextView
        val text_status: TextView

        init {
            textId = binding.textId
            textName = binding.textName
            text_email = binding.textEmail
            text_gender = binding.textGender
            text_status = binding.textStatus
        }

    }

}