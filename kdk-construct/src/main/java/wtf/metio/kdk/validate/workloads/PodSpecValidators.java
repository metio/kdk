/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.workloads;

import wtf.metio.kdk.construct.meta.Annotation;
import wtf.metio.kdk.construct.workloads.PodSpec;
import wtf.metio.kdk.validate.ValidationError;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static wtf.metio.kdk.validate.ValidationError.of;

/**
 * Validators for {@link PodSpec}s.
 */
public class PodSpecValidators {

    //region presets
    public static List<Function<PodSpec, Stream<ValidationError>>> all() {
        return List.of(serviceAccountUsed(),
                activeDeadlineSecondsIsNegative(),
                serviceAccountNameMaxLength(100),
                containersNotSet());
    }

    public static List<Function<PodSpec, Stream<ValidationError>>> recommended() {
        return List.of(serviceAccountUsed(),
                activeDeadlineSecondsIsNegative(),
                serviceAccountNameMinLength(1),
                containersNotSet());
    }

    public static List<Function<PodSpec, Stream<ValidationError>>> deprecated() {
        return List.of(serviceAccountUsed());
    }
    //endregion

    public static Function<PodSpec, Stream<ValidationError>> serviceAccountUsed() {
        return podSpec -> Stream.of(podSpec)
                .filter(object -> object.serviceAccount().isPresent())
                .map(object -> of("podSpec:serviceAccount-used", object));
    }

    public static Function<PodSpec, Stream<ValidationError>> serviceAccountNameMinLength(final int minLength) {
        return podSpec -> Stream.of(podSpec)
                .filter(object -> object.serviceAccountName().map(value -> value.length() < minLength).orElse(false))
                .map(object -> of("podSpec:serviceAccountName-min-length", object));
    }

    public static Function<PodSpec, Stream<ValidationError>> serviceAccountNameMaxLength(final int maxLength) {
        return podSpec -> Stream.of(podSpec)
                .filter(object -> object.serviceAccountName().map(value -> value.length() > maxLength).orElse(false))
                .map(object -> of("podSpec:serviceAccountName-max-length", object));
    }

    public static Function<PodSpec, Stream<ValidationError>> activeDeadlineSecondsIsNegative() {
        return podSpec -> Stream.of(podSpec)
                .filter(object -> object.activeDeadlineSeconds().isPresent())
                .filter(object -> object.activeDeadlineSeconds().map(value -> value <= 0).orElse(false))
                .map(object -> of("podSpec:activeDeadlineSeconds-negative", object));
    }

    public static Function<PodSpec, Stream<ValidationError>> containersNotSet() {
        return podSpec -> Stream.of(podSpec)
                .filter(object -> object.containers().isEmpty())
                .map(object -> of("podSpec:containers-not-set", object));
    }

    private PodSpecValidators() {
        // factory classs
    }

}
