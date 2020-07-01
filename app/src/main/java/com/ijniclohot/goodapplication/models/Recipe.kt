package com.ijniclohot.goodapplication.models

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Recipe(
    var title: String?, var publisher: String?,
    var ingredients: ArrayList<String>?, var recipe_id: String?, var image_url: String?,
    var social_rank: Double?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    constructor() : this("", "", null, "", "", 0.0)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(publisher)
        parcel.writeStringList(ingredients)
        parcel.writeString(recipe_id)
        parcel.writeString(image_url)
        social_rank?.let { parcel.writeDouble(it) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}