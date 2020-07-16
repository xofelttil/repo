import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import com.yc.UndertowServletWebServerFactory;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;

public class Test {
	
	 @Bean
	    public UndertowServletWebServerFactory  undertowServletWebServerFactory() {
	        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
	        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
	            @Override
	            public void customize(Undertow.Builder builder) {
	                builder.setServerOption(UndertowOptions.RECORD_REQUEST_START_TIME, true);
	            }
	        });
	        return factory;
	    }

}
