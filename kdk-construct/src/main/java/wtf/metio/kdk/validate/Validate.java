/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.construct.service.Service;
import wtf.metio.kdk.construct.workloads.Deployment;
import wtf.metio.kdk.stack.Stack;

import java.util.List;

public final class Validate {

    public static List<ValidationError> validate(final Deployment... deployments) {
        return validate(Stack.builder().addDeployment(deployments).build());
    }

    public static List<ValidationError> validate(final Service... services) {
        return validate(Stack.builder().addService(services).build());
    }

    public static List<ValidationError> validate(final Ingress... ingresses) {
        return validate(Stack.builder().addIngress(ingresses).build());
    }

    public static List<ValidationError> validate(final Stack stack) {
        return validate(stack, Validators.recommended());
    }

    public static List<ValidationError> validate(
            final Stack stack,
            final Validators validators) {
        return new Validator(validators).validate(stack);
    }

    private Validate() {
        // factory class
    }

}
