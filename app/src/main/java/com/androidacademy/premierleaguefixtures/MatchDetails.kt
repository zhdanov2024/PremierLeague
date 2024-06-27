package com.androidacademy.premierleaguefixtures

import android.os.Parcel
import android.os.Parcelable

data class MatchDetails(
    val matchNumber: Int,
    val roundNumber: Int,
    val dateUtc: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val score: String?,
    val homeScore: Int,
    val awayScore: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(matchNumber)
        parcel.writeInt(roundNumber)
        parcel.writeString(dateUtc)
        parcel.writeString(location)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeString(score)
        parcel.writeInt(homeScore)
        parcel.writeInt(awayScore)
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
