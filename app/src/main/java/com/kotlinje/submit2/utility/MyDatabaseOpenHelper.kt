package com.kotlinje.submit2.utility

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kotlinje.submit2.model.ModelFavorite
import org.jetbrains.anko.db.*

/**
 * Created by User on 20/05/2018.
 */

    //kelas MyDatabaseOpenHelper fungsinya untuk SqliteOpenHelper
// add coruntines anko
//submission 4
class MyDatabaseOpenHelper (context: Context): ManagedSQLiteOpenHelper
(context,"FavoriteTeam.db",null,1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context):MyDatabaseOpenHelper{
            if (instance==null){
                instance = MyDatabaseOpenHelper(context.applicationContext)
            }
            return instance!!

        }


    }

    //create db
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ModelFavorite.TABLE_FAVORITE,true,
                ModelFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ModelFavorite.EVENT_ID to TEXT + UNIQUE,
                ModelFavorite.EVENT_DATE to TEXT,
                ModelFavorite.HOME_TEAM to TEXT,
                ModelFavorite.AWAY_TEAM to TEXT,
                ModelFavorite.SCORE_HOME to TEXT,
                ModelFavorite.SCORE_AWAY to TEXT)

    }
    //upgrade db
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ModelFavorite.TABLE_FAVORITE,true)
    }

}

    val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)