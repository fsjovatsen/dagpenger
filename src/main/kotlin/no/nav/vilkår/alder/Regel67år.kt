package no.nav.vilkår.alder

import io.ktor.http.Url
import java.time.LocalDate

class Regel67år: AldersRegel() {

    init {
        alder = 67
        gyldigFra = LocalDate.of(1998, 6, 18)
        gyldigTil = LocalDate.of(2023, 12, 31)
        paragraf = Url("https://lovdata.no/lov/1997-02-28-19/§4-23")
    }
}