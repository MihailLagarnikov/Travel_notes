package com.twosmalpixels.travel_notes.ui

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.ISharedPrefRepositoriy
import com.twosmalpixels.travel_notes.core.ui.base.ToolbarViewModel
import com.twosmalpixels.travel_notes.ui.add_expence.AddExpenceFragment
import com.twosmalpixels.travel_notes.ui.add_expence.AddExpenceViewModel
import com.twosmalpixels.travel_notes.ui.auth.AuthViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.java.standalone.KoinJavaComponent.inject

class MainActivity : AppCompatActivity() {
    private lateinit var authViewModel: AuthViewModel
    lateinit var  auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    lateinit var  storage: FirebaseStorage
    private val sharedPref: ISharedPrefRepositoriy by inject(ISharedPrefRepositoriy::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref.init(getPreferences(Context.MODE_PRIVATE))
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        initCloudFirestore()
        initViewModel()
        setStartFragment(getStartFragment())
        setSupportActionBar(toolbar)
        createToolbarObserver()

    }

    private fun initViewModel(){
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    private fun initCloudFirestore(){
        db = Firebase.firestore
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings
    }

    private fun createToolbarObserver(){
        val toolBarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel::class.java)
        toolBarViewModel.toolbarParam.observe(this, Observer { t ->
            toolbar.visibility = t.visibl
            supportActionBar?.setDisplayHomeAsUpEnabled(t.backButtonVisiblity)
            supportActionBar?.title = t.title
        } )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getStartFragment(): Int {
        return if (authViewModel.isNeedAuth(auth)){
            R.id.authFragment
        }else{
            R.id.youTravelsFragment
        }
    }

    private fun setStartFragment(fragmentId: Int){
        val navHost =
            supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment?
        val navController = navHost!!.navController
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.nav_graph)
        graph.startDestination = fragmentId
        navController.graph = graph
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == AddExpenceFragment.PERMISION_LOCATION) {
            val addExpenceViewModel =
                ViewModelProviders.of(this).get(AddExpenceViewModel::class.java)
            addExpenceViewModel.isHaveLocationPermision.value =grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
