/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.workloads;

import wtf.metio.kdk.construct.workloads.Deployment;
import wtf.metio.kdk.validate.ValidationError;
import wtf.metio.kdk.validate.ValidationPresetTCK;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class DeploymentValidatorsTest implements ValidationPresetTCK<Deployment> {

    @Override
    public Supplier<List<Function<Deployment, Stream<ValidationError>>>> allPreset() {
        return DeploymentValidators::all;
    }

    @Override
    public Supplier<List<Function<Deployment, Stream<ValidationError>>>> recommendedPreset() {
        return DeploymentValidators::recommended;
    }

}
