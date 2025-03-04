/* Copyright (c) 2014-2016 Boundless and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 * Gabriel Roldan (Boundless) - initial implementation
 */
package org.locationtech.geogig.model.impl;

import org.locationtech.geogig.model.ObjectId;
import org.locationtech.geogig.model.RevCommit;
import org.locationtech.geogig.model.RevPerson;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * A reference to a commit in the DAG.
 * 
 */
class RevCommitImpl extends AbstractRevObject implements RevCommit {

    private ObjectId treeId;

    private ImmutableList<ObjectId> parentIds;

    private RevPerson author;

    private RevPerson committer;

    private String message;

    @Override
    public TYPE getType() {
        return TYPE.COMMIT;
    }

    /**
     * Constructs a new {@code RevCommit} with the given parameters.
     * 
     * @param id the {@link ObjectId} to use
     * @param treeId the {@link ObjectId} of the tree this commit points to
     * @param parentIds a list of parent commits' {@link ObjectId}s
     * @param author the author of this commit
     * @param committer the committer of this commit
     * @param message the message for this commit
     */
    RevCommitImpl(final ObjectId id, ObjectId treeId, ImmutableList<ObjectId> parentIds,
            RevPerson author, RevPerson committer, String message) {
        super(id);
        this.treeId = treeId;
        this.parentIds = parentIds;
        this.author = author;
        this.committer = committer;
        this.message = message;
    }

    @Override
    public ObjectId getTreeId() {
        return treeId;
    }

    @Override
    public ImmutableList<ObjectId> getParentIds() {
        return this.parentIds;
    }

    @Override
    public Optional<ObjectId> parentN(int parentIndex) {
        Optional<ObjectId> parent = Optional.absent();
        if (parentIds.size() > parentIndex) {
            parent = Optional.of(parentIds.get(parentIndex));
        }
        return parent;
    }

    @Override
    public RevPerson getAuthor() {
        return author;
    }

    @Override
    public RevPerson getCommitter() {
        return committer;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @return the {@code RevCommit} as a readable string
     */
    @Override
    public String toString() {
        return "Commit[" + getId() + ", '" + message + "']";
    }
}
