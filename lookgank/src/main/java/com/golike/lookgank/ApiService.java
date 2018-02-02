package com.golike.lookgank;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 *  api接口
 * @author admin
 * @date 2018/2/2
 */

public interface ApiService  {
    // http://gank.io/api/search/query/listview/category/Android/count/10/page/1

    @GET("search/query/listview/category/{type}/count/{count}/page/{page}")
    Observable<SearchData> getSearchData(@Path("type")String type,@Path("count")int count,@Path("page")int page);

    // http://gank.io/api/data/数据类型/请求个数/第几页

    @GET("data/{type}/{count}/{page}")
    Observable<SearchData> getHomeData(@Path("type")String type,@Path("count")int count,@Path("page")int page);


}
