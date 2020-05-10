/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import wtf.metio.kdk.construct.config.PersistentVolumeClaimVolumeSource;
import wtf.metio.kdk.construct.config.Volume;
import wtf.metio.kdk.construct.meta.*;
import wtf.metio.kdk.construct.service.*;
import wtf.metio.kdk.construct.workloads.*;
import wtf.metio.kdk.stack.Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Stream.of;

final class Validator {

    private final Validators validators;

    Validator(final Validators validators) {
        this.validators = validators;
    }

    List<ValidationError> validate(final Stack stack) {
        return of(check(validators.stack(), stack),
                deployments(stack.deployment()),
                services(stack.service()),
                ingresses(stack.ingress()))
                .flatMap(identity())
                .collect(Collectors.toList());
    }

    private Stream<ValidationError> ingresses(final List<Ingress> ingress) {
        return ingress.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Ingress ingress) {
        return flatten(check(validators.ingress(), ingress),
                objectMeta(ingress.metadata()),
                validate(ingress.spec()));
    }

    private Stream<ValidationError> validate(final IngressSpec spec) {
        return flatten(check(validators.ingressSpec(), spec),
                ingressRules(spec.rules()),
                ingressTLSs(spec.tls()));
    }

    private Stream<ValidationError> ingressRules(final List<IngressRule> rules) {
        return rules.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final IngressRule ingressRule) {
        return flatten(check(validators.ingressRule(), ingressRule),
                validate(ingressRule.http()));
    }

    private Stream<ValidationError> validate(final HTTPIngressRuleValue http) {
        return flatten(check(validators.httpIngressRuleValue(), http), httpIngressPaths(http.paths()));
    }

    private Stream<ValidationError> httpIngressPaths(final List<HTTPIngressPath> paths) {
        return paths.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final HTTPIngressPath httpIngressPath) {
        return flatten(check(validators.httpIngressPath(), httpIngressPath), validate(httpIngressPath.backend()));
    }

    private Stream<ValidationError> validate(final IngressBackend backend) {
        return flatten(check(validators.ingressBackend(), backend), check(validators.servicePort(), backend.servicePort()));
    }

    private Stream<ValidationError> ingressTLSs(final List<IngressTLS> tls) {
        return tls.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final IngressTLS tls) {
        return check(validators.ingressTLS(), tls);
    }

    private Stream<ValidationError> services(final List<Service> service) {
        return service.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Service service) {
        return flatten(check(validators.service(), service),
                objectMeta(service.metadata()),
                validate(service.spec()));
    }

    private Stream<ValidationError> validate(final ServiceSpec spec) {
        return flatten(check(validators.serviceSpec(), spec),
                servicePorts(spec.ports()),
                selectors(spec.selectors()));
    }

    private Stream<ValidationError> servicePorts(final List<ServicePort> servicePorts) {
        return servicePorts.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final ServicePort servicePort) {
        return check(validators.servicePort(), servicePort);
    }

    private Stream<ValidationError> deployments(final List<Deployment> deployments) {
        return deployments.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Deployment deployment) {
        return flatten(check(validators.deployment(), deployment),
                objectMeta(deployment.metadata()),
                validate(deployment.spec()));
    }

    private Stream<ValidationError> validate(final DeploymentSpec spec) {
        return flatten(check(validators.deploymentSpec(), spec),
                validate(spec.selector()),
                deploymentStrategy(spec.strategy()),
                validate(spec.template()));
    }

    private Stream<ValidationError> validate(final LabelSelector selector) {
        return flatten(check(validators.labelSelector(), selector), selectors(selector.labels()));
    }

    private Stream<ValidationError> selectors(final List<Selector> labels) {
        return labels.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Selector selector) {
        return check(validators.selector(), selector);
    }

    private Stream<ValidationError> deploymentStrategy(final Optional<DeploymentStrategy> strategy) {
        return strategy.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final DeploymentStrategy strategy) {
        return flatten(check(validators.deploymentStrategy(), strategy),
                rollingUpdateDeployment(strategy.rollingUpdate()));
    }

    private Stream<ValidationError> rollingUpdateDeployment(final Optional<RollingUpdateDeployment> rollingUpdateDeployment) {
        return rollingUpdateDeployment.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final RollingUpdateDeployment update) {
        return check(validators.rollingUpdateDeployment(), update);
    }

    private Stream<ValidationError> validate(final PodTemplateSpec template) {
        return flatten(check(validators.podTemplateSpec(), template),
                objectMeta(template.metadata()),
                validate(template.spec()));
    }

