/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

/**
 * Enumeration of all supported kuberntes API versions.
 */
public enum K8sVersions {

    /**
     * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.16/">k8s 1.17</a>
     */
    VERSION_1_16,

    /**
     * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/">k8s 1.17</a>
     */
    VERSION_1_17,

    /**
     * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.18/">k8s 1.18</a>
     */
    VERSION_1_18;

}
