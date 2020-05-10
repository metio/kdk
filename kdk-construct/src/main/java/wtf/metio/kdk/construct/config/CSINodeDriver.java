/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#csinodedriver-v1-storage-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface CSINodeDriver {

    //region Builders
    static ImmutableCSINodeDriver.Builder builder() {
        return ImmutableCSINodeDriver.builder();
    }
    //endregion

    /**
     * allocatable represents the volume resources of a node that are available for scheduling. This field is beta.
     */
    Optional<VolumeNodeResources> allocatable();

    /**
     * This is the name of the CSI driver that this object refers to. This MUST be the same name returned by the CSI
     * GetPluginName() call for that driver.
     */
    String name();

    /**
     * nodeID of the node from the driver point of view. This field enables Kubernetes to communicate with storage
     * systems that do not share the same nomenclature for nodes. For example, Kubernetes may refer to a given node as
     * "node1", but the storage system may refer to the same node as "nodeA". When Kubernetes issues a command to the
     * storage system to attach a volume to a specific node, it can use this field to refer to the node name using the
     * ID that the storage system will understand, e.g. "nodeA" instead of "node1". This field is required.
     */
    String ndoeID();

    /**
     * topologyKeys is the list of keys supported by the driver. When a driver is initialized on a cluster, it provides
     * a set of topology keys that it understands (e.g. "company.com/zone", "company.com/region"). When a driver is
     * initialized on a node, it provides the same topology keys along with values. Kubelet will expose these topology
     * keys as labels on its own node object. When Kubernetes does topology aware provisioning, it can use this list to
     * determine which labels it should retrieve from the node object and pass back to the driver. It is possible for
     * different nodes to use different topology keys. This can be empty if driver does not support topology.
     */
    List<String> topologyKeys();

}
