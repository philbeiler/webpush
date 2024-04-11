package com.springboot.webpush.selenium;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Java wrapper for interacting with the Web Push Testing Service.
 */
public class TestingService {
	private final String baseUrl;

	public TestingService(final String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * Start a new test suite.
	 *
	 * @return
	 */
	public int startTestSuite() throws IOException {
		final String startTestSuite = request(baseUrl + "start-test-suite/");

		final JsonElement root = JsonParser.parseString(startTestSuite);

		return root.getAsJsonObject().get("data").getAsJsonObject().get("testSuiteId").getAsInt();
	}

	/**
	 * Get a test ID and subscription for the given test case.
	 *
	 * @param testSuiteId
	 * @param configuration
	 * @return
	 * @throws IOException
	 */
	public JsonObject getSubscription(final int testSuiteId, final Configuration configuration) throws IOException {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("testSuiteId", testSuiteId);
		jsonObject.addProperty("browserName", configuration.browser);
		jsonObject.addProperty("browserVersion", configuration.version);

		if (configuration.gcmSenderId != null) {
			jsonObject.addProperty("gcmSenderId", configuration.gcmSenderId);
		}

		if (configuration.publicKey != null) {
			jsonObject.addProperty("vapidPublicKey", configuration.publicKey);
		}

		final HttpEntity entity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);

		final String getSubscription = request(baseUrl + "get-subscription/", entity);

		return getData(getSubscription);
	}

	/**
	 * Get the notification status for the given test case.
	 *
	 * @param testSuiteId
	 * @param testId
	 * @return
	 * @throws IOException
	 */
	public JsonArray getNotificationStatus(final int testSuiteId, final int testId) throws IOException {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("testSuiteId", testSuiteId);
		jsonObject.addProperty("testId", testId);

		final HttpEntity entity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);

		final String notificationStatus = request(baseUrl + "get-notification-status/", entity);

		return getData(notificationStatus).get("messages").getAsJsonArray();
	}

	/**
	 * End the given test suite.
	 *
	 * @return
	 */
	public boolean endTestSuite(final int testSuiteId) throws IOException {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("testSuiteId", testSuiteId);

		final HttpEntity entity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);

		final String endTestSuite = request(baseUrl + "end-test-suite/", entity);

		return getData(endTestSuite).get("success").getAsBoolean();
	}

	/**
	 * Perform HTTP request and return response.
	 *
	 * @param uri
	 * @return
	 */
	protected String request(final String uri) throws IOException {
		return request(uri, null);
	}

	/**
	 * Perform HTTP request and return response.
	 *
	 * @param uri
	 * @return
	 */
	protected String request(final String uri, final HttpEntity entity) throws IOException {
		return Request.Post(uri).body(entity).execute().handleResponse(httpResponse -> {
			final String json = EntityUtils.toString(httpResponse.getEntity());

			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				final JsonElement root = JsonParser.parseString(json);
				final JsonObject error = root.getAsJsonObject().get("error").getAsJsonObject();

				final String errorId = error.get("id").getAsString();
				final String errorMessage = error.get("message").getAsString();

				final String body = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);

				throw new IllegalStateException(
						"Error while requesting " + uri + " with body " + body + " (" + errorId + ": " + errorMessage);
			}

			return json;
		});
	}

	/**
	 * Get the a JSON object of the data in the JSON response.
	 *
	 * @param response
	 */
	protected JsonObject getData(final String response) {
		final JsonElement root = JsonParser.parseString(response);

		return root.getAsJsonObject().get("data").getAsJsonObject();
	}
}
