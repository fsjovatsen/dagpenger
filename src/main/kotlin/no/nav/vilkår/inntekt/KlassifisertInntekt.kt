package no.nav.vilkår.inntekt

import java.math.BigDecimal

data class KlassifisertInntekt(
    val beløp: BigDecimal,
    val inntektKlasse: InntektKlasse,
)

enum class InntektKlasse {
    ARBEIDSINNTEKT,
    DAGPENGER,
    DAGPENGER_FANGST_FISKE,
    SYKEPENGER_FANGST_FISKE,
    FANGST_FISKE,
    SYKEPENGER,
    TILTAKSLØNN,
    PLEIEPENGER,
    OMSORGSPENGER,
    OPPLÆRINGSPENGER,
}
