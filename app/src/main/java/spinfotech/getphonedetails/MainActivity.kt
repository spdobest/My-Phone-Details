package spinfotech.getphonedetails

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import spinfotech.getphonedetails.utility.PhoneUtility
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import spinfotech.getphonedetails.ui.ApplistDialogFragment


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = "TAG"
        val RECORD_REQUEST_CODE: Int = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermissions()




        tvCopySimSirialNumber.setOnClickListener(View.OnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", tvSimSerialNumber.text)
            clipboard!!.setPrimaryClip(clip)

            Toast.makeText(this,"Copied",Toast.LENGTH_SHORT).show()

        })
        tvAppList.setOnClickListener(View.OnClickListener {
            ApplistDialogFragment().show(supportFragmentManager,"")
        })

        tvCopySimNumber.setOnClickListener(View.OnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", tvSimNumber.text)
            clipboard!!.setPrimaryClip(clip)
            Toast.makeText(this,"Copied",Toast.LENGTH_SHORT).show()
        })



    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequest()
        } else {
            val (hour, minute) = PhoneUtility.getSimNumber(this)
            tvSimNumber.text = minute
            tvSimSerialNumber.text = hour
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                RECORD_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user")
                } else {
                    Log.i(TAG, "Permission has been granted by user")


                    val (hour, minute) = PhoneUtility.getSimNumber(this)
                    tvSimNumber.text = minute
                    tvSimSerialNumber.text = hour
                }
            }
        }
    }
}
