/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.meta;

import wtf.metio.kdk.construct.meta.ObjectMeta;
import wtf.metio.kdk.validate.ValidationError;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static wtf.metio.kdk.validate.ValidationError.of;

/**
 * Validators for {@link ObjectMeta}s.
 */
public final class ObjectMetaValidators {

    //region presets
    public static List<Function<ObjectMeta, Stream<ValidationError>>> all() {
        return List.of(nameMissing(), namespaceMissing());
    }

    public static List<Function<ObjectMeta, Stream<ValidationError>>> recommended() {
        return List.of(nameMissing());
    }
    //endregion

    /**
     * Checks that any {@link ObjectMeta} instance contains a name value.
     */
    public static Function<ObjectMeta, Stream<ValidationError>> nameMissing() {
        return resource -> Stream.of(resource)
                .filter(metaData -> metaData.name().isEmpty())
                .map(metaData -> of("objectMeta:name-missing", metaData));
    }

    /**
     * Checks that any {@link ObjectMeta} instance contains a namespace value.
     */
    public static Function<ObjectMeta, Stream<ValidationError>> namespaceMissing() {
        return resource -> Stream.of(resource)
                .filter(metaData -> metaData.namespace().isEmpty())
                .map(metaData -> of("objectMeta:namespace-missing", metaData));
    }
    //endregion

    private ObjectMetaValidators() {
        // factory class
    }

}
