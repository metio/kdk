/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.meta;

import wtf.metio.kdk.construct.meta.Annotation;
import wtf.metio.kdk.validate.ValidationError;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static wtf.metio.kdk.validate.ValidationError.of;
import static wtf.metio.kdk.validate.internal.StringValidators.blank;

/**
 * Validators for {@link Annotation}s.
 */
public final class AnnotationValidators {

    //region presets
    public static List<Function<Annotation, Stream<ValidationError>>> all() {
        return List.of(keyBlank(), valueBlank());
    }

    public static List<Function<Annotation, Stream<ValidationError>>> recommended() {
        return List.of(keyBlank(), valueBlank());
    }
    //endregion

    /**
     * Checks that no annotation key is blank
     */
    public static Function<Annotation, Stream<ValidationError>> keyBlank() {
        return annotation -> Stream.of(annotation)
                .filter(blank(Annotation::key))
                .map(object -> of("annotation:key-blank", object));
    }

    /**
     * Checks that no annotation value is blank
     */
    public static Function<Annotation, Stream<ValidationError>> valueBlank() {
        return annotation -> Stream.of(annotation)
                .filter(blank(Annotation::value))
                .map(object -> of("annotation:value-blank", object));
    }

    private AnnotationValidators() {
        // factory class
    }

}
