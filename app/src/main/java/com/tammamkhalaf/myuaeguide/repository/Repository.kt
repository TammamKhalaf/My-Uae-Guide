package com.tammamkhalaf.myuaeguide.repository


import android.content.Context
import androidx.room.Room
import com.tammamkhalaf.myuaeguide.categories.hereDeveloper.discoverExplore.DiscoverExploreResponse
import com.tammamkhalaf.myuaeguide.categories.hereDeveloper.discoverExplore.hereDevDiscoverExploreApiService
import com.tammamkhalaf.myuaeguide.categories.hereDeveloper.discoverHere.DiscoverHereResponse
import com.tammamkhalaf.myuaeguide.categories.hereDeveloper.discoverHere.LockupEntrypoint.PlaceResponse
import com.tammamkhalaf.myuaeguide.categories.hereDeveloper.discoverHere.hereDevDiscoverHereApiService
import com.tammamkhalaf.myuaeguide.categories.hereDeveloper.discoverHere.LockupEntrypoint.hereDevLockupApiService
import com.tammamkhalaf.myuaeguide.databases.hereDeveloper.PlacesDao
import com.tammamkhalaf.myuaeguide.databases.hereDeveloper.AppDatabase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

class Repository {
    private val hereDevDiscoverExploreService: hereDevDiscoverExploreApiService
    private val hereDevDiscoverHereService: hereDevDiscoverHereApiService
    private val hereDevLockupService: hereDevLockupApiService
    private val placesDao:PlacesDao

            @Inject
    constructor(hereDevDiscoverExploreService: hereDevDiscoverExploreApiService,
                hereDevLockupService: hereDevLockupApiService,
                hereDevDiscoverHereService: hereDevDiscoverHereApiService,
                placesDao: PlacesDao) {
        this.hereDevDiscoverExploreService = hereDevDiscoverExploreService
        this.hereDevDiscoverHereService = hereDevDiscoverHereService
        this.hereDevLockupService = hereDevLockupService
        this.placesDao = placesDao
    }

    /**
     * return data from api
     * OpenTripApiModel
     */


    fun discoverExplorePlacesHereDeveloper(app_id:String,
                                            app_code:String,
                                            position:String,//  circle_or_bounding_box:String,
                                           cat_list:List<String>)
    :Observable<DiscoverExploreResponse>? {// in or at circle_or_bounding_box
        return hereDevDiscoverExploreService.getRecommendedPlaces(app_id,app_code,position,cat_list)

    }

    fun discoverHerePlaceHereDeveloper(pvid:String,
                                       app_id:String,
                                       app_code:String,
                                       id: String)
    : Observable<PlaceResponse>? {
        return hereDevLockupService.lookupPlace(app_id,app_code,pvid,id)
    }

    fun discoverHerePlacesHereDeveloper(app_id:String,
                                            app_code:String,
                                            position:String,//  circle_or_bounding_box:String,
                                           cat_list:List<String>)
    :Observable<DiscoverHereResponse>? {// in or at circle_or_bounding_box
        return hereDevDiscoverHereService.getHerePlaces(app_id,app_code,position,cat_list)
    }


    companion object {
        private const val TAG = "Repository"
    }
}