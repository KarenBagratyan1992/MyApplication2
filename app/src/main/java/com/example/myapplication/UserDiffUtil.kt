package com.example.myapplication

import android.content.ClipData.Item
import androidx.recyclerview.widget.DiffUtil

class UserDiffUtil: DiffUtil.ItemCallback<UserUIModel>() {
    override fun areItemsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: UserUIModel, newItem: UserUIModel): Boolean {
       return oldItem==newItem
    }
}