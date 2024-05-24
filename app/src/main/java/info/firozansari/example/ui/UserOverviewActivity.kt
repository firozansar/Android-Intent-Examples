package info.firozansari.example.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import info.firozansari.example.R
import info.firozansari.example.utils.Constant

class UserOverviewActivity : AppCompatActivity() {
    private var textView1: TextView? = null

    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_overview)
        setUpView()
        handleIntent()
    }

    private fun handleIntent() {
        val intent = intent
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND == action && type != null) {
            if ("text/plain" == type) {
                handleSendText(intent) // Handle text being sent
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (sharedText != null) {
            textView1!!.text = sharedText
        }
    }


    private fun setUpView() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        textView1 = findViewById<View>(R.id.textView1) as TextView

        button = findViewById<View>(R.id.create_user_btn) as Button
        button!!.setOnClickListener {
            val intent = Intent(this@UserOverviewActivity, CreateUserActivity::class.java)
            startActivityForResult(intent, SUB_ACTIVITY_CREATE_USER)
        }

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Nothing here!", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }

    // This is the callback for the started activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SUB_ACTIVITY_CREATE_USER && resultCode == RESULT_OK) {
            val extras = data!!.extras
            if (extras != null) {
                val name = extras.getString(Constant.USER_NAME)
                textView1!!.text = name
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_user, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView =
            searchItem.actionView as SearchView?

        // Define the listener
        val expandListener: MenuItemCompat.OnActionExpandListener =
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    // Do something when action item collapses
                    return true // Return true to collapse action view
                }

                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    // Do something when expanded
                    return true // Return true to expand action view
                }
            }

        // Assign the listener to that action item
        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener)

        // Any other things you have to do when creating the options menu...
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val SUB_ACTIVITY_CREATE_USER: Int = 10
    }
}
