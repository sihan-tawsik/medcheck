package com.axionesl.medcheck.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.axionesl.medcheck.R
import com.axionesl.medcheck.domains.Test
import com.axionesl.medcheck.utils.PatientAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PatientActivity : AppCompatActivity() {
    private lateinit var addTest: FloatingActionButton
    private lateinit var testList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        title = "Tests"
        bindWidgets()
        bindListeners()
    }

    private fun bindWidgets() {
        addTest = findViewById(R.id.add_test)
        testList = findViewById(R.id.test_list)
    }

    private fun bindListeners() {
        addTest.setOnClickListener {
            startActivity(Intent(this, AddTestActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        testList.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        prepareRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun prepareRecyclerView() {
        val uid = Firebase.auth.currentUser!!.uid
        val ref = Firebase.database.reference.child("/tests/")
        ref.keepSynced(true)
        val query =
            ref.orderByChild("patient").equalTo(uid)
        val options = FirebaseRecyclerOptions.Builder<Test>()
            .setQuery(query, Test::class.java)
            .build()

        val adapter = PatientAdapter(this, options)
        testList.adapter = adapter
        adapter.startListening()
    }
}