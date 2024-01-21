package no.nav.vilkår.alder

import no.nav.vilkår.Vilkår
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class AldersvilkårsVurdering : Vilkår{

    private lateinit var fødselsdato: LocalDate
    private lateinit var søknadsdato: LocalDate

    fun fødselsdato(fødselsdato: LocalDate): AldersvilkårsVurdering {
        this.fødselsdato = fødselsdato
        return this
    }

    fun søknadsdato(søknadsdato: LocalDate): AldersvilkårsVurdering {
        this.søknadsdato = søknadsdato
        return this
    }


    override fun vurder(): AldersvilkårResultat {
            val between = ChronoUnit.MONTHS.between(fødselsdato, søknadsdato)
            val gjeldendeRegel = finnGjeldendeRegel(søknadsdato)

            return AldersvilkårResultat(
                fødselsdato,
                søknadsdato,
                between <= gjeldendeRegel.antallMåneder(),
                gjeldendeRegel.paragraf
            )

    }

    private fun finnGjeldendeRegel(søknadsdato: LocalDate): AldersRegel {

        return when {
            Regel67år().gyldig(søknadsdato) -> Regel67år()
            Regel68år().gyldig(søknadsdato) -> Regel68år()
            else -> throw IllegalArgumentException("Vi finner ingen aldersregler for søknadstidspunktet")
        }

    }


}