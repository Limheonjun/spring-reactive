package com.emgc.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.emgc.webclient.dto.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec05BaseRequestTest extends BaseTest {

	@Autowired
	private WebClient webClient;

	@Test
	public void badRequestTest() {
		Mono<Response> responseMono = this.webClient
			.get()
			.uri("reactive-math/square/{number}/throw", 5)
			.retrieve()
			.bodyToMono(Response.class)
			.doOnNext(System.out::println)
			.doOnError(err -> System.out.println(err.getMessage()))
			;

		StepVerifier.create(responseMono)
			.verifyError(WebClientResponseException.BadRequest.class);
	}

}
