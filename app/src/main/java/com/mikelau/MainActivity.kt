package com.mikelau

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mikelau.zenith.dialogs.DialogHelper
import com.mikelau.zenith.interfaces.AlertInterface
import com.mikelau.zenith.utils.CheckNetwork
import com.mikelau.zenith.utils.DebugLog
import com.mikelau.zenith.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DebugLog Test
        DebugLog.showError(this, "HELLO")
        DebugLog.showError(this, "HEY")
        DebugLog.showError(this, "HI")

        // CheckNetwork Test
        CheckNetwork.isInternetAvailable(this)

        tvMain.setSafeOnClickListener {
            // Test Safe Click Listener
            Toast.makeText(this@MainActivity, "Hello World " + i++, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        DialogHelper(this).getConfirmDialog(getString(R.string.app_name),
                "EXIT APP?",
                getString(android.R.string.yes),
                getString(android.R.string.no),
                object : AlertInterface.WithNoNeutral {
                    override fun positiveMethod(dialog: DialogInterface, id: Int) {
                        finish()
                    }

                    override fun negativeMethod(dialog: DialogInterface, id: Int) {}
                })?.show()
    }

}
