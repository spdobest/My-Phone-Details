package spinfotech.getphonedetails.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.dialogfragment_kotlin.*
import spinfotech.getphonedetails.R
import kotlinx.android.synthetic.main.dialogfragment_kotlin.view.*
import spinfotech.getphonedetails.ui.adapter.AppListAdapter

class ApplistDialogFragment : DialogFragment(), View.OnClickListener {

    lateinit var recyclerViewAppList:RecyclerView

    var appList: MutableList<ApplicationInfo> = ArrayList()
    lateinit var listAdapter: AppListAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBundle = arguments
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.dialogfragment_kotlin, container, false)
        recyclerViewAppList = view.findViewById(R.id.recyclerViewAppList)
        initView()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set click listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setWindowAnimations(
                    R.style.styleDialogFragment)
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
    }

    private fun initView() {

        println(recyclerViewAppList)



        LoadApplications().execute("")

    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    private fun getListOfAppsInstalled(list: List<ApplicationInfo>): List<ApplicationInfo> {
        val applist = java.util.ArrayList<ApplicationInfo>()
        for (info in list) {
            try {
                if (null != activity.packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return applist
    }

    private inner class LoadApplications : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String): String? {
            val appListNew = getListOfAppsInstalled(activity.packageManager.getInstalledApplications(PackageManager.GET_META_DATA))
            appList = ArrayList()
            appList.addAll(appListNew as List<ApplicationInfo>)
//            applicationName = "" + info.loadLabel(AppContext.getInstance().getPackageManager())
            return "asdas"
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            recyclerViewAppList?.layoutManager = LinearLayoutManager(activity)
            listAdapter = AppListAdapter(appList)
            recyclerViewAppList.adapter = listAdapter

        }
    }
}
