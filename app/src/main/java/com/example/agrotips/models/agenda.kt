package com.example.agrotips.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agenda(
    val scheduled: MutableList<String>
):Parcelable
