package com.kotlinje.submit2.presenter

import android.util.Log
import com.kotlinje.submit2.model.search.EventItem
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.network.repository.MatchRepositoryCallback
import com.kotlinje.submit2.view.SearchMatchView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class PresenterSearch
(private val view  : SearchMatchView, private val matchRepository: MatchRepository)
{


    fun getMatchSearch(id: String) {
        view.showLoadingProgress()
        matchRepository.getSearchMatch(id, object : MatchRepositoryCallback<ResponseSearch?> {
            override fun onDataLoaded(data: ResponseSearch?) {
                view.onDataLoaded(data)
                view.hideLoadingProgress()
                async(UI){

                    val dataMatch = bg { data?.event }
                    Log.e("data","coba 2"+dataMatch)
                    //view.showEventList(dataMatch.await())
                    view.showEventList(dataMatch.await() as List<EventItem>?)
                }

            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }



}