package br.com.waldirbaia.buscausuario.domain.repository

import android.content.Context
import br.com.waldirbaia.buscausuario.data.local.interfac.HistoryRepository
import br.com.waldirbaia.buscausuario.domain.entity.HistoryUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : HistoryRepository {
    private val preferes = context.getSharedPreferences("history_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val type = object: TypeToken<List<HistoryUser>>() {}.type


    override fun saveUserToHistory(user: HistoryUser) {
        val currentList = getGistory().toMutableList()
        currentList.removeAll{it.idUser == user.idUser}
        currentList.add(0, user)
        val limitedList = currentList.take(100)
        preferes.edit().putString("user_history", gson.toJson(limitedList)).apply()
    }

    override fun getGistory(): List<HistoryUser> {
        val json = preferes.getString("user_history", null) ?: return emptyList()
        return gson.fromJson(json,type)
    }

    override fun clearHistory() {
        preferes.edit().remove("user_history").apply()
    }
}