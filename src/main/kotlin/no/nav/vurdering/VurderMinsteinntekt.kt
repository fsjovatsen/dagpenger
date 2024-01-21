package no.nav.vurdering

import no.nav.vilkår.Vilkår

class VurderMinsteinntekt(private val vilkår: List<Vilkår>) {

    fun vurder(): Boolean {
        return vilkår.map { it.vurder() }
            .map { it.resultat() }
            .all { it }
    }


}