/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.config.EmptyDirVolumeSource;
import wtf.metio.kdk.construct.config.Volume;
import wtf.metio.kdk.construct.meta.*;
import wtf.metio.kdk.construct.workloads.*;

import static wtf.metio.kdk.construct.config.ConfigMapVolumeSource.configMapVolumeSource;
import static wtf.metio.kdk.construct.config.SecretVolumeSource.secretVolumeSource;
import static wtf.metio.kdk.construct.meta.Selector.of;
import static wtf.metio.kdk.construct.workloads.VolumeMount.volumeMount;
import static wtf.metio.kdk.tests.ObjectMetas.testMetadata;

public final class Deployments {

    public static Deployment testDeployment() {
        return createDeployment(ServicePort.of(8080));
    }

    public static Deployment createDeployment(final ServicePort servicePort) {
        final var metadata = testMetadata();
        final var spec = DeploymentSpecs.createDeploymentSpec(servicePort, metadata);
        return Deployment.of(metadata, spec);
    }

    public static Deployment createConanDeployment() {
        return Deployment.builder()
                .metadata(ObjectMeta.builder()
                        .name("test")
                        .namespace("example")
                        .addLabels(Label.of("app", "conan"))
                        .build())
                .spec(DeploymentSpec.builder()
                        .progressDeadlineSeconds(600)
                        .replicas(1)
                        .revisionHistoryLimit(10)
                        .selector(LabelSelector.of(of(Label.of("app", "conan"))))
                        .strategy(DeploymentStrategy.builder()
                                .type("RollingUpdate")
                                .rollingUpdate(RollingUpdateDeployment.builder()
                                        .maxSurge(1)
                                        .maxUnavailable(0)
                                        .build())
                                .build())
                        .template(PodTemplateSpec.builder()
                                .metadata(ObjectMeta.builder()
                                        .addLabels(Label.of("app", "conan"))
                                        .build())
                                .spec(PodSpec.builder()
                                        .dnsPolicy("ClusterFirst")
                                        .addImagePullSecrets(LocalObjectReference.of("pull-secret"))
                                        .restartPolicy("Always")
                                        .schedulerName("default-scheduler")
                                        .terminationGracePeriodSeconds(30)
                                        .addVolumes(Volume.builder()
                                                .name("config-volume")
                                                .configMap(configMapVolumeSource("springboot-config"))
                                                .build())
                                        .addVolumes(Volume.builder()
                                                .name("tmp-vol")
                                                .emptyDir(EmptyDirVolumeSource.builder().build())
                                                .build())
                                        .addVolumes(Volume.builder()
                                                .name("certs")
                                                .secret(secretVolumeSource("cert-secret"))
                                                .build())
                                        .addContainers(Container.builder()
                                                .addArgs("java", "-Dcom.sun.management.jmxremote.port=2002")
                                                .addCommand("/bin/bash", "-c")
                                                .image("some.image/service:release")
                                                .imagePullPolicy("Always")
                                                .name("test")
                                                .resources(ResourceRequirements.builder()
                                                        .limits(ResourceConstraint.of("1200m", "3000Mi"))
                                                        .requests(ResourceConstraint.of("350m", "2000Mi"))
                                                        .build())
                                                .startupProbe(Probe.builder()
                                                        .failureThreshold(40)
                                                        .periodSeconds(10)
                                                        .httpGet(HTTPGetAction.builder()
                                                                .path("/actuator/health")
                                                                .port(9194)
                                                                .scheme("HTTPS")
                                                                .build())
                                                        .build())
                                                .readinessProbe(Probe.builder()
                                                        .failureThreshold(4)
                                                        .periodSeconds(10)
                                                        .httpGet(HTTPGetAction.builder()
                                                                .path("/actuator/health")
                                                                .port(9194)
                                                                .scheme("HTTPS")
                                                                .build())
                                                        .build())
                                                .livenessProbe(Probe.builder()
                                                        .failureThreshold(4)
                                                        .periodSeconds(10)
                                                        .httpGet(HTTPGetAction.builder()
                                                                .path("/actuator/health")
                                                                .port(9194)
                                                                .scheme("HTTPS")
                                                                .build())
                                                        .build())
                                                .securityContext(SecurityContext.builder()
                                                        .allowPrivilegeEscalation(false)
                                                        .privileged(false)
                                                        .readOnlyRootFilesystem(true)
                                                        .runAsNonRoot(true)
                                                        .runAsUser(1000)
                                                        .build())
                                                .stdin(true)
                                                .terminationMessagePath("/dev/termination-log")
                                                .terminationMessagePolicy("File")
                                                .tty(true)
                                                .addVolumeMounts(volumeMount("tmp-vol", "/tmp"))
                                                .addVolumeMounts(volumeMount("config-volume", "/config"))
                                                .addVolumeMounts(VolumeMount.builder()
                                                        .mountPath("/usr/share/local/certs/")
                                                        .name("certs")
                                                        .readOnly(true)
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private Deployments() {
        // factory class
    }

}
