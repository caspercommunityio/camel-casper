package org.apache.camel.component.casper.consumer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.casper.ConsumerEvent;
import org.junit.jupiter.api.Test;

public class ConsumerTest_DEPLOY_EXPIRED_Event_Test2 extends CasperConsumerTest {

	@Override
	public boolean isUseAdviceWith() {
		return false;
	}

	@Test
	public void testEvent() throws Exception {

		mockResult.expectedMessageCount(1);
		mockResult.expectedHeaderReceived("status", "done");
		mockResult.expectedHeaderReceived("event", ConsumerEvent.DEPLOY_EXPIRED);
		mockError.expectedMinimumMessageCount(0);
		mockResult.assertIsSatisfied();
		mockError.assertIsSatisfied();
	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			public void configure() {
				errorHandler(deadLetterChannel("mock:error"));
				from("casper:http://localhost:8080/events/deploys?event=deploy_expired").to(mockResult);
			}
		};
	}

}