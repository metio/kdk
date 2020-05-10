/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import wtf.metio.kdk.construct.ConstructTCK;

import java.util.stream.Stream;

class ServiceTest implements ConstructTCK<Service> {

    static Stream<Service> types() {
        return Stream.of(Service.builder()
                .spec(ServiceSpec.builder().build())
                .build());
    }

}
