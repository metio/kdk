/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#objectmeta-v1-meta">k8s spec</a>
 */
@Value.Immutable
public interface ObjectMeta {

    //region Builders
    static ImmutableObjectMeta.Builder builder() {
        return ImmutableObjectMeta.builder();
    }
    //endregion

    /**
     * Annotations is an unstructured key value map stored with a resource that may be set by external tools to store
     * and retrieve arbitrary metadata. They are not queryable and should be preserved when modifying objects.
     * 
     * @see <a href="http://kubernetes.io/docs/user-guide/annotations">k8s docs</a>
     */
    List<Annotation> annotations();

    /**
     * The name of the cluster which the object belongs to. This is used to distinguish resources with same name and
     * namespace in different clusters. This field is not set anywhere right now and apiserver is going to ignore it if
     * set in create or update request.
     */
    Optional<String> clusterName();

    /**
     * Must be empty before the object is deleted from the registry. Each entry is an identifier for the responsible
     * component that will remove the entry from the list. If the deletionTimestamp of the object is non-nil, entries in
     * this list can only be removed. Finalizers may be processed and removed in any order. Order is NOT enforced
     * because it introduces significant risk of stuck finalizers. finalizers is a shared field, any actor with
     * permission can reorder it. If the finalizer list is processed in order, then this can lead to a situation in
     * which the component responsible for the first finalizer in the list is waiting for a signal (field value,
     * external system, or other) produced by a component responsible for a finalizer later in the list, resulting in a
     * deadlock. Without enforced ordering finalizers are free to order amongst themselves and are not vulnerable to
     * ordering changes in the list.
     */
    List<String> finalizers();

    /**
     * Map of string keys and values that can be used to organize and categorize (scope and select) objects. May match
     * selectors of replication controllers and services.
     * 
     * @see <a href="http://kubernetes.io/docs/user-guide/labels">k8s docs</a>
     */
    List<Label> labels();

    /**
     * Name must be unique within a namespace. Is required when creating resources, although some resources may allow a
     * client to request the generation of an appropriate name automatically. Name is primarily intended for creation
     * idempotence and configuration definition. Cannot be updated.
     * 
     * @see <a href="http://kubernetes.io/docs/user-guide/identifiers#names">k8s docs</a>
     */
    Optional<String> name();

    /**
     * Namespace defines the space within each name must be unique. An empty namespace is equivalent to the "default"
     * namespace, but "default" is the canonical representation. Not all objects are required to be scoped to a
     * namespace - the value of this field for those objects will be empty. Must be a DNS_LABEL. Cannot be updated.
     * 
     * @see <a href="http://kubernetes.io/docs/user-guide/namespaces">k8s docs</a>
     */
    Optional<String> namespace();

    // TODO: ownerReferences

}
