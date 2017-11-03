package it.diepet.vxplayground.helloworld.verticle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class PeriodicVerticle extends AbstractVerticle {

	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicVerticle.class);
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {

		vertx.setPeriodic(1000, id -> { LOGGER.info("Periodic!"); });
	}
	
}
