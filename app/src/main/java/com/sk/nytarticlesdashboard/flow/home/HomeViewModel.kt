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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
public class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :BaseViewModel(){

    var articlesState by mutableStateOf(ArrayList<ArticleCellModel>())
    var userState by mutableStateOf(UserModel("","","","","","",null))

    init {
        loadArticlesList()
        getUserById()
    }

    fun getUserById(){
        viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val userModel: UserModel = homeRepository.getUserById()
                   userState = userModel
                }

        }
    }

    fun loadArticlesList() {
         viewModelScope.launch() {
            when(val result = homeRepository.getArticleList()){
                is Resource.Success ->{
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
                        
                        articlesState = articles
                        homeRepository.insertArticle(articles)
                    }
                   
                }

                is Resource.Error->{

                  articlesState = ArrayList(homeRepository.getArticles())
                }

                else -> {
                    articlesState = ArrayList(homeRepository.getArticles())
                }
            }
            }
        }
}