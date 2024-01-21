package no.nav

import io.kotest.matchers.shouldBe
import no.nav.vilkår.inntekt.MinsteinntektVurdering
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.YearMonth
import no.nav.vilkår.inntekt.KlassifisertInntektMåned
import no.nav.vilkår.inntekt.KlassifisertInntekt
import no.nav.vilkår.inntekt.InntektKlasse
import no.nav.vilkår.inntekt.Inntekt

class InntektTest {

    private val sisteAvsluttendeKalenderMåned = YearMonth.of(2019, 3)

    private val testInntektsListe =
        (0..53).toList().map {
            KlassifisertInntektMåned(
                sisteAvsluttendeKalenderMåned.minusMonths(it.toLong()),
                listOf(
                    KlassifisertInntekt(
                        BigDecimal(14000),
                        InntektKlasse.ARBEIDSINNTEKT,
                    ),
                    KlassifisertInntekt(
                        BigDecimal(2000),
                        InntektKlasse.DAGPENGER_FANGST_FISKE,
                    ),
                ),
            )
        }

    private val testInntekt = Inntekt("id", testInntektsListe, sisteAvsluttendeKalenderMåned = sisteAvsluttendeKalenderMåned)

    @Test
    fun `Inntekten siste tolv avsluttede kalendermånedene er over 1,5G`() {
        val vurder = MinsteinntektVurdering()
            .inntekt(testInntekt)
            .grunnbeløpSats(BigDecimal(110000))
            .vurder()

        vurder.resultatSiste12Måneder shouldBe true
    }

    @Test
    fun `Inntekten siste 36 avsluttede kalendermånedene er over 3G`() {
        val vurder = MinsteinntektVurdering()
            .inntekt(testInntekt)
            .grunnbeløpSats(BigDecimal(110000))
            .vurder()

        vurder.resultatSiste36Måneder shouldBe true
    }
}