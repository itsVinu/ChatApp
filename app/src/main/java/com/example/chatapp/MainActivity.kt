package com.example.chatapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to get the home screen opened instead of login screen if already logged in
        auth.addAuthStateListener {
            if(it.currentUser != null){
                startActivity(Intent(this,ChatActivity::class.java))
            }else{

            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        auth.addAuthStateListener {
//            if(it.currentUser != null){
//                startActivity(Intent(this,ChatActivity::class.java))
//            }else{
//
//            }
//        }
//    }

    private fun signUpWithEmail() {
        auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
            .addOnCompleteListener {
                root.showSnack("Account Created Succesfully")
            }.addOnCanceledListener {

            }.addOnFailureListener {
                root.showSnack("Error : ${it.localizedMessage}")
            }

    }

    fun View.showSnack(msg: String) {
        val snack = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
        snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snack.show()
    }

    fun submit(view: View) {
        signUpWithEmail()
    }
}
