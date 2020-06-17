package com.example.remote.tmdbmovie.model


import com.example.domain.tmdbmovie.model.SpokenLanguage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguageRaw(
    @Json(name = "iso_639_1")
    val iso6391: String, // en
    @Json(name = "name")
    val name: String // English
)

fun SpokenLanguageRaw.domainSpokenLanguagesModel() = SpokenLanguage(
    iso6391 = iso6391,
    name = name
)