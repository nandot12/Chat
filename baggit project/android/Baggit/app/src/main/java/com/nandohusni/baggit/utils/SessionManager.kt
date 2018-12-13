package com.nandohusni.baggit.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by imastudio on 2/9/16.
 */
class SessionManager//konstruktor
    (internal var c: Context) {
    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor

    internal var PRIVATE_MODE = 0

    //0 agar cuma bsa dibaca hp itu sendiri
    internal var PREF_NAME = "GojegClonePref"
    //mendapatkan token
    var token: String? = null
        get() = pref.getString(KEY_TOKEN, "")
    //cek login
    val isLoggedIn: Boolean
        get() = pref.getBoolean(KEY_LOGIN, false)
    var idLevel: String?
        get() = pref.getString("idLevel", "")
        set(v) {
            editor.putString("idLevel", v)
            editor.commit()
        }
    var nama: String?
        get() = pref.getString("nama", "")
        set(nama) {
            editor.putString("nama", nama)
            editor.commit()
        }
    var email: String?
        get() = pref.getString("email", "")
        set(email) {
            editor.putString("email", email)
            editor.commit()
        }
    var hp: String?
        get() = pref.getString("phone", "")
        set(phone) {
            editor.putString("phone", phone)
            editor.commit()
        }
    var idUser: String?
        get() = pref.getString("id_user", "")
        set(id_user) {
            editor.putString("id_user", id_user)
            editor.commit()
        }
    var from: String?
        get() = pref.getString("awal", "")
        set(id_user) {
            editor.putString("awal", id_user)
            editor.commit()
        }
    var destinations: String?
        get() = pref.getString("destination", "")
        set(id_user) {
            editor.putString("destination", id_user)
            editor.commit()
        }
    var avatar: String?
        get() = pref.getString("avatar", "")
        set(v) {
            editor.putString("avatar", v)
            editor.commit()
        }
    var iRestoran: String?
        get() = pref.getString("id_restoran", "")
        set(restoran) {
            editor.putString("id_restoran", restoran)
            editor.commit()
        }
    var topUp: String?
        get() = pref.getString("topup", "")
        set(topUp) {
            editor.putString("topup", topUp)
            editor.commit()
        }

    init {
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    //membuat session login
    fun createLoginSession(token: String) {
        editor.putString(KEY_TOKEN, token)
        editor.putBoolean(KEY_LOGIN, true)
        editor.commit()
        //commit digunakan untuk menyimpan perubahan
    }

    //logout user
    fun logout() {
        editor.clear()
        editor.commit()
    }

    companion object {
        private val KEY_TOKEN = "tokenLogin"
        private val KEY_LOGIN = "isLogin"
    }


}
