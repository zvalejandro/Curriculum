package com.zaraos.curriculum.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.zaraos.curriculum.R
import com.zaraos.curriculum.base.EmptyHolder
import com.zaraos.curriculum.domain.entity.RecyclerItem
import com.zaraos.curriculum.domain.entity.UserEntity
import kotlinx.android.synthetic.main.row_home_banner.view.*

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class HomeAdapter(private val listener: ((AppCompatDialogFragment) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_BANNER: Int = 100
        const val TYPE_EXPERIENCE: Int = 101
        const val TYPE_SKILLS: Int = 102
    }

    var homeList = mutableListOf<RecyclerItem>()
        set(value) {
            field.clear()
            homeList.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount() = homeList.count()

    override fun getItemViewType(position: Int): Int {
        return homeList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_BANNER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_home_banner, parent, false)
                BannerHolder(view)
            }
            TYPE_EXPERIENCE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_home_experience, parent, false)
                ExperienceHolder(view)
            }
            TYPE_SKILLS -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_home_skills, parent, false)
                SkillsHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_empty, parent, false)
                EmptyHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_BANNER -> {
                (holder as BannerHolder).bind(homeList[position])
            }
            TYPE_EXPERIENCE -> {
                (holder as ExperienceHolder).bind(homeList[position])
            }
            TYPE_SKILLS -> {
                (holder as SkillsHolder).bind(homeList[position])
            }
        }
    }

    private inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RecyclerItem) = with(itemView) {
            val details: UserEntity? = item.obj as? UserEntity
            details?.let {
                homeBannerName.text = it.fullName
                homeBannerPosition.text = it.currentPosition
                homeBannerEmail.text = it.email
                homeBannerLinkedIn.text = it.linkedinId
                homeBannerGithub.text = it.githubId
            }


            /*
            whipBrakesBtnInfo.setOnClickListener {
                listener?.invoke(ColorsDialog.newInstance())
            }*/
        }
    }

    private inner class ExperienceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RecyclerItem) = with(itemView) {

        }
    }

    private inner class SkillsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RecyclerItem) = with(itemView) {

        }
    }

}