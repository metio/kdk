/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

@Value.Immutable
public interface Selector {

    //region Builders
    static Selector of(final Label label) {
        return ImmutableSelector.of(label.key(), label.value());
    }
    //endregion

    @Value.Parameter
    String key();

    @Value.Parameter
    String value();

}
