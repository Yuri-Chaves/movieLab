package com.rocket.movielab.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rocket.movielab.R

val RammettoOne = FontFamily(
    Font(R.font.rammetto_one, FontWeight.Normal)
)

val Rajdhani = FontFamily(
    Font(R.font.rajdhani_bold, FontWeight.Bold),
)

val NunitoSans = FontFamily(
    Font(R.font.nunito_sans, FontWeight.Normal),
    Font(R.font.nunito_sans_bold, FontWeight.Bold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = RammettoOne,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 34.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Rajdhani,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 31.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Rajdhani,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 23.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Rajdhani,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.4.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium =  TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall =  TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelMedium =  TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelSmall =  TextStyle(
        fontFamily = NunitoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
)