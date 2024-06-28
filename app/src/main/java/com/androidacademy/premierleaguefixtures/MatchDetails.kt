package com.androidacademy.premierleaguefixtures

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchDetails(
    @Json(name = "MatchNumber") val matchNumber: Int,
    @Json(name = "RoundNumber") val roundNumber: Int,
    @Json(name = "DateUtc") val dateUtc: String,
    @Json(name = "Location") val location: String,
    @Json(name = "HomeTeam") val homeTeam: String,
    @Json(name = "AwayTeam") val awayTeam: String,
    @Json(name = "Group") val group: String?,
    @Json(name = "HomeTeamScore") val homeTeamScore: Int?,
    @Json(name = "AwayTeamScore") val awayTeamScore: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(matchNumber)
        parcel.writeInt(roundNumber)
        parcel.writeString(dateUtc)
        parcel.writeString(location)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeString(group)
        parcel.writeValue(homeTeamScore)
        parcel.writeValue(awayTeamScore)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchDetails> {
        override fun createFromParcel(parcel: Parcel): MatchDetails {
            return MatchDetails(parcel)
        }

        override fun newArray(size: Int): Array<MatchDetails?> {
            return arrayOfNulls(size)
        }
    }
}
