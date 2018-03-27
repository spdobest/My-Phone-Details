package spinfotech.getphonedetails.ui.adapter

import android.content.pm.ApplicationInfo
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import spinfotech.getphonedetails.R
import kotlinx.android.synthetic.main.item_applist.view.*
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by root on 3/27/18.
 */
class AppListAdapter(private val listAPp: List<ApplicationInfo>) : RecyclerView.Adapter<AppListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_applist, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAPp.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindForecast(listAPp[position])
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvAppName = view.tvAppName
        var tvPackagename = view.tvPackageName
        fun bindForecast(forecast: ApplicationInfo) {
            with(forecast) {
                tvAppName.text = forecast.loadLabel(itemView.context.getPackageManager())
                tvPackagename.text = forecast.className
            }
        }
    }
}