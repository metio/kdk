/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.stack;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.internal.HasMetaData;
import wtf.metio.kdk.construct.meta.*;
import wtf.metio.kdk.construct.workloads.*;

import java.util.List;
import java.util.Optional;

import static wtf.metio.kdk.construct.workloads.EnvVar.of;

/**
 * Configuration for a "default" deployment which consists of:
 * <ul>
 *     <li></li>
 * </ul>
 */
@Value.Immutable
public interface DefaultDeployment extends HasMetaData {

    //region Builders
    static ImmutableDefaultDeployment.Builder builder() {
        return ImmutableDefaultDeployment.builder();
    }
    //endregion

    Probe readinessProbe();

    Probe livenessProbe();

    Probe startupProbe();

    String image();

    Optional<String> imagePullSecret();

    String name();

    int replicas();

    List<Annotation> annotations();

    List<Label> labels();

    LabelSelector selector();

    List<EnvVar> env();

    ResourceConstraint resources();

    List<VolumeMount> volumeMounts();

    default Deployment asDeployment() {
        final var metadata = ObjectMeta.builder()
                .addAllAnnotations(annotations())
                .addAllLabels(labels())
                .name(name())
                .build();
        final var container = Container.builder()
                .image(image())
                .imagePullPolicy("Always")
                .name(name())
                .startupProbe(startupProbe())
                .readinessProbe(readinessProbe())
                .livenessProbe(livenessProbe())
                .resources(ResourceRequirements.of(resources(), resources()))
                .addAllEnv(env())
                .addAllVolumeMounts(volumeMounts())
                .securityContext(SecurityContext.builder()
                        .allowPrivilegeEscalation(false)
                        .privileged(false)
                        .readOnlyRootFilesystem(true)
                        .runAsNonRoot(true)
                        .runAsUser(10000)
                        .build())
                .build();
        final var podSpecBuilder = PodSpec.builder()
                .addContainers(container)
                .restartPolicy("Always");
        imagePullSecret().map(LocalObjectReference::of).ifPresent(podSpecBuilder::addImagePullSecrets);
        final var spec = DeploymentSpec.builder()
                .replicas(replicas())
                .selector(selector())
                .strategy(DeploymentStrategy.builder()
                        .type("RollingUpdate")
                        .rollingUpdate(RollingUpdateDeployment.builder()
                                .maxSurge(1)
                                .maxUnavailable(0)
                                .build())
                        .build())
                .template(PodTemplateSpec.of(metadata, podSpecBuilder.build()))
                .build();
        return Deployment.of(metadata, spec);
    }

}
