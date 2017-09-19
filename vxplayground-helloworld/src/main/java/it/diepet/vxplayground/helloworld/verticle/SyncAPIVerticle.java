package it.diepet.vxplayground.helloworld.verticle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.WorkerExecutor;

public class SyncAPIVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SyncAPIVerticle.class);

	@Override
	public void start(Future<Void> fut) {
		final WorkerExecutor wex = vertx.createSharedWorkerExecutor("my-custom-worker-pool", 10, 5000);
		vertx.createHttpServer().requestHandler(r -> {
			final int fibonacciToCalculate = 45;
			LOGGER.info("Calculate Fibonacci {}", fibonacciToCalculate);
			wex.executeBlocking(future  -> {
				int result = slowFibonacci(fibonacciToCalculate);
				future.complete(result);
			}, false, result -> { 
				if (result.succeeded()) {
					Integer fibonacciResult = (Integer) result.result();
					r.response().end("<h1>Hello, Fibonacci result is " + fibonacciResult + "</h1>");
				} else {
					r.response().setStatusCode(503).end();
				}
			});
		}).listen(8080, result -> {
			if (result.succeeded()) {
				fut.complete();
			} else {
				fut.fail(result.cause());
			}
		});
	}

	private int slowFibonacci(int i) {
		if (i <= 1) return i;
		else return slowFibonacci(i - 1) + slowFibonacci(i - 2);
	}
}
