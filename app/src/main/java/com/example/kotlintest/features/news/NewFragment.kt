package com.example.kotlintest.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintest.KedditApp
import com.example.kotlintest.R
import com.example.kotlintest.commons.InfiniteScrollListener
import com.example.kotlintest.commons.RedditNews
import com.example.kotlintest.commons.RedditNewsItem
import com.example.kotlintest.commons.RxBaseFragment
import com.example.kotlintest.commons.extensions.inflate
import com.example.kotlintest.features.news.adapter.NewsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class NewsFragment : RxBaseFragment() {

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    @Inject
    lateinit var newsManager: NewsManager
    private var redditNews: RedditNews? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KedditApp.newsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //val view = inflater.inflate(R.layout.news_fragment, container, false)
        return container?.inflate(R.layout.news_fragment)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }
    private fun requestNews() {
        /**
         * first time will send empty string for 'after' parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the after param.
         */
        val subscription = newsManager.getNews(redditNews?.after ?: "")
            .subscribeOn(Schedulers.io())
            .subscribe (
                { retrievedNews ->
                    redditNews = retrievedNews
                    (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                },
                { e ->
                    Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                }
            )
        subscriptions.add(subscription)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.size > 0) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }


    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = NewsAdapter()
        }
    }
}
