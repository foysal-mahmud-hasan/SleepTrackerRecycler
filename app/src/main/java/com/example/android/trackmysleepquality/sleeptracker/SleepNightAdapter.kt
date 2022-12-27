/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding
import com.example.android.trackmysleepquality.generated.callback.OnClickListener

// TODO (02) Create SleepNightAdapter class and extend it
// from RecyclerView.Adapter<TextItemViewHolder>
//import androidx one (for list adapter)
class SleepNightAdapter(val onClickListener: SleepNightListener) : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallBack()){


    // already keep tracking done by ListAdapter
//    var data = listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

// list adapter also can get the item count
//    override fun getItemCount(): Int {
//        return data.size
//    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {

        val item = getItem(position)


//        cant call it while using listAdapter
//        val item = data[position]


//        val res = holder.itemView.context.resources
//        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
//        holder.quality.text = convertNumericQualityToString(item.sleepQuality, res)
//
//        holder.qualityImage.setImageResource(when (item.sleepQuality){
//
//            0 -> R.drawable.ic_sleep_0
//            1 -> R.drawable.ic_sleep_1
//            2 -> R.drawable.ic_sleep_2
//            3 -> R.drawable.ic_sleep_3
//            4 -> R.drawable.ic_sleep_4
//            5 -> R.drawable.ic_sleep_5
//            else -> R.drawable.ic_sleep_active
//        })
        //we extracted the code upper
//        holder.bind(item)
        holder.bind(getItem(position))

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder
    {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) :
        RecyclerView.ViewHolder(binding.root){


        fun bind(item: SleepNight) {

            binding.sleep = item
            binding.executePendingBindings()
        /*//binding adapter
            val res = itemView.context.resources
            binding.sleepLength.text =
                convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)

            binding.qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )*/

        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListItemSleepNightBinding.inflate(layoutInflater,
                    parent, false)
                return ViewHolder(binding)

//                already done by databinding
//                val view = layoutInflater
//                    .inflate(R.layout.list_item_sleep_night, parent, false)
                //short way
                //        val view = LayoutInflater.from(parent.context)
                //            .inflate(R.layout.list_item_sleep_night, parent, false)
//                return ViewHolder(view)
            }
        }
    }



}
class SleepNightDiffCallBack : DiffUtil.ItemCallback<SleepNight>(){
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }


}

class SleepNightListener(val clickListener: (sleepId : Long) -> Unit){

    fun onClick(night: SleepNight) = clickListener(night.nightId)

}

// TODO (03) Create a variable, data, that holds a list of SleepNight.

// TODO (04) Override getItemCount() to return the total number of items in the data set.

// TODO (05) Override onBindViewHolder() and have it update the contents of the
// ViewHolder to reflect the item at the given position.

// TODO (06) Override onCreateViewHolder(). We'll complete this method
// in a later exercise.
