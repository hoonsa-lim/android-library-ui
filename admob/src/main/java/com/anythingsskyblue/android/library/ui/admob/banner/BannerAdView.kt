package com.anythingsskyblue.android.library.ui.admob.banner

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

@Composable
fun BannerAdView(
    modifier: Modifier = Modifier,
    unitIds: List<String>,
    size: BannerAdSize = BannerAdSize.Large,
    dividerColor: Color? = null,
    viewModel: BannerAdViewModel = remember { BannerAdViewModel(unitIds, size) }
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        dividerColor?.let {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 16.dp,
                color = it,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AndroidView(
                modifier = modifier,
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(uiState.adSize)
                        adUnitId = unitIds[uiState.currentAdUnitIdIndex]
                        adListener = viewModel.adListener
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    }
}