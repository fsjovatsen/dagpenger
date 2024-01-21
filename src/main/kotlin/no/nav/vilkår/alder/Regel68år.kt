package no.nav.vilkår.alder

import io.ktor.http.Url
import java.time.LocalDate

class Regel68år : AldersRegel() {

    init {
        alder = 68
        gyldigFra = LocalDate.of(2024, 1, 1)
        paragraf = Url("https://lovdata.no/lov/2023-12-31-00/§4-23")
    }

}