/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import wtf.metio.kdk.construct.config.Secret;
import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.construct.service.Service;
import wtf.metio.kdk.construct.workloads.Deployment;
import wtf.metio.kdk.stack.Stack;

import static wtf.metio.kdk.serialize.K8sVersions.VERSION_1_18;

public final class Serialize {

    public static String asYaml(final Deployment deployment) {
        return asYaml(deployment, VERSION_1_18);
    }

    public static String asYaml(final Deployment deployment, final K8sVersions apiVersion) {
        return asYaml(Stack.builder().addDeployment(deployment).build(), apiVersion);
    }

    public static String asYaml(final Service service) {
        return asYaml(service, VERSION_1_18);
    }

    public static String asYaml(final Service service, final K8sVersions apiVersion) {
        return asYaml(Stack.builder().addService(service).build(), apiVersion);
    }

    public static String asYaml(final Ingress ingress) {
        return asYaml(ingress, VERSION_1_18);
    }

    public static String asYaml(final Ingress ingress, final K8sVersions apiVersion) {
        return asYaml(Stack.builder().addIngress(ingress).build(), apiVersion);
    }

    public static String asYaml(final Secret secret) {
        return asYaml(secret, VERSION_1_18);
    }

    public static String asYaml(final Secret secret, final K8sVersions apiVersion) {
        return asYaml(Stack.builder().addSecrets(secret).build(), apiVersion);
    }

    public static String asYaml(final Stack stack) {
        return asYaml(stack, VERSION_1_18);
    }

    public static String asYaml(final Stack stack, final K8sVersions apiVersion) {
        final var options = new DumperOptions();
        options.setExplicitStart(true);
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return asYaml(stack, apiVersion, options);
    }

    public static String asYaml(final Stack stack, final K8sVersions apiVersion, final DumperOptions options) {
        return new YamlSerializer(new Yaml(options), apiVersion).asYaml(stack);
    }

    private Serialize() {
        // factory class
    }

}
