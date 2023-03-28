package com.sultonbek1547.realtimedbfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.sultonbek1547.realtimedbfirebase.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("xabarlar")

        binding.btnSave.setOnClickListener {
            val key = reference.push().key
            reference.child(key!!)
                .setValue(MyMessage(key, binding.edtText.text.toString(),
                SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date())))
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }

loadData()
    }

    private fun loadData() {
        rvAdapter = RvAdapter()
        binding.myRv.adapter = rvAdapter
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<MyMessage>()
              val children  =snapshot.children
                for (child in children){
                    val value = child.getValue(MyMessage::class.java)
                    if (value!=null){
                        list.add(value)
                    }
                }
                rvAdapter.list.clear()
                rvAdapter.list.addAll(list)
                rvAdapter.notifyDataSetChanged()
                binding.myRv.scrollToPosition(rvAdapter.itemCount -1)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}