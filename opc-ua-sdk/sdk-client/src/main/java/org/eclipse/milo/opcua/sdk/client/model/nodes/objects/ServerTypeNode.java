/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public class ServerTypeNode extends BaseObjectTypeNode implements ServerType {
    public ServerTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getServerArrayNode() {
        return getPropertyNode(ServerType.SERVER_ARRAY);
    }

    public CompletableFuture<String[]> getServerArray() {
        return getProperty(ServerType.SERVER_ARRAY);
    }

    public CompletableFuture<StatusCode> setServerArray(String[] value) {
        return setProperty(ServerType.SERVER_ARRAY, value);
    }

    public CompletableFuture<PropertyTypeNode> getNamespaceArrayNode() {
        return getPropertyNode(ServerType.NAMESPACE_ARRAY);
    }

    public CompletableFuture<String[]> getNamespaceArray() {
        return getProperty(ServerType.NAMESPACE_ARRAY);
    }

    public CompletableFuture<StatusCode> setNamespaceArray(String[] value) {
        return setProperty(ServerType.NAMESPACE_ARRAY, value);
    }

    public CompletableFuture<PropertyTypeNode> getServiceLevelNode() {
        return getPropertyNode(ServerType.SERVICE_LEVEL);
    }

    public CompletableFuture<UByte> getServiceLevel() {
        return getProperty(ServerType.SERVICE_LEVEL);
    }

    public CompletableFuture<StatusCode> setServiceLevel(UByte value) {
        return setProperty(ServerType.SERVICE_LEVEL, value);
    }

    public CompletableFuture<PropertyTypeNode> getAuditingNode() {
        return getPropertyNode(ServerType.AUDITING);
    }

    public CompletableFuture<Boolean> getAuditing() {
        return getProperty(ServerType.AUDITING);
    }

    public CompletableFuture<StatusCode> setAuditing(Boolean value) {
        return setProperty(ServerType.AUDITING, value);
    }

    public CompletableFuture<PropertyTypeNode> getEstimatedReturnTimeNode() {
        return getPropertyNode(ServerType.ESTIMATED_RETURN_TIME);
    }

    public CompletableFuture<DateTime> getEstimatedReturnTime() {
        return getProperty(ServerType.ESTIMATED_RETURN_TIME);
    }

    public CompletableFuture<StatusCode> setEstimatedReturnTime(DateTime value) {
        return setProperty(ServerType.ESTIMATED_RETURN_TIME, value);
    }

    @Override
    public CompletableFuture<ServerStatusTypeNode> getServerStatusNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ServerStatus").thenApply(ServerStatusTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServerStatusDataType> getServerStatus() {
        return getServerStatusNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServerStatusDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setServerStatus(ServerStatusDataType value) {
        return getServerStatusNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ServerCapabilitiesTypeNode> getServerCapabilitiesNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ServerCapabilities").thenApply(ServerCapabilitiesTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServerDiagnosticsTypeNode> getServerDiagnosticsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ServerDiagnostics").thenApply(ServerDiagnosticsTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<VendorServerInfoTypeNode> getVendorServerInfoNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "VendorServerInfo").thenApply(VendorServerInfoTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<ServerRedundancyTypeNode> getServerRedundancyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ServerRedundancy").thenApply(ServerRedundancyTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<NamespacesTypeNode> getNamespacesNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Namespaces").thenApply(NamespacesTypeNode.class::cast);
    }
}
