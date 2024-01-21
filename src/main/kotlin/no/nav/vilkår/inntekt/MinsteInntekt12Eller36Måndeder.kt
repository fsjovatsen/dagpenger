package no.nav.vilkår.inntekt

import io.ktor.http.Url

class MinsteInntekt12Eller36Måndeder : InntektsRegel() {
    init {
        paragraf = Url("https://lovdata.no/lov/1997-02-28-19/§4-4")
        innkluderInnteksKlasser = listOf(InntektKlasse.ARBEIDSINNTEKT, InntektKlasse.FANGST_FISKE)
    }
}