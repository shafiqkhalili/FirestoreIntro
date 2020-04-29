package com.shafigh.firestoreintro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        val shoppingItems = mutableListOf<Item>()

        val itemsRef = db.collection("items")
        //Read continuously
        itemsRef.addSnapshotListener{snapshot, e ->
            if (snapshot != null) {
                shoppingItems.clear()
                for (document in snapshot.documents){
                    val newItem = document.toObject(Item::class.java)
                    if (newItem != null) {
                        shoppingItems.add(newItem)
                    }
                    println("Test : $newItem")
                }
            }
        }

        //Read data at once
       /*
        itemsRef.get().addOnSuccessListener {documentSnapshot ->
            for (document in documentSnapshot.documents){
                val newItem = document.toObject(Item::class.java)
                if (newItem != null) {
                    shoppingItems.add(newItem)
                }
                println("Test : $newItem")
            }
        }*/

        //Add only data to DB
       /* val item = Item("lök",false,"grönsak")
        db.collection("items").add(item)
        db.collection("items").document("onion").set(item)*/

        //Read Users data
       /* db.collection("users")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(
                            " SK ",
                            document.id + " => " + document.data
                        )
                    }
                } else {
                    Log.w(
                        " SK ",
                        "Error getting documents.",
                        task.exception
                    )
                }
            }*/
        // Create a new user with a first and last name
        /*val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "!!!",
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w("!!!", "Error adding document", e) }*/
    }
}
