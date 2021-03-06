package com.tammamkhalaf.myuaeguide.categories.nearbyPlaces.network

import com.tammamkhalaf.myuaeguide.categories.nearbyPlaces.Location
import com.tammamkhalaf.myuaeguide.categories.nearbyPlaces.Place
import com.tammamkhalaf.myuaeguide.categories.nearbyPlaces.TrueWayPlacesResponse
import retrofit2.http.*
import io.reactivex.rxjava3.core.Observable

import java.util.*

interface TrueWayPlacesServiceApi {
    @Headers("Content-Type:application/x-www-form-urlencoded",
            "x-rapidapi-key:$RAPIDAPI_KEY",
            "x-rapidapi-host:$RAPIDAPI_TRUEWAY_PLACES_HOST")
    @GET("FindPlaceByText")
    fun findPlacesByText(@Query("text") text: String?,
                         @Query("language") language: String?): Observable<TrueWayPlacesResponse?>?

    @Headers("Content-Type:application/x-www-form-urlencoded",
            "x-rapidapi-key:$RAPIDAPI_KEY",
            "x-rapidapi-host:$RAPIDAPI_TRUEWAY_PLACES_HOST")
    @GET("FindPlacesNearby")
    fun findPlacesNearby(@QueryMap filters: HashMap<String, String>?,
                         @Query("radius") radius: Int,
                         @Query("language") language: String?): Observable<TrueWayPlacesResponse?>?

    //todo this just for demonstration purposes >>>
    @Headers("Content-Type:application/x-www-form-urlencoded",
            "x-rapidapi-key:$RAPIDAPI_KEY",
            "x-rapidapi-host:$RAPIDAPI_TRUEWAY_PLACES_HOST")
    @POST("places")
    fun addPlace(@Body newPlace: Place?): Observable<Place?>?

    @Headers("Content-Type:application/x-www-form-urlencoded",
            "x-rapidapi-key:$RAPIDAPI_KEY",
            "x-rapidapi-host:$RAPIDAPI_TRUEWAY_PLACES_HOST")
    @FormUrlEncoded
    @PUT("places/{id}")
    fun updatePlaceById(@Path("id") ID: Int,
                        @Field("name") name: String?,
                        @Field("address") address: String?,
                        @Field("phone_number") phoneNumber: String?,
                        @Field("website") website: String?,
                        @Field("location") location: Location?,
                        @Field("types") types: List<String?>?): Observable<Place?>?

    @Headers("Content-Type:application/x-www-form-urlencoded",
            "x-rapidapi-key:$RAPIDAPI_KEY",
            "x-rapidapi-host:$RAPIDAPI_TRUEWAY_PLACES_HOST")
    @DELETE("places/{id}")
    fun deleteSpecificPlace(@Path("id") ID: Int): Observable<Void?>?

    companion object {
        const val BASE_URL = "https://trueway-places.p.rapidapi.com/"
        const val RAPIDAPI_KEY = "cb558f9b05mshc727d913f6cde72p158d4fjsn1c4bf14f36d0"
        const val RAPIDAPI_TRUEWAY_PLACES_HOST = "trueway-places.p.rapidapi.com"
    }
}