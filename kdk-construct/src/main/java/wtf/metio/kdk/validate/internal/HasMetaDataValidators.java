/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.internal;

import wtf.metio.kdk.construct.internal.HasMetaData;
import wtf.metio.kdk.construct.meta.Annotation;
import wtf.metio.kdk.validate.ValidationError;

import java.util.stream.Stream;

import static wtf.metio.kdk.validate.ValidationError.of;

/**
 * Validators for object that implement {@link HasMetaData}.
 */
public final class HasMetaDataValidators {

    public static Stream<ValidationError> metadataMissing(final HasMetaData hasMetaData, final String resource) {
        return Stream.of(hasMetaData)
                .filter(object -> object.metadata().isEmpty())
                .map(object -> of(resource + ":missing-metadata", object));
    }

    private HasMetaDataValidators() {
        // factory class
    }

}
