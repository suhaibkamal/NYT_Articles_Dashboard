package com.sk.nytarticlesdashboard.flow.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sk.movieshare.base.BaseViewModel
import com.sk.movieshare.base.Resource
import com.sk.nytarticlesdashboard.models.model.ArticleCellModel
import com.sk.nytarticlesdashboard.models.model.ArticleModel
import com.sk.nytarticlesdashboard.models.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
public class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :BaseViewModel(){

    var articlesState = MutableStateFlow(ArrayList<ArticleCellModel>())
    var userState= MutableStateFlow(UserModel("","","","","","",0))
    val searchText = MutableStateFlow("")
    init {
        loadArticlesList()
        getUserById()
    }

    fun getUserById(){
        viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val userModel: UserModel = homeRepository.getUserById()
                   userState.value=userModel
                }

        }
    }




    val articles = searchText
        .debounce(1000L)
        .onEach { showLoading.update { true } }
        .combine(articlesState) { text, articles ->
            if(text.isBlank()) {
                articles
            } else {
                delay(2000L)
                articles.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { showLoading.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            articlesState.value
        )
    fun onSearchTextChange(text: String) {
        searchText.value = text
    }
    fun logout(){
        viewModelScope.launch() {
            homeRepository.removeUserSession()
        }
    }

    fun loadArticlesList() {
         viewModelScope.launch() {
             showLoading.value=true
            when(val result = homeRepository.getArticleList()){
                is Resource.Success ->{
                    showLoading.value = false
                    val articleList = result.data?.results;

                    var articles:ArrayList<ArticleCellModel> = ArrayList()
                    if (articleList != null) {
                        for (article:ArticleModel in articleList){
                            if(article.media.isNotEmpty()) {
                                if( article.media[0].metadata.isNotEmpty()) {
                                    var articleCell = ArticleCellModel(
                                        article.id,
                                        article.title,
                                        article.media[0].metadata[0].url,
                                        article.date
                                    )
                                    articles.add(articleCell)
                                }else{
                                    var articleCell = ArticleCellModel(
                                        article.id,
                                        article.title,
                                        null,
                                        article.date
                                    )
                                    articles.add(articleCell)
                                }
                            }else{
                                var articleCell = ArticleCellModel(
                                    article.id,
                                    article.title,
                                    null,
                                    article.date
                                )
                                articles.add(articleCell)
                            }
                        }
                        
                        articlesState.value = articles
                        homeRepository.insertArticle(articles)
                    }
                   
                }

                is Resource.Error->{
                    showLoading.value = false

                  articlesState.value = ArrayList(homeRepository.getArticles())
                }

                else -> {
                    showLoading.value = false

                    articlesState.value = ArrayList(homeRepository.getArticles())
                }
            }
            }
        }
}