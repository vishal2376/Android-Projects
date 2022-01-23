package com.example.birthdayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.birthdayapp.BirthdayGreetingActivity.Companion.NAME_EXTRA
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	fun createBirthdayCard(view: android.view.View) {

		val name = editTextPersonName.editableText.toString()

		val intent = Intent(this,BirthdayGreetingActivity::class.java)
		intent.putExtra(NAME_EXTRA,name)

		startActivity(intent)

//		Toast.makeText(this,"Hello $name",Toast.LENGTH_LONG).show()
	}

}