package co.uk.gel.utils;

public enum HttpOperations {
GET("GET"),POST("POST"),PUT("PUT"),PATCH("PATCH");
	private final String ops;

	HttpOperations(String ops) {
        this.ops = ops;
    }

	public static HttpOperations setOpertaion(String httpCall) {
		HttpOperations ops = null;
		httpCall = httpCall.toUpperCase();
		switch(httpCall) {
		case "GET":
			ops = GET;
			break;
		case "PUT":
			ops = PUT;
			break;
		case "POST":
			ops = POST;
			break;
		case "PATCH":
			ops = PATCH;
			break;
		default:
			System.out.println("Unsupported operation: " + httpCall);
		}
		return ops;
	}
}

