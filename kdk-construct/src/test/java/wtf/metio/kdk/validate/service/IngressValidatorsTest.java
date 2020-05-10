/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate.service;

import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.validate.ValidationError;
import wtf.metio.kdk.validate.ValidationPresetTCK;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class IngressValidatorsTest implements ValidationPresetTCK<Ingress> {

    @Override
    public Supplier<List<Function<Ingress, Stream<ValidationError>>>> allPreset() {
        return IngressValidators::all;
    }

    @Override
    public Supplier<List<Function<Ingress, Stream<ValidationError>>>> recommendedPreset() {
        return IngressValidators::recommended;
    }

}
