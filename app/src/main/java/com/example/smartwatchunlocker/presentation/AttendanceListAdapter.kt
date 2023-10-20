package com.example.smartwatchunlocker.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartwatchunlocker.databinding.ReportItemsRowBinding

class AttendanceListAdapter : ListAdapter<Record, AttendanceListAdapter.ReportVideoContentVH>(ListCheckDiffCallback()) {

    var onClickReportContainer: ((position: Int, templatedData: Record) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportVideoContentVH {
        val binding =
            ReportItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReportVideoContentVH(binding)
    }

    override fun onBindViewHolder(holder: ReportVideoContentVH, position: Int) {
        when (holder) {
            is ReportVideoContentVH -> {
                holder.bind(getItem(position))
            }
        }
    }

    inner class ReportVideoContentVH internal constructor(private val binding: ReportItemsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Record) {

            item.run {
                val res = binding.root.context
                binding.reportTitleTv.text = "Name ${item.fields.name}\n Class ${item.fields.classField}\n Date ${item.fields.date}"

                binding.root.setOnClickListener {
                    onClickReportContainer?.invoke(bindingAdapterPosition, item)
                }
            }
        }
    }


    class ListCheckDiffCallback : DiffUtil.ItemCallback<Record>() {
        override fun areItemsTheSame(
            oldItem: Record,
            newItem: Record
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Record,
            newItem: Record
        ): Boolean {
            return oldItem == newItem
        }
    }

}