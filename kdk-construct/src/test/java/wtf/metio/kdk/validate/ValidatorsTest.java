/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ValidatorsTest {

    @Test
    void shouldDefineNoOpValidators() {
        assertNotNull(Validators.none());
    }

    @Test
    void shouldDefineAllValidators() {
        assertNotNull(Validators.all());
    }

    @Test
    void shouldDefineRecommendedValidators() {
        assertNotNull(Validators.recommended());
    }

    @Test
    void shouldDefineDeprecatedValidators() {
        assertNotNull(Validators.deprecated());
    }

    @Test
    void shouldDefineStackValidators() {
        assertNotNull(Validators.builder()
                .addStack(stack -> Stream.of())
                .build());
    }

}
