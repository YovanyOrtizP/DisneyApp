package com.example.disneyapp.ui.characters.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.data.model.DisneyResponse
import com.example.disneyapp.data.remote.DisneyApi
import com.example.disneyapp.util.ResponseType

class DisneyPaging(private val disneyApi: DisneyApi) : PagingSource<Int, DisneyData>() {
    //All things about calling the api fetching the list inside this method and when the network call executes
    // successfully, load returns: load result that page
    //if an error occurred when executing the network call, load returns: load result.error
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DisneyData> {
        return try {
            //to have the current page if there is not page, pass 1
            val currentPage = params.key ?: 1
            // get the function from the api and send current page
            val response = disneyApi.getCharacters(currentPage)
            if (response.isSuccessful) {
                response.body()?.let {
                    it.data?.let { result ->
                        LoadResult.Page(
                            data = result,
                            prevKey = if (currentPage == 1) null else -1,
                            nextKey = currentPage.plus(1)
                        )
                    } ?: throw Exception("Data is null")
                }?: throw Exception("Body response is null")
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DisneyData>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
