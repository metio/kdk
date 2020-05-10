/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.meta;

import wtf.metio.kdk.construct.meta.Annotation;
import wtf.metio.kdk.validate.ValidationError;
import wtf.metio.kdk.validate.ValidatorTCK;

import java.util.function.Function;
import java.util.stream.Stream;

class AnnotationValidatorsValueBlankTest implements ValidatorTCK<Annotation> {

    @Override
    public Function<Annotation, Stream<ValidationError>> validator() {
        return AnnotationValidators.valueBlank();
    }

    static Stream<Annotation> noErrors() {
        return Stream.of(Annotation.of("key", "value"));
    }

    static Stream<Annotation> errors() {
        return Stream.of(
                Annotation.of("key", ""),
                Annotation.of("key", " "),
                Annotation.of("key", "  ")
        );
    }

}
