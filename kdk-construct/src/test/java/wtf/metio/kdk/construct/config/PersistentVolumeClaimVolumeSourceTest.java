/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.junit.jupiter.api.Test;
import wtf.metio.kdk.construct.ConstructTCK;
import wtf.metio.kdk.construct.ConstructWithBuilderTCK;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersistentVolumeClaimVolumeSourceTest implements
        ConstructTCK<PersistentVolumeClaimVolumeSource>,
        ConstructWithBuilderTCK<ImmutablePersistentVolumeClaimVolumeSource.Builder> {

    static Stream<PersistentVolumeClaimVolumeSource> types() {
        return Stream.of(
                PersistentVolumeClaimVolumeSource.of("test"),
                PersistentVolumeClaimVolumeSource.builder().claimName("test").build(),
                PersistentVolumeClaimVolumeSource.builder().claimName("test").readOnly(true).build()
        );
    }

    @Override
    public ImmutablePersistentVolumeClaimVolumeSource.Builder builder() {
        return PersistentVolumeClaimVolumeSource.builder();
    }

    @Test
    void shouldSupportFactoryMethod() {
        // given
        final var claimName = "test";

        // when
        final var service = PersistentVolumeClaimVolumeSource.of(claimName);

        // then
        assertNotNull(service);
    }

}
