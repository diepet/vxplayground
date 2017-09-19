package it.diepet.vxplayground.helloworld;

import io.vertx.core.Launcher;
import io.vertx.core.VertxOptions;

public class AppLauncher extends Launcher {

	@Override
	public void beforeStartingVertx(VertxOptions options) {
		options.setWorkerPoolSize(4);
	}
}
