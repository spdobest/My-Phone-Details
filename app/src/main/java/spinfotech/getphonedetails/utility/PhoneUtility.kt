package spinfotech.getphonedetails.utility

import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.provider.Settings
import android.telephony.TelephonyManager
import android.provider.Settings.Secure





/**
 * Created by root on 3/27/18.
 */
class PhoneUtility {

    companion object {
         fun getSimNumber(context:Context){
            val telemamanger = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val getSimSerialNumber = telemamanger.simSerialNumber
            val getSimNumber = telemamanger.line1Number
        }


        fun getDeviceIMEI(context:Context): String? {
            var deviceUniqueIdentifier: String? = null
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (null != tm) {
                deviceUniqueIdentifier = tm.deviceId
            }
            if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length) {
                deviceUniqueIdentifier = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)
            }
            return deviceUniqueIdentifier
        }


        fun getOperator(context:Context): String? {
            val telemamanger = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val getSimSerialNumber = telemamanger.simOperator

            return getSimSerialNumber
        }
        fun getSimISO(context:Context): String? {
            val telemamanger = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val getSimSerialNumber = telemamanger.simCountryIso

            return getSimSerialNumber
        }
        fun getSim1State(context:Context): String? {
            val telemamanger = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simState1 = telemamanger.getSimState(0)
            return simState1
        }
        fun getSim2State(context:Context): String? {
            val telemamanger = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simState2 = telemamanger.getSimState(1)
            return simState2
        }

        fun getSim2State(context:Context): String? {
            val telemamanger = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simState2 = telemamanger.(1)
            return simState2
        }

    }

}