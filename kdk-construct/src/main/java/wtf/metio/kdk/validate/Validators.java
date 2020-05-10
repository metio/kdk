/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.config.PersistentVolumeClaimVolumeSource;
import wtf.metio.kdk.construct.config.Secret;
import wtf.metio.kdk.construct.config.Volume;
import wtf.metio.kdk.construct.meta.*;
import wtf.metio.kdk.construct.service.*;
import wtf.metio.kdk.construct.workloads.*;
import wtf.metio.kdk.stack.Stack;
import wtf.metio.kdk.validate.config.SecretValidators;
import wtf.metio.kdk.validate.meta.AnnotationValidators;
import wtf.metio.kdk.validate.meta.ObjectMetaValidators;
import wtf.metio.kdk.validate.service.IngressValidators;
import wtf.metio.kdk.validate.service.ServiceValidators;
import wtf.metio.kdk.validate.workloads.DeploymentValidators;
import wtf.metio.kdk.validate.workloads.JobValidators;
import wtf.metio.kdk.validate.workloads.PodSpecValidators;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Configuration object/factory for validators.
 */
@Value.Immutable
public interface Validators {

    //region Builders
    static ImmutableValidators.Builder builder() {
        return ImmutableValidators.builder();
    }

    static ImmutableValidators from(final Validators base) {
        return ImmutableValidators.copyOf(base);
    }

    /**
     * No-op validators.
     */
    static Validators none() {
        return Validators.builder().build();
    }

    /**
     * All validators (excluding opposite-pairs).
     */
    static Validators all() {
        return Validators.builder()
                .addAllObjectMeta(ObjectMetaValidators.all())
                .addAllDeployment(DeploymentValidators.all())
                .addAllService(ServiceValidators.all())
                .addAllIngress(IngressValidators.all())
                .addAllSecret(SecretValidators.all())
                .addAllPodSpec(PodSpecValidators.all())
                .addAllJob(JobValidators.all())
                .addAllAnnotation(AnnotationValidators.all())
                .build();
    }

    /**
     * Recommended set of validators for all resources in every k8s cluster.
     */
    static Validators recommended() {
        return Validators.builder()
                .addAllObjectMeta(ObjectMetaValidators.recommended())
                .addAllDeployment(DeploymentValidators.recommended())
                .addAllService(ServiceValidators.recommended())
                .addAllIngress(IngressValidators.recommended())
                .addAllSecret(SecretValidators.recommended())
                .addAllPodSpec(PodSpecValidators.recommended())
                .addAllJob(JobValidators.recommended())
                .addAllAnnotation(AnnotationValidators.recommended())
                .build();
    }

    /**
     * Validators that ensure that no deprecated field is used in your constructs.
     */
    static Validators deprecated() {
        return Validators.builder()
                .addAllPodSpec(PodSpecValidators.deprecated())
                .build();
    }
    //endregion

    List<Function<Stack, Stream<ValidationError>>> stack();

    List<Function<ObjectMeta, Stream<ValidationError>>> objectMeta();

    List<Function<Deployment, Stream<ValidationError>>> deployment();

    List<Function<DeploymentSpec, Stream<ValidationError>>> deploymentSpec();

    List<Function<Service, Stream<ValidationError>>> service();

    List<Function<ServiceSpec, Stream<ValidationError>>> serviceSpec();

    List<Function<Ingress, Stream<ValidationError>>> ingress();

    List<Function<IngressSpec, Stream<ValidationError>>> ingressSpec();

    List<Function<IngressRule, Stream<ValidationError>>> ingressRule();

    List<Function<HTTPIngressRuleValue, Stream<ValidationError>>> httpIngressRuleValue();

    List<Function<HTTPIngressPath, Stream<ValidationError>>> httpIngressPath();

    List<Function<IngressBackend, Stream<ValidationError>>> ingressBackend();

    List<Function<ServicePort, Stream<ValidationError>>> servicePort();

    List<Function<IngressTLS, Stream<ValidationError>>> ingressTLS();

    List<Function<LabelSelector, Stream<ValidationError>>> labelSelector();

    List<Function<Selector, Stream<ValidationError>>> selector();

    List<Function<DeploymentStrategy, Stream<ValidationError>>> deploymentStrategy();

    List<Function<RollingUpdateDeployment, Stream<ValidationError>>> rollingUpdateDeployment();

    List<Function<PodTemplateSpec, Stream<ValidationError>>> podTemplateSpec();

    List<Function<Annotation, Stream<ValidationError>>> annotation();

    List<Function<PodSpec, Stream<ValidationError>>> podSpec();

    List<Function<Volume, Stream<ValidationError>>> volume();

    List<Function<PersistentVolumeClaimVolumeSource, Stream<ValidationError>>> persistentVolumeClaimVolumeSource();

    List<Function<PodSecurityContext, Stream<ValidationError>>> podSecurityContext();

    List<Function<Container, Stream<ValidationError>>> container();

    List<Function<VolumeMount, Stream<ValidationError>>> volumeMount();

    List<Function<EnvVar, Stream<ValidationError>>> envVar();

    List<Function<EnvVarSource, Stream<ValidationError>>> envVarSource();

    List<Function<SecretKeySelector, Stream<ValidationError>>> secretKeySelector();

    List<Function<ResourceRequirements, Stream<ValidationError>>> resourceRequirements();

    List<Function<ResourceConstraint, Stream<ValidationError>>> resourceConstraint();

    List<Function<Probe, Stream<ValidationError>>> probe();

    List<Function<HTTPGetAction, Stream<ValidationError>>> httpGetAction();

    List<Function<LocalObjectReference, Stream<ValidationError>>> localObjectReference();

    List<Function<Secret, Stream<ValidationError>>> secret();

    List<Function<Job, Stream<ValidationError>>> job();

}
