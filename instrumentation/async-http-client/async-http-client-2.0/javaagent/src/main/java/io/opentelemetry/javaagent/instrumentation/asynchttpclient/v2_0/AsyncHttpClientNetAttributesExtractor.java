/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.asynchttpclient.v2_0;

import io.opentelemetry.instrumentation.api.instrumenter.net.InetSocketAddressNetClientAttributesExtractor;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes;
import java.net.InetSocketAddress;
import org.asynchttpclient.Response;
import org.checkerframework.checker.nullness.qual.Nullable;

final class AsyncHttpClientNetAttributesExtractor
    extends InetSocketAddressNetClientAttributesExtractor<RequestContext, Response> {

  @Override
  public String transport(RequestContext request, @Nullable Response response) {
    return SemanticAttributes.NetTransportValues.IP_TCP;
  }

  @Override
  public @Nullable InetSocketAddress getAddress(
      RequestContext request, @Nullable Response response) {
    if (response != null && response.getRemoteAddress() instanceof InetSocketAddress) {
      return (InetSocketAddress) response.getRemoteAddress();
    }
    return null;
  }
}