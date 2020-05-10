/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.workloads;

import wtf.metio.kdk.construct.workloads.Deployment;
import wtf.metio.kdk.validate.ValidationError;
import wtf.metio.kdk.validate.internal.HasMetaDataValidators;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Validators for {@link Deployment}s.
 */
public final class DeploymentValidators {

    //region presets
    public static List<Function<Deployment, Stream<ValidationError>>> all() {
        return List.of(metadataMissing());
    }

    public static List<Function<Deployment, Stream<ValidationError>>> recommended() {
        return List.of(metadataMissing());
    }
    //endregion

    public static Function<Deployment, Stream<ValidationError>> metadataMissing() {
        return deployment -> HasMetaDataValidators.metadataMissing(deployment, "deployment");
    }

    private DeploymentValidators() {
        // factory class
    }

}
