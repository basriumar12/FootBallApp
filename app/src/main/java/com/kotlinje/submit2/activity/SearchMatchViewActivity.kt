package com.kotlinje.submit2.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.kotlinje.submit2.R
import com.kotlinje.submit2.adapter.AdapterListSearch
import com.kotlinje.submit2.model.search.EventItem
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.presenter.PresenterSearch
import com.kotlinje.submit2.view.ViewSearchMatch
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.content_search.*
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), ViewSearchMatch {
    override fun onDataLoaded(data: ResponseSearch?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun showEventList(data: List<EventItem>?) {
        liga.clear()
        if (data!=null){
            liga.addAll(data)
            Log.d("tag", "data search :"+data)


        }
        adapterList?.notifyDataSetChanged()
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onDataError() {
  //      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingProgress() {
        progressBarId.visibility = View.VISIBLE

//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingProgress() {
        progressBarId.visibility = View.INVISIBLE
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String?) {
        showToast("Error")
     //   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




    var load : ProgressBar? = null
    //var present : PresenterMain? =null
    var present : PresenterSearch? =null
    //var adapterList : AdapterListData? = null
    var adapterList : AdapterListSearch? = null
  //  var liga : MutableList<EventLiga> = mutableListOf()
    var liga : MutableList<EventItem> = mutableListOf()
    var hasil : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        rv_list_match.layoutManager = LinearLayoutManager(this@SearchMatchActivity)

        present = PresenterSearch(this, MatchRepository())


      /*  progressBarId.visibility = View.INVISIBLE
        adapterList = AdapterListData(liga){}
        rv_list_match.adapter = adapterList*/


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                progressBarId.visibility = View.VISIBLE
                hasil = query!!

                Log.e("query","search : " +query)
                if (query.length > 4 ) {
                    //present?.getMatchLast(hasil!!)
                    present?.getMatchSearch(hasil!!)

                    progressBarId.visibility = View.INVISIBLE
                    adapterList = AdapterListSearch(liga){
                      startActivity<DetailActivity>("idEvent" to it.idEvent)
                    }
                    rv_list_match.adapter = adapterList
                }
                return true
            //   return false
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {

            /*    hasil = newText!!

                Log.e("query","search :" +newText)
                if (newText.length > 6 ) {
                    present?.getMatchLast(hasil!!)
                    rv_list_match.layoutManager = LinearLayoutManager(this@SearchMatchActivity)
                    rv_list_match.adapter = adapterList
                }*/
                return false
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        //present?.getSearchMatch(hasil!!)



    }
}
