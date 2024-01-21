package no.nav.vurdering

import io.kotest.matchers.shouldBe
import no.nav.vilkår.alder.AldersvilkårsVurdering
import no.nav.vilkår.inntekt.Inntekt
import no.nav.vilkår.inntekt.InntektKlasse
import no.nav.vilkår.inntekt.KlassifisertInntekt
import no.nav.vilkår.inntekt.KlassifisertInntektMåned
import no.nav.vilkår.inntekt.MinsteinntektVurdering
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate
import java.time.YearMonth

class VurderMinsteinntektTest {

    private val aldersvilkårsVurdering = AldersvilkårsVurdering()
        .fødselsdato(LocalDate.of(2023, 7, 7).minusYears(37))
        .søknadsdato(LocalDate.of(2023, 7, 7))


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


    private val minsteinntektVurdering = MinsteinntektVurdering()
        .inntekt(Inntekt("id", testInntektsListe, sisteAvsluttendeKalenderMåned = sisteAvsluttendeKalenderMåned))
        .grunnbeløpSats(BigDecimal(140000))

    @Test
    fun `Søknaden avslås fordi alle vilkårene ikke innvilges`() {
        val vurder = VurderMinsteinntekt(listOf(aldersvilkårsVurdering, minsteinntektVurdering)).vurder()

        vurder shouldBe false
    }
}