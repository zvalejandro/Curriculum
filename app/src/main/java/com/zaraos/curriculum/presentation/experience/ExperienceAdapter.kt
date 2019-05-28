package com.zaraos.curriculum.presentation.experience

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaraos.curriculum.R
import com.zaraos.curriculum.domain.entity.ExperienceEntity
import kotlinx.android.synthetic.main.row_experience_content.view.*

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class ExperienceAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var experienceList = mutableListOf<ExperienceEntity>()
        set(value) {
            field.clear()
            experienceList.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount() = experienceList.count()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_experience_content, parent, false)
        return ContentHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContentHolder).bind(experienceList[position])
    }

    private inner class ContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ExperienceEntity) = with(itemView) {
            experienceRole.text = item.roleName
            experienceCompany.text = item.companyName
            experienceDate.text = String.format("%s - %s", item.startDate, item.endDate)

            if (item.responsibilities.isNotEmpty()) {
                var completStr = ""
                for (newItem in item.responsibilities)
                    completStr = completStr.plus("• $newItem").plus("\n")

                experienceResponsibilities.text = completStr
            } else {
                experienceResponsTitle.visibility = View.GONE
                experienceResponsibilities.visibility = View.GONE
            }

            if (item.achievements.isNotEmpty()) {
                var completStr = ""
                for (newItem in item.achievements)
                    completStr = completStr.plus("• $newItem").plus("\n")

                experienceAchievements.text = completStr
            } else {
                experienceAchievementsTitle.visibility = View.GONE
                experienceAchievements.visibility = View.GONE
            }
        }
    }

}