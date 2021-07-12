package com.example.toothpickex

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    //private var nameEditText: EditText? = null
    //private var emailEditText: EditText? = null
//
    //private var loadButton: Button? = null
    //private var saveButton: Button? = null



    @Inject
    lateinit var userRepository: UserRepository // Если это поле помечено аннотацией @Inject значит в нее поставляются зависимости от UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //userRepository = UserRepository(getSharedPreferences("app.pref", MODE_PRIVATE))
        //bindViews();
        initButtons();

        val appScope: Scope = Toothpick.openScope("APP")

        Toothpick.inject(this, appScope) // this - объект, в который нужно поставить зависимости(в нашем случае наша активность)
    }

    //private fun bindViews() {
    //    nameEditText = findViewById(R.id.edit_text_name)
    //    emailEditText = findViewById(R.id.edit_text_email)
    //    loadButton = findViewById(R.id.button_load)
    //    saveButton = findViewById(R.id.button_save)
    //}

    private fun initButtons() {
        button_load!!.setOnClickListener { v: View? -> loadUser() }
        button_save!!.setOnClickListener { v: View? -> saveUser() }
    }

    private fun loadUser() {
        showUser(userRepository.user)
    }

    private fun showUser(user: User?) {
        fillEditText(edit_text_name!!, user!!.name)
        fillEditText(edit_text_email!!, user.email)
    }

    private fun fillEditText(editText: EditText, text: String) {
        editText.setText(text)
        editText.setSelection(text.length)
    }

    private fun saveUser() {
        val user = User(
            edit_text_name!!.text.toString().trim { it <= ' ' },
            edit_text_email!!.text.toString().trim { it <= ' ' }
        )
        userRepository.saveUser(user)
    }
}