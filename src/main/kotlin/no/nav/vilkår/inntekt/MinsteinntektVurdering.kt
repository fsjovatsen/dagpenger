package no.nav.vilkår.inntekt

import no.nav.vilkår.Vilkår
import java.math.BigDecimal

class MinsteinntektVurdering : Vilkår {
    private lateinit var inntekt: Inntekt
    private lateinit var grunnbeløpSats: BigDecimal
    fun inntekt(inntekt: Inntekt): MinsteinntektVurdering {
        this.inntekt = inntekt
        return this
    }

    fun grunnbeløpSats(sats: BigDecimal): MinsteinntektVurdering {
        this.grunnbeløpSats = sats
        return this
    }

    override fun vurder(): MinsteinntektResultat {
        val inntektsPerioder = inntekt.splitIntoInntektsPerioder()
        val sumInntektSiste12Måneder = inntektsPerioder.first.sumInntekt(MinsteInntekt12Eller36Måndeder().innkluderInnteksKlasser)
        val sumInntektSiste36Måneder =
            listOf(inntektsPerioder.first, inntektsPerioder.second, inntektsPerioder.third).flatten().sumInntekt(
                listOf(InntektKlasse.ARBEIDSINNTEKT)
            )


        return MinsteinntektResultat(
            inntektSiste12Måneder = sumInntektSiste12Måneder,
            inntektSiste36Måneder = sumInntektSiste36Måneder,
            resultatSiste12Måneder = sumInntektSiste12Måneder > (grunnbeløpSats * BigDecimal(1.5)),
            resultatSiste36Måneder = sumInntektSiste36Måneder > (grunnbeløpSats * BigDecimal(3)),
            paragraf = MinsteInntekt12Eller36Måndeder().paragraf
        )
    }

}
