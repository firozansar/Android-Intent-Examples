package info.firozansari.android_intent_example

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import info.firozansari.android_intent_example.MainFragment.OnFragmentInteractionListener

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, MainFragment())
                    .commit()
        }
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { startActivity(Intent(this@MainActivity, UserOverviewActivity::class.java)) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val shareItem = menu.findItem(R.id.action_share)
        val myShareActionProvider = MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        val send = Intent()
        send.action = Intent.ACTION_SEND
        send.putExtra(Intent.EXTRA_TEXT, "Some random text to share!")
        send.type = "text/plain"
        myShareActionProvider.setShareIntent(send)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }


    override fun onFragmentInteraction(intent: Intent?) {
        intent?.let{
            startActivity(it)
        }
    }
}