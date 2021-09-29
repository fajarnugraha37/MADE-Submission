package com.example.githubapp.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val USERNAME_ASC = "username_asc"
    const val USERNAME_DESC = "username_desc"

    fun getSortedQueryUsers(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM userEntities ")
        when (filter) {
            USERNAME_ASC -> {
                simpleQuery.append("ORDER BY login ASC")
            }
            USERNAME_DESC -> {
                simpleQuery.append("ORDER BY login DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryFavoriteUsers(filter: String): SimpleSQLiteQuery {
        val simpleQuery =
            StringBuilder().append("SELECT * FROM userEntities where favorite = 1 ")
        when (filter) {
            USERNAME_ASC -> {
                simpleQuery.append("ORDER BY login ASC")
            }
            USERNAME_DESC -> {
                simpleQuery.append("ORDER BY login DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}