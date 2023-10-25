package com.anythingsskyblue.android.library.ui.compose

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

interface PreventContinuousClick {
    var debounceMilliseconds: Long

    fun preventOrClick(event: () -> Unit)

    companion object
}

fun PreventContinuousClick.Companion.get(
    debounce: Duration = 500.milliseconds
): PreventContinuousClick = PreventContinuousClickImpl(debounce.toLong(DurationUnit.MILLISECONDS))

private class PreventContinuousClickImpl(
    override var debounceMilliseconds: Long
) : PreventContinuousClick {

    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun preventOrClick(event: () -> Unit) {
        if (now - lastEventTimeMs >= debounceMilliseconds) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}

fun Modifier.clickableNotContinuous(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    debounce: Duration = 500.milliseconds,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val preventContinuousClick = remember { PreventContinuousClick.get(debounce) }
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { preventContinuousClick.preventOrClick(onClick) },
        role = role,
        indication = LocalIndication.current,
        interactionSource = remember { MutableInteractionSource() }
    )
}