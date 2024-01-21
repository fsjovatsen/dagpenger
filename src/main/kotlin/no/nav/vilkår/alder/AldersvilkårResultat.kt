package no.nav.vilkår.alder

import io.ktor.http.Url
import no.nav.vilkår.VilkårsvurderingResultat
import java.time.LocalDate

data class AldersvilkårResultat(
    val fødselsdato: LocalDate,
    val søknadsdato: LocalDate,
    private val resultat: Boolean,
    val paragraf: Url
) : VilkårsvurderingResultat {
    override fun resultat(): Boolean {
        return resultat
    }
}