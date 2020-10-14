package com.autentia;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@MicronautTest
public class GastosCompartidosTest {

    @Inject
    EmbeddedServer server;

    @Test
    void testGroups() {

        try(RxHttpClient client = server.getApplicationContext().createBean(RxHttpClient.class, server.getURL())) {

            Assertions.assertEquals(HttpStatus.OK, client.toBlocking().exchange("/groups").status());
        }
    }

    @Test
    void testFriends() {

        try(RxHttpClient client = server.getApplicationContext().createBean(RxHttpClient.class, server.getURL())) {

            Assertions.assertEquals(HttpStatus.OK, client.toBlocking().exchange("/friends").status());
        }
    }

    @Test
    void testExpenses() {

        try(RxHttpClient client = server.getApplicationContext().createBean(RxHttpClient.class, server.getURL())) {

            Assertions.assertEquals(HttpStatus.OK, client.toBlocking().exchange("/expenses").status());
        }
    }

}
