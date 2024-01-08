package com.example.supabasepractice.database

import android.content.Context
import android.util.Log
import com.example.supabasepractice.BuildConfig
import com.example.supabasepractice.model.Professor
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest


object SupabaseHelper {
    val client = createSupabaseClient(
        supabaseUrl = BuildConfig.Url,
        supabaseKey = BuildConfig.Key
    ) {
        install(Auth)
        install(ComposeAuth) {
            googleNativeLogin(serverClientId = BuildConfig.Id)
        }
        install(Postgrest)
    }

    fun getComposeAuth() = client.composeAuth


    suspend fun logout() {
        Log.e("TAG", "logout: ${getToken()}")
        client.auth.signOut()
        Log.e("TAG", "logout: ${getToken()}")
    }

    fun getToken(): String? = client.auth.currentAccessTokenOrNull()

    suspend fun read() {
//        client.auth.retrieveUser()
        client.auth.refreshCurrentSession()

        val gap = client.postgrest["Pro"].select().decodeList<Professor>()
        Log.e("TAG", "read: $gap")
    }

    suspend fun create() {
        val city = client.postgrest["Pro"].insert(Professor(6, "이푸름", "컴퓨터꽁학부"))
        Log.e("TAG", "read: $city")
    }

    suspend fun delete() {
        client.from("Pro").delete {
            filter {
                Professor::id eq 666
                eq("id", 1)
            }
        }
    }
}