    private Stream<ValidationError> objectMeta(final Optional<ObjectMeta> metadata) {
        return metadata.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final ObjectMeta meta) {
        return flatten(check(validators.objectMeta(), meta),
                annotations(meta.annotations()));
    }

    private Stream<ValidationError> annotations(final List<Annotation> annotations) {
        return annotations.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Annotation annotations) {
        return check(validators.annotation(), annotations);
    }

    private Stream<ValidationError> validate(final PodSpec spec) {
        return flatten(check(validators.podSpec(), spec),
                containers(spec.containers()),
                localObjectReferences(spec.imagePullSecrets()),
                podSecurityContext(spec.securityContext()),
                volumes(spec.volumes()));
    }

    private Stream<ValidationError> volumes(final List<Volume> volumes) {
        return volumes.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Volume volume) {
        return flatten(check(validators.volume(), volume),
                persistentVolumeClaimVolumeSource(volume.persistentVolumeClaim()));
    }

    private Stream<ValidationError> persistentVolumeClaimVolumeSource(
            final Optional<PersistentVolumeClaimVolumeSource> persistentVolumeClaim) {
        return persistentVolumeClaim.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final PersistentVolumeClaimVolumeSource persistentVolumeClaim) {
        return check(validators.persistentVolumeClaimVolumeSource(), persistentVolumeClaim);
    }

    private Stream<ValidationError> podSecurityContext(final Optional<PodSecurityContext> securityContext) {
        return securityContext.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final PodSecurityContext securityContext) {
        return check(validators.podSecurityContext(), securityContext);
    }

    private Stream<ValidationError> containers(final List<Container> containers) {
        return containers.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Container container) {
        return flatten(check(validators.container(), container),
                probe(container.startupProbe()),
                probe(container.readinessProbe()),
                probe(container.livenessProbe()),
                resourceRequirements(container.resources()),
                envVars(container.env()),
                volumeMounts(container.volumeMounts()));
    }

    private Stream<ValidationError> volumeMounts(final List<VolumeMount> volumeMounts) {
        return volumeMounts.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final VolumeMount volumeMount) {
        return check(validators.volumeMount(), volumeMount);
    }

    private Stream<ValidationError> envVars(final List<EnvVar> env) {
        return env.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final EnvVar envVar) {
        return flatten(check(validators.envVar(), envVar), envVarSource(envVar.valueFrom()));
    }

    private Stream<ValidationError> envVarSource(final Optional<EnvVarSource> envVarSource) {
        return envVarSource.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final EnvVarSource envVarSource) {
        return flatten(check(validators.envVarSource(), envVarSource),
                secretKeySelector(envVarSource.secretKeyRef()));
    }

    private Stream<ValidationError> secretKeySelector(final Optional<SecretKeySelector> secretKeySelector) {
        return secretKeySelector.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final SecretKeySelector secretKeySelector) {
        return check(validators.secretKeySelector(), secretKeySelector);
    }

    private Stream<ValidationError> resourceRequirements(final Optional<ResourceRequirements> resources) {
        return resources.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final ResourceRequirements resourceRequirements) {
        return flatten(check(validators.resourceRequirements(), resourceRequirements),
                resourceConstraint(resourceRequirements.limits()),
                resourceConstraint(resourceRequirements.requests()));
    }

    private Stream<ValidationError> resourceConstraint(final Optional<ResourceConstraint> resourceConstraint) {
        return resourceConstraint.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final ResourceConstraint resourceConstraint) {
        return check(validators.resourceConstraint(), resourceConstraint);
    }

    private Stream<ValidationError> probe(final Optional<Probe> probe) {
        return probe.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final Probe probe) {
        return flatten(check(validators.probe(), probe),
                httpGetAction(probe.httpGet()));
    }

    private Stream<ValidationError> httpGetAction(final Optional<HTTPGetAction> httpGetAction) {
        return httpGetAction.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final HTTPGetAction httpGetAction) {
        return check(validators.httpGetAction(), httpGetAction);
    }

    private Stream<ValidationError> localObjectReferences(final List<LocalObjectReference> references) {
        return references.stream().flatMap(this::validate);
    }

    private Stream<ValidationError> validate(final LocalObjectReference references) {
        return check(validators.localObjectReference(), references);
    }

    @SafeVarargs
    private Stream<ValidationError> flatten(final Stream<ValidationError>... streams) {
        return Arrays.stream(streams).flatMap(identity());
    }

    private <T> Stream<ValidationError> check(
            final List<Function<T, Stream<ValidationError>>> validators,
            final T object) {
        return validators.stream().flatMap(validator -> validator.apply(object));
    }

}
