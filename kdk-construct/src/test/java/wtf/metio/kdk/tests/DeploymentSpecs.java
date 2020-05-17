/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.meta.LabelSelector;
import wtf.metio.kdk.construct.meta.LocalObjectReference;
import wtf.metio.kdk.construct.meta.ObjectMeta;
import wtf.metio.kdk.construct.workloads.*;

import static wtf.metio.kdk.construct.meta.Label.k8sInstance;
import static wtf.metio.kdk.construct.meta.Label.k8sName;
import static wtf.metio.kdk.construct.meta.Selector.of;
import static wtf.metio.kdk.tests.Containers.createContainer;
import static wtf.metio.kdk.tests.ObjectMetas.testMetadata;

public class DeploymentSpecs {

    public static ImmutableDeploymentSpec testDeploymentSpec() {
        final var metadata = testMetadata();
        final var port = ServicePort.of(8080);
        return createDeploymentSpec(port, metadata);
    }

    public static ImmutableDeploymentSpec createDeploymentSpec(
            final ServicePort servicePort,
            final ObjectMeta metadata) {
        final var container = createContainer(servicePort);
        final var pullSecret = LocalObjectReference.of("registry-pull-secret");
        final var podSpec = PodSpec.builder()
                .addContainers(container)
                .addImagePullSecrets(pullSecret)
                .restartPolicy("Always")
                .build();
        final var template = PodTemplateSpec.of(metadata, podSpec);
        final var labelSelector = LabelSelector.of(
                of(k8sName("test")),
                of(k8sInstance("eu-west-17")));
        return DeploymentSpec.builder()
                .replicas(3)
                .selector(labelSelector)
                .template(template)
                .build();
    }

    private DeploymentSpecs() {
        // factory class
    }

}
