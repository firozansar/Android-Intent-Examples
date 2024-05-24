package info.firozansari.example.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import info.firozansari.example.R
import info.firozansari.example.utils.Constant

class CreateUserActivity : AppCompatActivity() {
    private lateinit var userName: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        setUpViews()
    }

    private fun setUpViews() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        userName = findViewById<View>(R.id.username) as EditText
        submitBtn = findViewById<View>(R.id.submit_btn) as Button
        submitBtn.setOnClickListener { finish() }

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Nothing to do", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun finish() {
        val intent = Intent()
        intent.putExtra(Constant.USER_NAME, userName.text.toString())
        setResult(RESULT_OK, intent)
        super.finish()
    }
}
