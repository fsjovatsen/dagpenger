package no.nav.vilkår.alder

import io.ktor.http.Url
import java.time.LocalDate
import kotlin.properties.Delegates

abstract class AldersRegel {

    internal lateinit var gyldigFra: LocalDate
    internal var gyldigTil: LocalDate? = null
    internal var alder by Delegates.notNull<Int>()
    lateinit var paragraf: Url

    fun antallMåneder() : Int = alder * 12
    fun gyldig(søknadsdato: LocalDate) : Boolean {
        if (gyldigTil == null && søknadsdato.isAfter(gyldigFra.minusDays(1))) {
            return true
        }

        return søknadsdato.isAfter(gyldigFra.minusDays(1)) and søknadsdato.isBefore(gyldigTil!!.plusDays(1))
    }
}