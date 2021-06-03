package com.hungto.kotlin_learn

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.hungto.kotlin_learn.FragmentMain.*
import com.hungto.kotlin_learn.FragmentMain.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //icon nav addFragment(HomeFragment.)
        addFragment(HomeFragment.newIntance())
        navbottom_main.show(0)
        navbottom_main.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
        navbottom_main.add(MeowBottomNavigation.Model(1, R.drawable.ic_explore))
        navbottom_main.add(MeowBottomNavigation.Model(2, R.drawable.ic_chat))
        navbottom_main.add(MeowBottomNavigation.Model(3, R.drawable.ic_notfication))
        navbottom_main.add(MeowBottomNavigation.Model(4, R.drawable.ic_person))

        navbottom_main.setOnClickMenuListener {
            when (it.id) {
                0 -> {
                    replayFragment(HomeFragment.newIntance())
                    Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    replayFragment(ExploreFragment.newIntance())
                    Toast.makeText(this, "explore", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    replayFragment(ChatFragment.newInstance())
                    Toast.makeText(this, "chat", Toast.LENGTH_SHORT).show()
                }
                3 -> {
                    replayFragment(NotificationFragment.newIntance())
                    Toast.makeText(this, "notification", Toast.LENGTH_SHORT).show()
                }
                4 -> {
                    replayFragment(UserFragment.newIntance())
                    Toast.makeText(this, "user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun replayFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_main, fragment)
            .addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.frame_main, fragment)
            .addToBackStack(Fragment::class.java.simpleName).commit()
    }
}