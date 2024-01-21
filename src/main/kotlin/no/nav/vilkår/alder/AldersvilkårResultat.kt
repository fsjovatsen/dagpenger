package no.nav.vilkår.alder

import io.ktor.http.Url
import java.time.LocalDate

data class AldersvilkårResultat(
    val fødselsdato: LocalDate,
    val søknadsdato: LocalDate,
    val resultat: Boolean,
    val paragraf: Url
)