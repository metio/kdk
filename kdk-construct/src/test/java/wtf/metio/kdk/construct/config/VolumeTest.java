/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import wtf.metio.kdk.construct.ConstructTCK;
import wtf.metio.kdk.construct.ConstructWithBuilderTCK;

import java.util.stream.Stream;

class VolumeTest implements ConstructTCK<Volume>, ConstructWithBuilderTCK<ImmutableVolume.Builder> {

    static Stream<Volume> types() {
        return Stream.of(
                Volume.builder().name("test").build()
        );
    }

    @Override
    public ImmutableVolume.Builder builder() {
        return Volume.builder();
    }

}
