package uz.gita.vocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import uz.gita.vocabulary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.searchFragment -> showBottomNav()
                R.id.bookmarkFragment -> showBottomNav()
                R.id.historyFragment ->showBottomNav()
                else -> hideBottomNav()
            }

        }
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)


        binding.bottomNavigation.setOnItemReselectedListener(NavigationBarView.OnItemReselectedListener {
        })

    }

    private fun showBottomNav(){
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNav(){
        binding.bottomNavigation.visibility = View.GONE
    }


}
