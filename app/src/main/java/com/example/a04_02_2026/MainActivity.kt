package com.example.a04_02_2026

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import com.example.a04_02_2026.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController

    private val TAG = "MainActivitySafe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            drawerLayout = binding.drawerLayout
            navView = binding.navDrawer

            setSupportActionBar(binding.toolbar)

             binding.toolbar.navigationIcon =
                ContextCompat.getDrawable(this, R.drawable.menu)
            binding.toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.white))
            binding.toolbar.setNavigationOnClickListener {
                safeAction { drawerLayout.openDrawer(GravityCompat.START) }
            }


            binding.backRight.setOnClickListener {
                safeAction { onBackPressedDispatcher.onBackPressed() }
            }

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
            navController = navHostFragment.navController


            navController.addOnDestinationChangedListener { _, destination, _ ->
                safeAction {
                    binding.toolbar.title = when (destination.id) {
                        R.id.settingsFragment -> "Settings"
                        R.id.resultFragment -> "Results"
                        R.id.aboutFragment -> "About"
                        R.id.dialogFragment -> "Dialog"
                        R.id.snackbarFragment -> "Snackbar"
                        R.id.bottomSheetFragment -> "Bottom Sheet"
                        R.id.quizFragment -> "Quiz"
                        else -> "App"
                    }
                }
            }


            navView.setNavigationItemSelectedListener { item ->
                safeAction {
                    val currentDestination = navController.currentDestination?.id

                    when (item.itemId) {
                        R.id.settingsFragment -> if (currentDestination != R.id.settingsFragment)
                            navController.navigate(R.id.settingsFragment)
                        R.id.resultFragment -> if (currentDestination != R.id.resultFragment)
                            navController.navigate(R.id.resultFragment)
                        R.id.aboutFragment -> if (currentDestination != R.id.aboutFragment)
                            navController.navigate(R.id.aboutFragment)
                    }

                    drawerLayout.closeDrawers()
                }
                true
            }

            val navDialog = findViewById<ImageView>(R.id.navDialog)
            val navSnackbar = findViewById<ImageView>(R.id.navSnackbar)
            val navBottomSheet = findViewById<ImageView>(R.id.navBottomSheet)
            val navQuiz = findViewById<ImageView>(R.id.navQuiz)

            val navIcons = listOf(navDialog, navSnackbar, navBottomSheet, navQuiz)

            fun selectIcon(selected: ImageView) {
                safeAction {
                    navIcons.forEach { icon ->
                        icon.setColorFilter(
                            if (icon == selected)
                                ContextCompat.getColor(this, R.color.ios_blue)
                            else
                                ContextCompat.getColor(this, R.color.ios_gray)
                        )
                    }
                }
            }

            navDialog.setOnClickListener {
                safeAction {
                    if (navController.currentDestination?.id != R.id.dialogFragment)
                        navController.navigate(R.id.dialogFragment)
                    selectIcon(navDialog)
                }
            }

            navSnackbar.setOnClickListener {
                safeAction {
                    if (navController.currentDestination?.id != R.id.snackbarFragment)
                        navController.navigate(R.id.snackbarFragment)
                    selectIcon(navSnackbar)
                }
            }

            navBottomSheet.setOnClickListener {
                safeAction {
                    if (navController.currentDestination?.id != R.id.bottomSheetFragment)
                        navController.navigate(R.id.bottomSheetFragment)
                    selectIcon(navBottomSheet)
                }
            }

            navQuiz.setOnClickListener {
                safeAction {
                    if (navController.currentDestination?.id != R.id.quizFragment)
                        navController.navigate(R.id.quizFragment)
                    selectIcon(navQuiz)
                }
            }

             selectIcon(navDialog)


            drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

                override fun onDrawerOpened(drawerView: View) {
                    safeAction {
                        val menuContainer = navView.getChildAt(0) as? ViewGroup ?: return@safeAction

                        for (i in 0 until menuContainer.childCount) {
                            val item = menuContainer.getChildAt(i)
                            val anim = AnimationUtils.loadAnimation(
                                this@MainActivity,
                                R.anim.slide_fade
                            )
                            anim.startOffset = (i * 70).toLong()
                            item.startAnimation(anim)
                        }
                    }
                }

                override fun onDrawerClosed(drawerView: View) {}
                override fun onDrawerStateChanged(newState: Int) {}
            })

        } catch (e: Exception) {
            Log.e(TAG, "Error in onCreate: ${e.localizedMessage}", e)
        }
    }

    private fun safeAction(action: () -> Unit) {
        try {
            action()
        } catch (e: Exception) {
            Log.e(TAG, "SafeAction error: ${e.localizedMessage}", e)
        }
    }
}
