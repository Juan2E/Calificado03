package com.sanchez.juan.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanchez.juan.laboratoriocalificado03.databinding.ItemTeacherBinding


class TeacherAdapter(
    private val teachers: List<Teacher>,
    private val onClick: (Teacher) -> Unit,
    private val onLongClick: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)
        holder.itemView.setOnClickListener { onClick(teacher) }
        holder.itemView.setOnLongClickListener {
            onLongClick(teacher)
            true
        }
    }

    override fun getItemCount() = teachers.size

    class TeacherViewHolder(private val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: Teacher) {
            binding.tvName.text = teacher.name
            binding.tvLastname.text = teacher.last_name
            Glide.with(binding.root.context)
                .load(teacher.imageUrl)
                .into(binding.imgPhoto)
        }
    }
}