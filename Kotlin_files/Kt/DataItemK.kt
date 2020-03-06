package ltd.nickolay.listclick.Kt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItemK(val image: Int, val title: String, val subText: String): Parcelable