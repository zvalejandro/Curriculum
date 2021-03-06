package com.zaraos.curriculum.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DecodeFormat
import com.zaraos.curriculum.R
import com.zaraos.curriculum.base.EmptyHolder
import com.zaraos.curriculum.di.modules.GlideApp
import com.zaraos.curriculum.domain.entity.RecyclerItem
import com.zaraos.curriculum.domain.entity.UserEntity
import kotlinx.android.synthetic.main.row_home_banner.view.*
import kotlinx.android.synthetic.main.row_home_experience.view.*
import kotlinx.android.synthetic.main.row_home_summary.view.*

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class HomeAdapter(private val listener: ((Int) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_BANNER: Int = 100
        const val TYPE_SUMMARY: Int = 101
        const val TYPE_EXPERIENCE: Int = 102
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
            TYPE_SUMMARY -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_home_summary, parent, false)
                SummaryHolder(view)
            }
            TYPE_EXPERIENCE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.row_home_experience, parent, false)
                ExperienceHolder(view)
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
            TYPE_SUMMARY -> {
                (holder as SummaryHolder).bind(homeList[position])
            }
            TYPE_EXPERIENCE -> {
                (holder as ExperienceHolder).bind(homeList[position])
            }
        }
    }

    private inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RecyclerItem) = with(itemView) {
            val details: UserEntity? = item.obj as? UserEntity
            details?.let {
                GlideApp.with(itemView.context)
                    .asBitmap()
                    .dontAnimate()
                    .format(DecodeFormat.PREFER_RGB_565)
                    .load(it.photoUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(homeBannerImg)

                homeBannerName.text = it.fullName
                homeBannerPosition.text = it.currentPosition
                homeBannerEmail.text = it.email
                homeBannerLinkedIn.text = it.linkedinId
                homeBannerGithub.text = it.githubId
            }
        }
    }

    private inner class SummaryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RecyclerItem) = with(itemView) {
            val professionalSummary: String? = item.obj as? String
            professionalSummary?.let {
                homeSummaryDesc.text = professionalSummary
            }
        }
    }

    private inner class ExperienceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: RecyclerItem) = with(itemView) {
            val experience: UserEntity.Experience? = item.obj as? UserEntity.Experience
            experience?.let {
                GlideApp.with(itemView.context)
                    .asBitmap()
                    .format(DecodeFormat.PREFER_RGB_565)
                    .load(it.logoUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(homeExperienceImgCompany)

                homeExperienceCompany.text = experience.companyName
                homeExperienceRole.text = experience.roleName
                homeExperienceSeeAll.setOnClickListener {
                    listener?.invoke(item.type)
                }
            }
        }
    }

}