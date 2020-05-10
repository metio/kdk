/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.yaml.snakeyaml.Yaml;
import wtf.metio.kdk.construct.config.*;
import wtf.metio.kdk.construct.meta.*;
import wtf.metio.kdk.construct.service.*;
import wtf.metio.kdk.construct.workloads.*;
import wtf.metio.kdk.stack.Stack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static wtf.metio.kdk.serialize.ApiVersionMapping.version;

final class YamlSerializer {

    private final Yaml yaml;
    private final K8sVersions apiVersion;

    YamlSerializer(final Yaml yaml, final K8sVersions apiVersion) {
        this.yaml = yaml;
        this.apiVersion = apiVersion;
    }

    public String asYaml(final Stack stack) {
        return Stream.of(
                deployment(stack.deployment().stream()),
                service(stack.service().stream()),
                ingress(stack.ingress().stream()),
                secret(stack.secrets().stream()))
                .flatMap(Function.identity())
                .collect(Collectors.joining());
    }

    private Stream<String> deployment(final Stream<Deployment> deployments) {
        return deployments.map(this::asYaml);
    }

    private Stream<String> service(final Stream<Service> services) {
        return services.map(this::asYaml);
    }

    private Stream<String> ingress(final Stream<Ingress> ingress) {
        return ingress.map(this::asYaml);
    }

    private Stream<String> secret(final Stream<Secret> secrets) {
        return secrets.map(this::asYaml);
    }

