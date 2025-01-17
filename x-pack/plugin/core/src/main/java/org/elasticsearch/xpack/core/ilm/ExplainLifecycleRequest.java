/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.core.ilm;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.support.master.info.ClusterInfoRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.core.UpdateForV10;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * The request object used by the Explain Lifecycle API.
 * <p>
 * Multiple indices may be queried in the same request using the
 * {@link #indices(String...)} method
 */
public class ExplainLifecycleRequest extends ClusterInfoRequest<ExplainLifecycleRequest> {

    private boolean onlyErrors = false;
    private boolean onlyManaged = false;

    public ExplainLifecycleRequest(TimeValue masterTimeout) {
        super(masterTimeout);
    }

    /**
     * NB prior to 9.0 this was a TransportMasterNodeReadAction so for BwC we must remain able to read these requests until
     * we no longer need to support calling this action remotely.
     */
    @UpdateForV10(owner = UpdateForV10.Owner.DATA_MANAGEMENT)
    public ExplainLifecycleRequest(StreamInput in) throws IOException {
        super(in);
        onlyErrors = in.readBoolean();
        onlyManaged = in.readBoolean();
    }

    public boolean onlyErrors() {
        return onlyErrors;
    }

    public ExplainLifecycleRequest onlyErrors(boolean onlyErrors) {
        this.onlyErrors = onlyErrors;
        return this;
    }

    public boolean onlyManaged() {
        return onlyManaged;
    }

    public ExplainLifecycleRequest onlyManaged(boolean onlyManaged) {
        this.onlyManaged = onlyManaged;
        return this;
    }

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(indices()), indicesOptions(), onlyErrors, onlyManaged);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ExplainLifecycleRequest other = (ExplainLifecycleRequest) obj;
        return Objects.deepEquals(indices(), other.indices())
            && Objects.equals(indicesOptions(), other.indicesOptions())
            && Objects.equals(onlyErrors(), other.onlyErrors())
            && Objects.equals(onlyManaged(), other.onlyManaged());
    }

    @Override
    public String toString() {
        return "ExplainLifecycleRequest [indices()="
            + Arrays.toString(indices())
            + ", indicesOptions()="
            + indicesOptions()
            + ", onlyErrors()="
            + onlyErrors()
            + ", onlyManaged()="
            + onlyManaged()
            + "]";
    }

}
