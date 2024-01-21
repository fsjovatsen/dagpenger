package no.nav.vilkår.inntekt

import io.ktor.http.Url
import java.math.BigDecimal

class MinsteinntektResultat(
    val inntektSiste12Måneder: BigDecimal,
    val resultatSiste12Måneder: Boolean,
    val inntektSiste36Måneder: BigDecimal,
    val resultatSiste36Måneder: Boolean,
    val paragraf: Url

) {

}