package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.search.EventItem
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.network.repository.MatchRepositoryCallback

/**
 * Created by User on 31/05/2018.
 */
interface SearchMatchView : MatchRepositoryCallback<ResponseSearch> {

    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showToast(message: String?)
    fun showEventList(data:List<EventItem>?)
}