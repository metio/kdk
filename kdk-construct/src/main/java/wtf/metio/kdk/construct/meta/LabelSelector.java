/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface LabelSelector {

    //region Builders
    static LabelSelector of(final Selector... selectors) {
        return ImmutableLabelSelector.builder().addLabels(selectors).build();
    }

    static LabelSelector of(final String key, final String value) {
        return ImmutableLabelSelector.builder().addLabels(Selector.of(Label.of(key, value))).build();
    }
    
    static LabelSelector of(final Label label) {
        return ImmutableLabelSelector.builder().addLabels(Selector.of(label)).build();
    }
    //endregion

    List<Selector> labels();

}
