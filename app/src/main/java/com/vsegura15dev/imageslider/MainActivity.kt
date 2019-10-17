package com.vsegura15dev.imageslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4)

        cursorLayout.amount = images.size
        cursorLayout.addCursorImages()

        val adapter = ImagePagerAdapter(images, this)
        imagesViewPager.adapter = adapter
        imagesViewPager.addOnPageChangeListener( object: ViewPager.OnPageChangeListener{

            override fun onPageScrollStateChanged(state: Int) {
                Log.d("1- state", state.toString())
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                if(positionOffsetPixels == 0 && positionOffset == 0.0f) {
                    Log.d("2- position", position.toString())
                    cursorLayout.updateCursor(position)
                }
            }

            override fun onPageSelected(position: Int) {
                Log.d("3- position", position.toString())
            }
        } )


    }
}

