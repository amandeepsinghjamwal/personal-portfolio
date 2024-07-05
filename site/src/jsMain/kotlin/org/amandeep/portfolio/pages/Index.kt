package org.amandeep.portfolio.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText

import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.amandeep.portfolio.Res
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    val breakpoint = rememberBreakpoint()
    Box(
        Modifier.fillMaxSize().backgroundImage(
            linearGradient(
                dir = LinearGradient.Direction.ToRight,
                from = Res.Theme.GRADIENT_ONE.color,
                to = Res.Theme.GRADIENT_TWO.color
            )
        ), contentAlignment = Alignment.Center
    ) {
        SimpleGrid(
            numColumns = numColumns(1, md = 2),
            modifier = Modifier
                .fillMaxWidth(
                    if (breakpoint <= Breakpoint.MD) 100.percent
                    else Res.Dimens.MAX_CARD_WIDTH.px
                ).thenIf(
                    condition = breakpoint > Breakpoint.MD,
                    other = Modifier.height(Res.Dimens.MAX_CARD_HEIGHT.px)
                )
                .boxShadow(
                    color = Colors.White.copy(alpha = 10),
                    blurRadius = 50.px,
                    spreadRadius = 50.px
                )
                .padding(all = 12.px)
                .borderRadius(r = Res.Dimens.BORDER_RADIUS.px)
                .background(Colors.White)
        ) {
            LeftSide(breakpoint)
            RightSide(breakpoint)
        }
    }
}


@Composable
fun LeftSide(breakpoint: Breakpoint) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(50.px),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = if (breakpoint <= Breakpoint.SM) Alignment.CenterHorizontally else Alignment.Start
    ) {
        SpanText(
            "Contained image",
            modifier = Modifier.fontFamily(Res.String.ROBOTO_CONDENSED).fontSize(50.px).textAlign(
                if (breakpoint <= Breakpoint.SM) TextAlign.Center else TextAlign.Start
            )
        )
    }
}

@Composable
fun RightSide(breakpoint: Breakpoint) {
    Box(
        modifier = Modifier.fillMaxWidth().thenIf(
            condition = breakpoint > Breakpoint.MD,
            other = Modifier.height((Res.Dimens.MAX_CARD_HEIGHT - 24).px)
        )
    ) {
        Image(src = "img.jpg", modifier = Modifier.fillMaxSize().objectFit(ObjectFit.Cover))
    }
}