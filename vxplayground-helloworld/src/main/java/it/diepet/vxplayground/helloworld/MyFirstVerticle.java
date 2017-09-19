package it.diepet.vxplayground.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyFirstVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyFirstVerticle.class);

	@Override
	public void start(Future<Void> fut) {
		vertx.createHttpServer().requestHandler(r -> {
			LOGGER.info("Received some request");
			r.response().end("<h1>Hello from my first " + "Vert.x 3 application</h1>");
		}).listen(8080, result -> {
			if (result.succeeded()) {
				fut.complete();
			} else {
				fut.fail(result.cause());
			}
		});
	}
}
