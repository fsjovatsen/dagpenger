package no.nav.vilkår.inntekt

import io.ktor.http.Url

abstract class InntektsRegel {
    lateinit var gyldighetsPeriode: Periode
    lateinit var paragraf: Url
    lateinit var innkluderInnteksKlasser : List<InntektKlasse>
}