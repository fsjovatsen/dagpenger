package no.nav

import io.kotest.matchers.shouldBe
import no.nav.vilkår.alder.AldersvilkårsVurdering
import org.junit.jupiter.api.Test
import java.time.LocalDate

class AlderTest {
    @Test
    fun `Hvis søkeren er under 67 år og søknaden sendes inn før 31-12-2023 innvilges vilkåret`() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2023, 7, 7).minusYears(37))
            .søknadsdato(LocalDate.of(2023, 7, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe true
    }

    @Test
    fun `Hvis søkeren er over 67 år og søknaden sendes inn før 31-12-2023 avslås vilkåret`() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2023, 1, 7).minusYears(68))
            .søknadsdato(LocalDate.of(2023, 7, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe false
    }

    @Test
    fun `Hvis søkeren blir 67 år i den måneden søknaden sendes og søknaden sendes inn før 31-12-2023 inn innvilges vilkåret`() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2023, 7, 7).minusYears(67))
            .søknadsdato(LocalDate.of(2023, 7, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe true
    }

    @Test
    fun `Hvis søkeren blir 67 år i måneden før søknaden sendes og søknaden sendes inn før 31-12-2023 inn avslås vilkåret`() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2023, 7, 7).minusYears(67))
            .søknadsdato(LocalDate.of(2023, 8, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe false
    }

    @Test
    fun `Hvis søkeren er under 68 år og søknaden sendes inn etter 31-12-2023 innvilges vilkåret`() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2024, 1, 7).minusYears(68))
            .søknadsdato(LocalDate.of(2024, 1, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe true
    }

    @Test
    fun `Hvis søkeren er over 68 år og søknaden sendes inn etter 31-12-2023 avslås vilkåret `() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2024, 1, 1).minusYears(69))
            .søknadsdato(LocalDate.of(2024, 7, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe false
    }

    @Test
    fun `Hvis søkeren blir 68 år i den måneden søknaden sendes og søknaden sendes inn etter 31-12-2023 inn innvilges vilkåret`() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2024, 7, 7).minusYears(68))
            .søknadsdato(LocalDate.of(2024, 7, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe true
    }

    @Test
    fun `Hvis søkeren blir 68 år i måneden før søknaden sendes og søknaden sendes inn etter 31-12-2023 inn avslås vilkåret`() {
        val aldersvilkårsvurdering = AldersvilkårsVurdering()
            .fødselsdato(LocalDate.of(2024, 7, 7).minusYears(68))
            .søknadsdato(LocalDate.of(2024, 8, 7))
            .vurder()

        aldersvilkårsvurdering.resultat shouldBe false
    }
}