    private String asYaml(final Secret secret) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("apiVersion", version(Secret.class, apiVersion));
        data.put("kind", "Secret");
        secret.metadata().ifPresent(metadata -> data.put("metadata", objectMeta(metadata)));
        if (!secret.data().isEmpty()) {
            data.put("data", secret.data());
        }
        if (!secret.stringData().isEmpty()) {
            data.put("data", secret.stringData());
        }
        data.put("type", secret.type());
        return yaml.dump(data);
    }

    private String asYaml(final Deployment deployment) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("apiVersion", version(Deployment.class, apiVersion));
        data.put("kind", "Deployment");
        deployment.metadata().ifPresent(metadata -> data.put("metadata", objectMeta(metadata)));
        data.put("spec", deploymentSpec(deployment.spec()));
        return yaml.dump(data);
    }

    private Map<String, Object> deploymentSpec(final DeploymentSpec spec) {
        final var data = new LinkedHashMap<String, Object>();
        spec.minReadySeconds().ifPresent(minReadySeconds -> data.put("minReadySeconds", minReadySeconds));
        spec.paused().ifPresent(paused -> data.put("paused", paused));
        spec.progressDeadlineSeconds().ifPresent(progressDeadlineSeconds ->
                data.put("progressDeadlineSeconds", progressDeadlineSeconds));
        spec.replicas().ifPresent(replicas -> data.put("replicas", replicas));
        spec.revisionHistoryLimit().ifPresent(revisionHistoryLimit ->
                data.put("revisionHistoryLimit", revisionHistoryLimit));
        data.put("selector", labelSelector(spec.selector()));
        spec.strategy().ifPresent(strategy -> data.put("strategy", strategy(strategy)));
        data.put("template", template(spec.template()));
        return data;
    }

    private Map<String, Object> strategy(final DeploymentStrategy strategy) {
        final var data = new LinkedHashMap<String, Object>();
        strategy.type().ifPresent(type -> data.put("type", type));
        strategy.rollingUpdate().ifPresent(rollingUpdate -> data.put("rollingUpdate", rollingUpdateDeployment(rollingUpdate)));
        return data;
    }

    private Map<String, Object> rollingUpdateDeployment(final RollingUpdateDeployment rollingUpdate) {
        final var data = new LinkedHashMap<String, Object>();
        rollingUpdate.maxSurge().ifPresent(maxSurge -> data.put("maxSurge", maxSurge));
        rollingUpdate.maxSurgePercent().ifPresent(maxSurgePercent -> data.put("maxSurge", maxSurgePercent));
        rollingUpdate.maxUnavailable().ifPresent(maxUnavailable -> data.put("maxUnavailable", maxUnavailable));
        rollingUpdate.maxUnavailablePercent().ifPresent(maxUnavailablePercent ->
                data.put("maxUnavailable", maxUnavailablePercent));
        return data;
    }

    private Map<String, Object> template(final PodTemplateSpec template) {
        final var data = new LinkedHashMap<String, Object>();
        template.metadata().ifPresent(metaData -> data.put("metadata", objectMeta(metaData)));
        data.put("spec", podSpec(template.spec()));
        return data;
    }

    private Map<String, Object> podSpec(final PodSpec spec) {
        final var data = new LinkedHashMap<String, Object>();
        spec.activeDeadlineSeconds().ifPresent(seconds -> data.put("activeDeadlineSeconds", seconds));
        spec.automountServiceAccountToken().ifPresent(token -> data.put("automountServiceAccountToken", token));
        if (!spec.containers().isEmpty()) {
            data.put("containers", containers(spec.containers()));
        }
        spec.dnsPolicy().ifPresent(dnsPolicy -> data.put("dnsPolicy", dnsPolicy));
        spec.enableServiceLinks().ifPresent(links -> data.put("enableServiceLinks", links));
        spec.hostIPC().ifPresent(hostIPC -> data.put("hostIPC", hostIPC));
        spec.hostNetwork().ifPresent(hostNetwork -> data.put("hostNetwork", hostNetwork));
        spec.hostPID().ifPresent(hostPID -> data.put("hostPID", hostPID));
        spec.hostname().ifPresent(hostname -> data.put("hostname", hostname));
        if (!spec.imagePullSecrets().isEmpty()) {
            data.put("imagePullSecrets", imagePullSecrets(spec.imagePullSecrets()));
        }
        spec.nodeName().ifPresent(nodeName -> data.put("nodeName", nodeName));
        spec.preemptionPolicy().ifPresent(preemptionPolicy -> data.put("preemptionPolicy", preemptionPolicy));
        spec.priority().ifPresent(priority -> data.put("priority", priority));
        spec.priorityClassName().ifPresent(priorityClassName -> data.put("priorityClassName", priorityClassName));
        spec.restartPolicy().ifPresent(restartPolicy -> data.put("restartPolicy", restartPolicy));
        spec.runtimeClassName().ifPresent(runtimeClassName -> data.put("runtimeClassName", runtimeClassName));
        spec.schedulerName().ifPresent(schedulerName -> data.put("schedulerName", schedulerName));
        spec.serviceAccount().ifPresent(serviceAccount -> data.put("serviceAccount", serviceAccount));
        spec.serviceAccountName().ifPresent(serviceAccountName -> data.put("serviceAccountName", serviceAccountName));
        spec.shareProcessNamespace().ifPresent(namespace -> data.put("shareProcessNamespace", namespace));
        spec.subdomain().ifPresent(subdomain -> data.put("subdomain", subdomain));
        spec.terminationGracePeriodSeconds().ifPresent(seconds -> data.put("terminationGracePeriodSeconds", seconds));
        if (!spec.volumes().isEmpty()) {
            data.put("volumes", volumes(spec.volumes()));
        }
        return data;
    }

    private List<Map<String, Object>> volumes(final List<Volume> volumes) {
        final var list = new ArrayList<Map<String, Object>>();
        volumes.forEach(volume -> list.add(volume(volume)));
        return list;
    }

    private Map<String, Object> volume(final Volume volume) {
        final var data = new LinkedHashMap<String, Object>();
        volume.configMap().ifPresent(configMap -> data.put("configMap", configMap(configMap)));
        volume.emptyDir().ifPresent(emptyDir -> data.put("emptyDir", emptyDir(emptyDir)));
        data.put("name", volume.name());
        volume.persistentVolumeClaim().ifPresent(claim ->
                data.put("persistentVolumeClaim", persistentVolumeClaimVolumeSource(claim)));
        volume.secret().ifPresent(secret -> data.put("secret", secretVolumeSource(secret)));
        return data;
    }

    private Map<String, Object> secretVolumeSource(final SecretVolumeSource secret) {
        final var data = new LinkedHashMap<String, Object>();
        secret.defaultMode().ifPresent(defaultMode -> data.put("defaultMode", defaultMode));
        if (!secret.items().isEmpty()) {
            data.put("items", keyToPaths(secret.items()));
        }
        secret.optional().ifPresent(optional -> data.put("optional", optional));
        data.put("secretName", secret.secretName());
        return data;
    }

    private Map<String, Object> persistentVolumeClaimVolumeSource(final PersistentVolumeClaimVolumeSource claim) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("claimName", claim.claimName());
        claim.readOnly().ifPresent(readOnly -> data.put("readOnly", readOnly));
        return data;
    }

    private Map<String, Object> emptyDir(final EmptyDirVolumeSource emptyDir) {
        final var data = new LinkedHashMap<String, Object>();
        emptyDir.medium().ifPresent(medium -> data.put("medium", medium));
        emptyDir.sizeLimit().ifPresent(sizeLimit -> data.put("sizeLimit", sizeLimit));
        return data;
    }

    private Map<String, Object> configMap(final ConfigMapVolumeSource configMap) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("name", configMap.name());
        configMap.defaultMode().ifPresent(defaultMode -> data.put("defaultMode", defaultMode));
        if (!configMap.items().isEmpty()) {
            data.put("items", keyToPaths(configMap.items()));
        }
        configMap.optional().ifPresent(optional -> data.put("optional", optional));
        return data;
    }

    private List<Map<String, Object>> keyToPaths(final List<KeyToPath> items) {
        final var list = new ArrayList<Map<String, Object>>();
        items.forEach(item -> list.add(keyToPath(item)));
        return list;
    }

    private Map<String, Object> keyToPath(final KeyToPath keyToPath) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("key", keyToPath.key());
        keyToPath.mode().ifPresent(mode -> data.put("mode", mode));
        data.put("path", keyToPath.path());
        return data;
    }

    private List<Map<String, Object>> imagePullSecrets(final List<LocalObjectReference> imagePullSecrets) {
        final var list = new ArrayList<Map<String, Object>>();
        imagePullSecrets.forEach(imagePullSecret -> list.add(localObjectReference(imagePullSecret)));
        return list;
    }

    private Map<String, Object> localObjectReference(final LocalObjectReference reference) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("name", reference.name());
        return data;
    }

    private List<Map<String, Object>> containers(final List<Container> containers) {
        final var list = new ArrayList<Map<String, Object>>();
        containers.forEach(container -> list.add(container(container)));
        return list;
    }

    private Map<String, Object> container(final Container container) {
        final var data = new LinkedHashMap<String, Object>();
        if (!container.args().isEmpty()) {
            data.put("args", container.args());
        }
        if (!container.command().isEmpty()) {
            data.put("command", container.command());
        }
        if (!container.env().isEmpty()) {
            data.put("env", envs(container.env()));
        }
        container.image().ifPresent(image -> data.put("image", image));
        container.imagePullPolicy().ifPresent(imagePullPolicy -> data.put("imagePullPolicy", imagePullPolicy));
        data.put("name", container.name());
        container.livenessProbe().ifPresent(livenessProbe -> data.put("livenessProbe", probe(livenessProbe)));
        container.resources().ifPresent(resources -> data.put("resources", resources(resources)));
        container.readinessProbe().ifPresent(readinessProbe -> data.put("readinessProbe", probe(readinessProbe)));
        container.securityContext().ifPresent(securityContext -> data.put("securityContext", securityContext(securityContext)));
        container.startupProbe().ifPresent(startupProbe -> data.put("startupProbe", probe(startupProbe)));
        container.stdin().ifPresent(stdin -> data.put("stdin", stdin));
        container.stdinOnce().ifPresent(stdinOnce -> data.put("stdinOnce", stdinOnce));
        container.terminationMessagePath().ifPresent(terminationMessagePath ->
                data.put("terminationMessagePath", terminationMessagePath));
        container.terminationMessagePolicy().ifPresent(terminationMessagePolicy ->
                data.put("terminationMessagePolicy", terminationMessagePolicy));
        container.tty().ifPresent(tty -> data.put("tty", tty));
        if (!container.volumeMounts().isEmpty()) {
            data.put("volumeMounts", volumeMounts(container.volumeMounts()));
        }
        container.workingDir().ifPresent(workingDir -> data.put("workingDir", workingDir));
        return data;
    }

    private Map<String, Object> securityContext(final SecurityContext securityContext) {
        final var data = new LinkedHashMap<String, Object>();
        securityContext.allowPrivilegeEscalation().ifPresent(allowPrivilegeEscalation ->
                data.put("allowPrivilegeEscalation", allowPrivilegeEscalation));
        securityContext.privileged().ifPresent(privileged -> data.put("privileged", privileged));
        securityContext.procMount().ifPresent(procMount -> data.put("procMount", procMount));
        securityContext.readOnlyRootFilesystem().ifPresent(readOnlyRootFilesystem ->
                data.put("readOnlyRootFilesystem", readOnlyRootFilesystem));
        securityContext.runAsGroup().ifPresent(runAsGroup -> data.put("runAsGroup", runAsGroup));
        securityContext.runAsNonRoot().ifPresent(runAsNonRoot -> data.put("runAsNonRoot", runAsNonRoot));
        securityContext.runAsUser().ifPresent(runAsUser -> data.put("runAsUser", runAsUser));
        return data;
    }

    private List<Map<String, Object>> volumeMounts(final List<VolumeMount> volumeMounts) {
        final var list = new ArrayList<Map<String, Object>>();
        volumeMounts.forEach(volumeMount -> list.add(volumeMount(volumeMount)));
        return list;
    }

    private Map<String, Object> volumeMount(final VolumeMount volumeMount) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("mountPath", volumeMount.mountPath());
        volumeMount.mountPropagation().ifPresent(mountPropagation -> data.put("mountPropagation", mountPropagation));
        data.put("name", volumeMount.name());
        volumeMount.readOnly().ifPresent(readOnly -> data.put("readOnly", readOnly));
        volumeMount.subPath().ifPresent(subPath -> data.put("subPath", subPath));
        volumeMount.subPathExpr().ifPresent(subPathExpr -> data.put("subPathExpr", subPathExpr));
        return data;
    }

    private List<Map<String, Object>> envs(final List<EnvVar> envs) {
        final var list = new ArrayList<Map<String, Object>>();
        envs.forEach(env -> list.add(env(env)));
        return list;
    }

    private Map<String, Object> env(final EnvVar env) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("name", env.name());
        env.value().ifPresent(value -> data.put("value", value));
        env.valueFrom().ifPresent(valueFrom -> data.put("valueFrom", envVarSource(valueFrom)));
        return data;
    }

    private Map<String, Object> envVarSource(final EnvVarSource valueFrom) {
        final var data = new LinkedHashMap<String, Object>();
        valueFrom.secretKeyRef().ifPresent(secretKeyRef -> data.put("secretKeyRef", secretKeyRef(secretKeyRef)));
        return data;
    }

    private Map<String, Object> secretKeyRef(final SecretKeySelector secretKeyRef) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("key", secretKeyRef.key());
        data.put("name", secretKeyRef.name());
        data.put("optional", secretKeyRef.optional());
        return data;
    }

    private Map<String, Object> resources(final ResourceRequirements resources) {
        final var data = new LinkedHashMap<String, Object>();
        resources.requests().ifPresent(requests -> data.put("requests", constraint(requests)));
        resources.limits().ifPresent(limits -> data.put("limits", constraint(limits)));
        return data;
    }

    private Map<String, Object> constraint(final ResourceConstraint requests) {
        final var data = new LinkedHashMap<String, Object>();
        requests.cpu().ifPresent(cpu -> data.put("cpu", cpu));
        requests.memory().ifPresent(memory -> data.put("memory", memory));
        return data;
    }

    private Map<String, Object> probe(final Probe probe) {
        final var data = new LinkedHashMap<String, Object>();
        probe.failureThreshold().ifPresent(failureThreshold -> data.put("failureThreshold", failureThreshold));
        probe.successThreshold().ifPresent(successThreshold -> data.put("successThreshold", successThreshold));
        probe.initialDelaySeconds().ifPresent(initialDelaySeconds -> data.put("initialDelaySeconds", initialDelaySeconds));
        probe.periodSeconds().ifPresent(periodSeconds -> data.put("periodSeconds", periodSeconds));
        probe.timeoutSeconds().ifPresent(timeoutSeconds -> data.put("timeoutSeconds", timeoutSeconds));
        probe.httpGet().ifPresent(httpGet -> data.put("httpGet", httpGet(httpGet)));
        return data;
    }

    private Map<String, Object> httpGet(final HTTPGetAction httpGet) {
        final var data = new LinkedHashMap<String, Object>();
        httpGet.host().ifPresent(host -> data.put("host", host));
        httpGet.scheme().ifPresent(scheme -> data.put("scheme", scheme));
        data.put("path", httpGet.path());
        data.put("port", httpGet.port());
        return data;
    }

    private Map<String, Object> labelSelector(final LabelSelector selector) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("matchLabels", matchLabels(selector.labels()));
        return data;
    }

    private Map<String, Object> matchLabels(final List<Selector> labels) {
        final var data = new LinkedHashMap<String, Object>();
        labels.forEach(label -> data.put(label.key(), label.value()));
        return data;
    }

    private String asYaml(final Service service) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("apiVersion", version(Service.class, apiVersion));
        data.put("kind", "Service");
        service.metadata().ifPresent(metadata -> data.put("metadata", objectMeta(metadata)));
        data.put("spec", serviceSpec(service.spec()));
        return yaml.dump(data);
    }

    private Map<String, Object> objectMeta(final ObjectMeta objectMeta) {
        final var data = new LinkedHashMap<String, Object>();
        if (!objectMeta.annotations().isEmpty()) {
            data.put("annotations", annotations(objectMeta.annotations()));
        }
        if (!objectMeta.labels().isEmpty()) {
            data.put("labels", labels(objectMeta.labels()));
        }
        objectMeta.name().ifPresent(name -> data.put("name", name));
        objectMeta.namespace().ifPresent(namespace -> data.put("namespace", namespace));
        return data;
    }

    private Map<String, Object> annotations(final List<Annotation> annotations) {
        final var data = new LinkedHashMap<String, Object>();
        annotations.forEach(annotation -> data.put(annotation.key(), annotation.value()));
        return data;
    }

    private Map<String, Object> labels(final List<Label> labels) {
        final var data = new LinkedHashMap<String, Object>();
        labels.forEach(label -> data.put(label.key(), label.value()));
        return data;
    }

    private Map<String, Object> serviceSpec(final ServiceSpec spec) {
        final var data = new LinkedHashMap<String, Object>();
        if (!spec.ports().isEmpty()) {
            data.put("ports", servicePorts(spec.ports()));
        }
        if (!spec.ports().isEmpty()) {
            data.put("selector", selectors(spec.selectors()));
        }
        spec.type().ifPresent(type -> data.put("type", type));
        return data;
    }

    private Map<String, Object> selectors(final List<Selector> selectors) {
        final var data = new LinkedHashMap<String, Object>();
        selectors.forEach(selector -> data.put(selector.key(), selector.value()));
        return data;
    }

    private List<Map<String, Object>> servicePorts(final List<ServicePort> ports) {
        final var list = new ArrayList<Map<String, Object>>();
        ports.forEach(port -> list.add(servicePort(port)));
        return list;
    }

    private Map<String, Object> servicePort(final ServicePort port) {
        final var data = new LinkedHashMap<String, Object>();
        port.name().ifPresent(name -> data.put("name", name));
        data.put("port", port.port());
        port.protocol().ifPresent(protocol -> data.put("protocol", protocol));
        port.nodePort().ifPresent(nodePort -> data.put("nodePort", nodePort));
        port.targetPort().ifPresent(targetPort -> data.put("targetPort", targetPort));
        return data;
    }

    private String asYaml(final Ingress ingress) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("apiVersion", version(Ingress.class, apiVersion));
        data.put("kind", "Ingress");
        ingress.metadata().ifPresent(metadata -> data.put("metadata", objectMeta(metadata)));
        data.put("spec", ingressSpec(ingress.spec()));
        return yaml.dump(data);
    }

    private Map<String, Object> ingressSpec(final IngressSpec spec) {
        final var data = new LinkedHashMap<String, Object>();
        if (!spec.rules().isEmpty()) {
            data.put("rules", rules(spec.rules()));
        }
        if (!spec.tls().isEmpty()) {
            data.put("tls", tls(spec.tls()));
        }
        return data;
    }

    private List<Map<String, Object>> rules(List<IngressRule> rules) {
        final var list = new ArrayList<Map<String, Object>>();
        rules.forEach(rule -> list.add(rule(rule)));
        return list;
    }

    private Map<String, Object> rule(IngressRule rule) {
        final var data = new LinkedHashMap<String, Object>();
        rule.host().ifPresent(host -> data.put("host", host));
        data.put("http", http(rule.http()));
        return data;
    }

    private Map<String, Object> http(final HTTPIngressRuleValue http) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("paths", paths(http.paths()));
        return data;
    }

    private List<Map<String, Object>> paths(final List<HTTPIngressPath> paths) {
        final var list = new ArrayList<Map<String, Object>>();
        paths.forEach(path -> list.add(path(path)));
        return list;
    }

    private Map<String, Object> path(final HTTPIngressPath path) {
        final var data = new LinkedHashMap<String, Object>();
        path.path().ifPresent(p -> data.put("path", p));
        data.put("backend", backend(path.backend()));
        return data;
    }

    private Map<String, Object> backend(final IngressBackend backend) {
        final var data = new LinkedHashMap<String, Object>();
        data.put("serviceName", backend.serviceName());
        data.put("servicePort", backend.servicePort().port());
        return data;
    }

    private List<Map<String, Object>> tls(List<IngressTLS> tls) {
        final var list = new ArrayList<Map<String, Object>>();
        tls.forEach(cert -> list.add(cert(cert)));
        return list;
    }

    private Map<String, Object> cert(final IngressTLS cert) {
        final var data = new LinkedHashMap<String, Object>();
        if (!cert.hosts().isEmpty()) {
            data.put("hosts", cert.hosts());
        }
        cert.secretName().ifPresent(secretName -> data.put("secretName", secretName));
        return data;
    }

}
