package com.springboot.webpush.impl;

import static org.asynchttpclient.Dsl.asyncHttpClient;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Response;
import org.jose4j.lang.JoseException;

public class PushAsyncService extends AbstractPushService<PushAsyncService> {

	private final AsyncHttpClient httpClient = asyncHttpClient();

	public PushAsyncService() {
	}

	public PushAsyncService(final String gcmApiKey) {
		super(gcmApiKey);
	}

	public PushAsyncService(final KeyPair keyPair) {
		super(keyPair);
	}

	public PushAsyncService(final KeyPair keyPair, final String subject) {
		super(keyPair, subject);
	}

	public PushAsyncService(final String publicKey, final String privateKey) throws GeneralSecurityException {
		super(publicKey, privateKey);
	}

	public PushAsyncService(final String publicKey, final String privateKey, final String subject)
			throws GeneralSecurityException {
		super(publicKey, privateKey, subject);
	}

	/**
	 * Send a notification asynchronously.
	 *
	 * @param notification
	 * @param encoding
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 * @throws JoseException
	 */
	public CompletableFuture<Response> send(final Notification notification, final Encoding encoding)
			throws GeneralSecurityException, IOException, JoseException {
		final BoundRequestBuilder httpPost = preparePost(notification, encoding);
		return httpPost.execute().toCompletableFuture();
	}

	public CompletableFuture<Response> send(final Notification notification)
			throws GeneralSecurityException, IOException, JoseException {
		return send(notification, Encoding.AES128GCM);
	}

	/**
	 * Prepare a POST request for AHC.
	 *
	 * @param notification
	 * @param encoding
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 * @throws JoseException
	 */
	public BoundRequestBuilder preparePost(final Notification notification, final Encoding encoding)
			throws GeneralSecurityException, IOException, JoseException {
		final HttpRequest request = prepareRequest(notification, encoding);
		final BoundRequestBuilder httpPost = httpClient.preparePost(request.getUrl());
		request.getHeaders().forEach(httpPost::addHeader);
		if (request.getBody() != null) {
			httpPost.setBody(request.getBody());
		}
		return httpPost;
	}
}
