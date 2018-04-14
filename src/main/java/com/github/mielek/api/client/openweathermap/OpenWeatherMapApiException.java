package com.github.mielek.api.client.openweathermap;

import com.github.mielek.api.client.openweathermap.model.ErrorMessage;

import javax.ws.rs.core.Response;
import java.net.URI;

public class OpenWeatherMapApiException extends Exception {

    private Response.StatusType responseStatus;
    private ErrorMessage errorMessage;
    private URI uri;

    public OpenWeatherMapApiException(Response.StatusType responseStatus, URI uri) {
        this(responseStatus, null, uri);
    }

    public OpenWeatherMapApiException(Response.StatusType responseStatus, ErrorMessage errorMessage, URI uri) {
        this.responseStatus = responseStatus;
        this.errorMessage = errorMessage;
        this.uri = uri;
    }

    @Override
    public String getMessage() {
        if(errorMessage!=null){
            return "Error invoking \"" + uri +
                    "\", returned status " + responseStatus.getStatusCode() + " " + responseStatus.getReasonPhrase() +
                    " with message \"" + errorMessage.getMessage() + "\"";
        } else {
            return "Error invoking \"" + uri + "\", returned status " + responseStatus.getStatusCode() + " " + responseStatus.getReasonPhrase();
        }
    }
}
