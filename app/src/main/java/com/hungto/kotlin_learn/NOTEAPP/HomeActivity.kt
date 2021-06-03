package com.hungto.kotlin_learn.NOTEAPP

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hungto.kotlin_learn.FragmentMain.HomeFragment
import com.hungto.kotlin_learn.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replayFragment(HomeFragment.newIntance(),false)
    }


    fun replayFragment(fragment: Fragment, istranstition: Boolean) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        if (istranstition) {
            fragmentTransition.setCustomAnimations(R.anim.left_to_right, R.anim.right_to_left)
        }
        fragmentTransition.add(R.id.frame_home, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragment = supportFragmentManager.fragments
        if (fragment.size == 0) {
            finish()
        }
    }
}