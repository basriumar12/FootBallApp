package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.EventLiga

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
interface MainView {
    //fun showLiga()
    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showToast(message: String?)
    fun showEventList(data:List<EventLiga>?)
}