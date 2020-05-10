/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.config;

import wtf.metio.kdk.construct.config.Secret;
import wtf.metio.kdk.tests.Secrets;
import wtf.metio.kdk.validate.ValidationError;
import wtf.metio.kdk.validate.ValidatorTCK;

import java.util.function.Function;
import java.util.stream.Stream;

class SecretValidatorsMissingMetaDataTest implements ValidatorTCK<Secret> {

    @Override
    public Function<Secret, Stream<ValidationError>> validator() {
        return SecretValidators.metadataMissing();
    }

    static Stream<Secret> noErrors() {
        return Stream.of(Secrets.testSecretWithMetadata());
    }

    static Stream<Secret> errors() {
        return Stream.of(Secrets.testSecret());
    }

}
