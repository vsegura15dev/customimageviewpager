package com.vsegura15dev.imageslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class ImagePagerAdapter(var items: List<Int>, context: Context) : PagerAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view.run { equals(`object`) }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val viewItem = inflater.inflate(R.layout.slider_image_item, container, false)

        viewItem.let {

            val imageView = it.findViewById<ImageView>(R.id.itemImageView)
            imageView.setImageResource(items[position])
        }

        container.addView(viewItem, 0)

        return viewItem
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }

    override fun getCount(): Int = items.size
}