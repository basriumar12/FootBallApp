package com.kotlinje.submit2.model.event

/**
 * Created by User on 19/05/2018.
 */
// add coruntines anko
//submission 4
//model favorite
class ModelFavorite(val id:Long?,
                    val eventId:String?,
                    val eventDate:String?,
                    val strHomeName:String?,
                    val strAwayName:String?,
                    val strScoreHome:String?,
                    val strScoreAway:String?) {

    //object nama tabel
    /*sesuaikan nama tabel
    field setiap tabel
    even_id
    even_date
    home team
    away team
    score home dan away*/
    companion object {
        const val TABLE_FAVORITE = "tabel_favorite"
        const val ID = "ID_"
        const val EVENT_ID = "event_id"
        const val EVENT_DATE = "event_tgl"
        const val HOME_TEAM = "team_home"
        const val AWAY_TEAM = "team_away"
        const val SCORE_HOME = "score_home_team"
        const val SCORE_AWAY = "score_away_team"
    }
}