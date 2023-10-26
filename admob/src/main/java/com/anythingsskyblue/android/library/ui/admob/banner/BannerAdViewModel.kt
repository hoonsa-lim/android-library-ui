package com.anythingsskyblue.android.library.ui.admob.banner

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.LoadAdError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BannerAdViewModel(
    private val unitIds: List<String>,
    private val bannerAdSize: BannerAdSize,
){
    private val _uiState = MutableStateFlow(initState())
    val uiState = _uiState.asStateFlow()

    val adListener = object: AdListener() {
        override fun onAdFailedToLoad(adError : LoadAdError) {
            when(adError.code){
                3 -> {
                    val nextIndex = _uiState.value.currentAdUnitIdIndex + 1
                    val lastIndex = unitIds.size-1

                    if (lastIndex <= nextIndex){
                        _uiState.update { it.copy(currentAdUnitIdIndex = nextIndex) }
                    }
                }
                else -> {}
            }
        }
        override fun onAdClicked() {}
        override fun onAdClosed() {}
        override fun onAdImpression() {}
        override fun onAdLoaded() {}
        override fun onAdOpened() {}
    }

    private fun initState(): BannerAdViewState {
        return BannerAdViewState(
            currentAdUnitIdIndex = 0,
            adSize = when(bannerAdSize){
                BannerAdSize.Large -> AdSize.LARGE_BANNER
                BannerAdSize.MediumRectangle -> AdSize.MEDIUM_RECTANGLE
            },
        )
    }
}