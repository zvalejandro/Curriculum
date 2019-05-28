package com.zaraos.curriculum.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.zaraos.curriculum.MyApplication

/**
 * Created by alejandro on 6/12/17.
 */

class ResourceUtils private constructor() {

    init {
        throw AssertionError("You can not instantiate this class. Use its static utility " + "methods instead")
    }

    companion object {

        /**
         * Get color from a resource id
         *
         * @param context  The context
         * @param colorRes The resource identifier of the color
         * @return The resolved color value
         */
        fun getColor(context: Context, @ColorRes colorRes: Int): Int {
            return ContextCompat.getColor(context, colorRes)
        }

        /**
         * Get color from a resource id
         *
         * @param colorRes The resource identifier of the color
         * @return The resolved color value
         */
        fun getColor(@ColorRes colorRes: Int): Int {
            return ContextCompat.getColor(MyApplication.instance, colorRes)
        }

        /**
         * Get string from a resource id
         *
         * @param context   The context
         * @param stringRes The resource identifier of the string
         * @return The string value
         */
        fun getString(context: Context, @StringRes stringRes: Int): String {
            return context.getString(stringRes)
        }

        /**
         * Get string from a resource id
         *
         * @param stringRes The resource identifier of the string
         * @return The string value
         */
        fun getString(@StringRes stringRes: Int): String {
            return MyApplication.instance.getString(stringRes)
        }

        /**
         * Get dimension in pixels from its resource id
         *
         * @param context  The context
         * @param dimenRes The resource identifier of the dimension
         * @return The dimension in pixels
         */
        fun getDimensionInPx(context: Context, @DimenRes dimenRes: Int): Float {
            return context.resources.getDimension(dimenRes)
        }


        /**
         * Get drawable from a resource id
         *
         * @param context     The context
         * @param drawableRes The resource identifier of the drawable
         * @return The drawable
         */
        fun getDrawable(context: Context, @DrawableRes drawableRes: Int): Drawable? {
            return ContextCompat.getDrawable(context, drawableRes)
        }

        /**
         * Get drawable from a resource id
         *
         * @param drawableRes The resource identifier of the drawable
         * @return The drawable
         */
        fun getDrawable(@DrawableRes drawableRes: Int): Drawable? {
            return ContextCompat.getDrawable(MyApplication.instance, drawableRes)
        }

        /**
         * Get drawable from a resource id
         *
         * @param drawableRes The resource identifier of the drawable
         * @return The drawable
         */
        fun getBitmap(@DrawableRes drawableRes: Int): Bitmap {
            return BitmapFactory.decodeResource(MyApplication.instance.resources, drawableRes)
        }
    }
}
